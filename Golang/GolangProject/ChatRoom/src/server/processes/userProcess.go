package processes

import (
	"Project/ChatRoom/src/common/message"
	"Project/ChatRoom/src/server/model"
	"Project/ChatRoom/src/server/utils"
	"encoding/json"
	"fmt"
	"net"
)

/*
userProcess.go 主要处理：
1. 处理和用户相关的请求
2. 登录
3. 注册
4. 注销
5. 用户列表管理
*/

type UserProcess struct {
	Conn net.Conn
	//增加 用户签名 字段
	userId int
}

// ServerProcessRegister 这个函数专门用来处理注册请求
func (this *UserProcess) ServerProcessRegister(msg *message.Message) (err error) {
	//核心代码
	//先从 message 中取出 message.Data，并直接反序列化成 registerMsg
	var registerMsg message.RegisterMessage
	err = json.Unmarshal([]byte(msg.Data), &registerMsg)
	if err != nil {
		fmt.Println("ServerProcessRegister() 反序列化 message.Data 错误: ", err)
		return
	}

	//1. 先声明一个 resultMessage
	var resultMsg message.Message
	resultMsg.Type = message.RegisterResMesType
	//再声明一个 registerResultMsg
	var registerResultMsg message.RegisterResultMessage

	//2. 对用户信息进行注册
	err = model.MyUserDao.Register(&registerMsg.User)
	if err != nil {
		if err == model.ErrorUserExists {
			registerResultMsg.Code = 505
			registerResultMsg.Error = model.ErrorUserExists.Error()
		} else {
			registerResultMsg.Code = 506
			registerResultMsg.Error = "注册过程中出现未知错误"
		}
	} else {
		registerResultMsg.Code = 200
	}

	//3. 序列化 registerResultMsg
	buf, err := json.Marshal(registerResultMsg)
	if err != nil {
		fmt.Println("serverProcessRegister() 序列化 registerResultMsg 错误: ", err)
		return
	}

	//4. 将 buf 封装给 resultMsg
	resultMsg.Data = string(buf)

	//5. 将 resultMsg 序列化后准备发送给客户端
	data, err := json.Marshal(resultMsg)
	if err != nil {
		fmt.Println("serverProcessRegister() 序列化 resultMsg 错误: ", err)
		return
	}

	//6. 发送给客户端（这里的逻辑封装成一个函数: writePkg()）
	transfer := &utils.Transfer{Conn: this.Conn}
	err = transfer.WritePkg(data)
	return
}

// ServerProcessLogin 这个函数专门用来处理登录请求
func (this *UserProcess) ServerProcessLogin(msg *message.Message) (err error) {
	//核心代码
	//先从 message 中取出 message.Data，并直接反序列化成 LoginMessage
	var loginMsg message.LoginMessage
	err = json.Unmarshal([]byte(msg.Data), &loginMsg)
	if err != nil {
		fmt.Println("serverProcessLogin() 反序列化 message.Data 错误: ", err)
		return
	}

	//1. 先声明一个 resultMessage
	var resultMsg message.Message
	resultMsg.Type = message.LoginResultType
	//再声明一个 LoginResultMessage
	var loginResultMsg message.LoginResult

	//2. 验证用户的 userId 和 userPwd 是否正确，通过 redis 数据库
	user, err := model.MyUserDao.Login(loginMsg.UserId, loginMsg.UserPwd)
	if err == nil {
		//登录成功后，将登录成功的用户的 userId 赋值给 this.UserId
		this.userId = user.UserId
		//登录成功后，将登录成功的用户的 process 写入 userMgr 中
		userMgr.AddUpdateOnlineUser(this)
		//将当前在线用户的 id 写入到 LoginResult.UsersId 中
		for id, _ := range userMgr.onlineUser {
			loginResultMsg.UsersId = append(loginResultMsg.UsersId, id)
		}
		//封装 loginResultMsg 中的字段信息
		loginResultMsg.Code = 200
		fmt.Printf("ID 为 %d 的用户 %s 登录成功!\n", user.UserId, user.UserName)
		//当登录成功后，向其他用户发送上线的通知
		this.NotifyOthersOnlineUser(user.UserId)
	} else {
		loginResultMsg.Code = 500 //500 状态码，表示用户不存在
		loginResultMsg.Error = err.Error()
	}

	//3. 序列化 loginResultMsg
	buf, err := json.Marshal(loginResultMsg)
	if err != nil {
		fmt.Println("serverProcessLogin() 序列化 loginResultMsg 错误: ", err)
		return
	}
	//4. 将 buf 封装给 resultMsg
	resultMsg.Data = string(buf)

	//5. 将 resultMsg 序列化后准备发送给客户端
	data, err := json.Marshal(resultMsg)
	if err != nil {
		fmt.Println("serverProcessLogin() 序列化 resultMsg 错误: ", err)
		return
	}

	//6. 发送给客户端（这里的逻辑封装成一个函数: writePkg()）
	transfer := &utils.Transfer{Conn: this.Conn}
	err = transfer.WritePkg(data)
	return
}

// NotifyOthersOnlineUser 此方法用于通知用于 userId 通知所有其他在线用户：我已上线
func (this *UserProcess) NotifyOthersOnlineUser(userId int) {
	//遍历所有的 onlineUser，给其每一个都发送 我已上线 的消息
	for id, userProcess := range userMgr.onlineUser {
		//将自己过滤掉
		if id == userId {
			continue
		}
		//通知其他用户，该用户已经上线
		userProcess.NotifyMeOnline(userId)

	}
}

func (this *UserProcess) NotifyMeOnline(userId int) {
	//封装 Message
	var msg message.Message
	msg.Type = message.NotifyUserStatusMsgType

	//封装 NotifyUserStatusMessage
	var notifyUserStatusMessage message.NotifyUserStatusMessage
	notifyUserStatusMessage.UserId = userId
	notifyUserStatusMessage.Status = message.UserOnline

	//对 NotifyUserStatusMessage 序列化
	data, err := json.Marshal(notifyUserStatusMessage)
	if err != nil {
		fmt.Println("server userProcess NotifyMeOnline() 序列化 NotifyUserStatusMessage 错误: ", err)
		return
	}

	//将 data 赋值给 message
	msg.Data = string(data)

	//将 message 序列化
	data, err = json.Marshal(msg)
	if err != nil {
		fmt.Println("server userProcess NotifyMeOnline() 序列化 Message 错误: ", err)
		return
	}

	//将 data 发送给客户端
	transfer := utils.Transfer{
		Conn: this.Conn,
	}
	err = transfer.WritePkg(data)
	if err != nil {
		fmt.Println("server userProcess NotifyMeOnline() 发送 data 错误: ", err)
	}
}
