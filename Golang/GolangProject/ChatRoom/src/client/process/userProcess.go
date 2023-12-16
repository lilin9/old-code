package process

import (
	"Project/ChatRoom/src/client/utils"
	"Project/ChatRoom/src/common/message"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
	"os"
)

/*
userProcess.go 功能：
1. 处理和用户相关的业务
2. 登录
3. 注册
*/

type UserProcess struct {
}

func (this *UserProcess) Register(userId int, userName string, userPwd string) (err error) {
	//开始连接服务器
	conn, err := net.Dial("tcp", "localhost:8889")
	if err != nil {
		fmt.Println("连接服务器错误: ", err)
		return
	}

	//延时关闭连接
	defer func() {
		err = conn.Close()
		if err != nil {
			fmt.Println("关闭 conn 连接失败: ", err)
		}
	}()

	//准备通过 conn 发送消息给服务器
	//创建 message 结构体
	var msg message.Message
	msg.Type = message.RegisterMessageType

	//创建 registerMessage 结构体
	var registerMessage = message.RegisterMessage{}
	registerMessage.User.UserId = userId
	registerMessage.User.UserPwd = userPwd
	registerMessage.User.UserName = userName

	//将 registerMessage 序列化
	result, err := json.Marshal(registerMessage)
	if err != nil {
		fmt.Println("序列化 registerMessage 错误: ", err)
	}
	msg.Data = string(result)

	//序列化 msg
	result, err = json.Marshal(msg)
	if err != nil {
		fmt.Println("序列化 message 错误: ", err)
	}

	//创建一个 transfer 实例
	transfer := &utils.Transfer{
		Conn: conn,
	}

	//发送消息体给服务器
	err = transfer.WritePkg(result)
	if err != nil {
		fmt.Println("client UserProcess() Register() WritePkg() 错误: ", err)
	}
	//处理服务端返回的数据
	msg, err = transfer.ReadPkg()
	if err != nil {
		fmt.Println("login() utils.ReadPkg() 错误: ", err)
		return
	}

	//将 msg.data 反序列化为 registerResultMessage
	var registerResultMessage message.RegisterResultMessage
	err = json.Unmarshal([]byte(msg.Data), &registerResultMessage)

	//判断是否注册成功
	if registerResultMessage.Code == 200 {
		//注册成功，要求用户重新登录
		fmt.Println("注册成功，请重新登录!")
		os.Exit(0)
	} else {
		fmt.Println(registerResultMessage.Error)
		os.Exit(0)
	}

	return
}

func (this *UserProcess) Login(userId int, userPwd string) (err error) {
	//开始连接服务器
	conn, err := net.Dial("tcp", "localhost:8889")
	if err != nil {
		fmt.Println("连接服务器错误: ", err)
		return
	}

	//延时关闭连接
	defer func() {
		err = conn.Close()
		if err != nil {
			fmt.Println("关闭 conn 连接失败: ", err)
		}
	}()

	//准备通过 conn 发送消息给服务器
	//创建 message 结构体
	var msg message.Message
	msg.Type = message.LoginMessageType

	//创建 loginMessage 结构体
	var loginMessage = message.LoginMessage{}
	loginMessage.UserId = userId
	loginMessage.UserPwd = userPwd

	//将 loginMessage 封装进 msg
	result, err := json.Marshal(loginMessage)
	if err != nil {
		fmt.Println("序列化 loginMessage 错误: ", err)
	}
	msg.Data = string(result)

	//序列化 msg
	result, err = json.Marshal(msg)
	if err != nil {
		fmt.Println("序列化 message 错误: ", err)
	}

	//将消息转发给客户端
	//先发送 data 的长度
	var dataLen uint32
	dataLen = uint32(len(result))
	var buf = make([]byte, 4)
	binary.BigEndian.PutUint32(buf, dataLen)

	//将长度发送给客户端
	n, err := conn.Write(buf)
	if n != 4 || err != nil {
		fmt.Println("发送长度给客户端失败: ", err)
		return
	}
	//fmt.Println("发送数据长度给客户端成功, len=", len(result))

	//将消息体发送给客户端
	_, err = conn.Write(result)
	if err != nil {
		fmt.Println("发送消息体给客户端失败: ", err)
		return
	}
	fmt.Println("发送消息体给客户端成功, result=", string(result)) //{\"userId\":1001,\"userPwd\":\"123abc\",\"username\":\"\"}

	//创建 transfer 实例
	transfer := &utils.Transfer{
		Conn: conn,
	}
	//处理服务端返回的数据
	msg, err = transfer.ReadPkg()
	if err != nil {
		fmt.Println("login() utils.ReadPkg() 错误: ", err)
		return
	}

	//将 msg.data 反序列化为 LoginResult
	var loginResultMsg message.LoginResult
	err = json.Unmarshal([]byte(msg.Data), &loginResultMsg)

	//判断是否登录成功
	if loginResultMsg.Code == 200 {
		//初始化 curUser
		curUser.Conn = conn
		curUser.UserId = userId
		curUser.UserStatus = message.UserOnline

		fmt.Println("用户在线ID列表:")
		for _, value := range loginResultMsg.UsersId {
			fmt.Println("用户ID:\t", value)

			//进行客户端 onlineUser 初始化工作
			user := &message.User{
				UserId:     value,
				UserStatus: message.UserOnline,
			}
			onlineUsers[value] = user
		}

		//在显示登录成功的菜单之前，还需要在客户端启动一个协程，该协程用来保持和服务端的通讯。
		//如果服务端有数据推送给客户端，则接受并显示在客户端的终端
		go ServerProcessMessage(conn)

		//1. 循环显示登录成功后的菜单
		for {
			showMenu()
		}
	} else {
		fmt.Println(loginResultMsg.Error)
	}

	return
}
