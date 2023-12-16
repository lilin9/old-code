package process

import (
	"Project/ChatRoom/src/client/utils"
	"Project/ChatRoom/src/common/message"
	"encoding/json"
	"fmt"
)

/*
smsProcess.go 功能：
1. 处理和短消息相关的逻辑
2. 私聊
3. 群发
*/

type SmsProcess struct {
}

// SendGroupMes 发送群聊信息
func (this *SmsProcess) SendGroupMes(content string) (err error) {
	//创建 message 实例
	var msg message.Message
	msg.Type = message.SmsMessageType

	//创建 SmsMessage 实例
	var smsMsg message.SmsMessage
	smsMsg.Content = content
	smsMsg.UserId = curUser.UserId
	smsMsg.UserStatus = curUser.UserStatus

	//序列化 smsMessage
	data, err := json.Marshal(smsMsg)
	if err != nil {
		fmt.Println("json.Marshal(&smsMsg) 错误: ", err)
		return
	}

	//将 smsMsg 赋值给 msg
	msg.Data = string(data)

	//序列化 msg
	data, err = json.Marshal(msg)
	if err != nil {
		fmt.Println("json.Marshal(msg) 错误: ", err)
		return
	}

	//将 msg 序列化后的结果发送给给服务器
	transfer := &utils.Transfer{
		Conn: curUser.Conn,
	}
	err = transfer.WritePkg(data)
	if err != nil {
		fmt.Println("transfer.WritePkg(data) 错误: ", err)
		return
	}
	return
}
