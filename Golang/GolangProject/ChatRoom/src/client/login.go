package main

import (
	"Project/ChatRoom/src/common/message"
	"Project/ChatRoom/src/utils"
	"encoding/binary"
	"encoding/json"
	"fmt"
	"net"
)

func login(userId int, userPwd string) (err error) {
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
	fmt.Println("发送数据长度给客户端成功, len=", len(result))

	//将消息体发送给客户端
	_, err = conn.Write(result)
	if err != nil {
		fmt.Println("发送消息体给客户端失败: ", err)
		return
	}
	fmt.Println("发送消息体给客户端成功, result=", result)

	//处理服务端返回的数据
	msg, err = utils.ReadPkg(conn)
	if err != nil {
		fmt.Println("login() utils.ReadPkg() 错误: ", err)
		return
	}

	//将 msg.data 反序列化为 LoginResult
	var loginResultMsg message.LoginResult
	err = json.Unmarshal([]byte(msg.Data), &loginResultMsg)

	//判断是否登录成功
	if loginResultMsg.Code == 200 {
		fmt.Println("登录成功")
	} else if loginResultMsg.Code == 500 {
		fmt.Println(loginResultMsg.Error)
	}

	return nil
}
