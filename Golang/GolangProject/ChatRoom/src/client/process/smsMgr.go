package process

import (
	"Project/ChatRoom/src/common/message"
	"encoding/json"
	"fmt"
)

//显示接收到的群发消息
func outputGroupMessage(msg *message.Message) {
	//对 msg 反序列化
	var smsMsg message.SmsMessage
	err := json.Unmarshal([]byte(msg.Data), &smsMsg)
	if err != nil {
		fmt.Println("outputGroupMessage() json.Unmarshal([]byte(msg.Data), &smsMsg) 错误: ", err)
		return
	}

	//将群发消息显示出来
	info := fmt.Sprintf("ID为 %d 的用户发布了消息: %s", smsMsg.UserId, smsMsg.Content)
	fmt.Println(info)
	fmt.Println()
}
