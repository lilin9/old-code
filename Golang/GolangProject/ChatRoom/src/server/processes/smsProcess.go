package processes

import (
	"Project/ChatRoom/src/common/message"
	"Project/ChatRoom/src/server/utils"
	"encoding/json"
	"fmt"
	"net"
)

/*
smsProcess.go 主要处理：
1. 处理和短消息相关的请求
2. 群聊调控
3. 点对点聊天调控
*/

type SmsProcess struct {
}

// SendGroupMessage 此方法用于转发消息
func (this *SmsProcess) SendGroupMessage(msg *message.Message) {
	//取出 msg 的消息内容
	var smsMsg message.SmsMessage
	err := json.Unmarshal([]byte(msg.Data), &smsMsg)
	if err != nil {
		fmt.Println("json.Unmarshal([]byte(msg.Data), &smsMsg) 错误: ", err)
		return
	}

	//序列化 msg
	data, err := json.Marshal(msg)
	if err != nil {
		fmt.Println("json.Marshal(msg) 错误: ", err)
		return
	}

	//遍历用户的 onlineUser
	for id, userProcess := range userMgr.onlineUser {
		//过滤掉自己
		if id == smsMsg.UserId {
			continue
		}
		//给其他每个用户发送消息
		this.sendMsgToEachOnlineUser(data, userProcess.Conn)
	}
}

// sendMsgToEachOnlineUser 给其他每个用户发送消息
func (this *SmsProcess) sendMsgToEachOnlineUser(data []byte, conn net.Conn) {
	//创建 transfer 实例
	transfer := utils.Transfer{
		Conn: conn,
	}
	//发送数据
	err := transfer.WritePkg(data)
	if err != nil {
		fmt.Println("transfer.WritePkg(data) 错误: ", err)
	}
}
