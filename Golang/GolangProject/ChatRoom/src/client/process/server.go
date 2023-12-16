package process

import (
	"Project/ChatRoom/src/client/utils"
	"Project/ChatRoom/src/common/message"
	"encoding/json"
	"fmt"
	"net"
	"os"
)

/*
server.go 功能：
1. 显示登录成功界面
2. 保持和服务器通讯【即启动协程】
3. 当读取到服务器发送的消息后，将其显示在页面
*/

//显示登录成功后的页面
func showMenu() {
	fmt.Println("---------登录成功---------")
	fmt.Println("\t\t1. 显示在线用户列表")
	fmt.Println("\t\t2. 发送消息")
	fmt.Println("\t\t3. 消息列表")
	fmt.Println("\t\t4. 退出系统")
	fmt.Print("请选择（1-4）: ")
	fmt.Println()

	var content string
	var key int
	var smsProcess *SmsProcess

	fmt.Scanf("%d\n", &key)

	switch key {
	case 1:
		//fmt.Println("显示在线用户列表")
		outputOnlineUser()
	case 2:
		//接受输入
		fmt.Println("请输入:")
		fmt.Scanf("%s\n", &content)

		//将消息发送给服务器
		err := smsProcess.SendGroupMes(content)
		if err != nil {
			fmt.Println("发送消息给服务器失败: ", err)
		}
	case 3:
		fmt.Println("消息列表")
	case 4:
		fmt.Println("退出系统……")
		os.Exit(0)
	default:
		fmt.Println("输入有误，请重新输入!")
	}
}

//ServerProcessMessage 此函数负责和函数不停的保持通讯
func ServerProcessMessage(conn net.Conn) {
	//获取 transfer 实例
	transfer := utils.Transfer{
		Conn: conn,
	}

	//不停的循环向服务器读取消息
	for {
		fmt.Println("客户端正在读取服务端消息……")
		msg, err := transfer.ReadPkg()
		if err != nil {
			fmt.Println("client server.go ServerProcessMessage() 错误: ", err)
			return
		}
		//判断消息的类型
		switch msg.Type {
		case message.NotifyUserStatusMsgType:
			//1. 取出 NotifyUserStatusMsg
			var notifyUserStatusMsg message.NotifyUserStatusMessage
			err := json.Unmarshal([]byte(msg.Data), &notifyUserStatusMsg)
			if err != nil {
				fmt.Println("client userProcess ServerProcessMessage() 反序列化失败: ", err)
				return
			}
			//2. 将该用户的信息、状态保存到客户端的 map[int]user 中
			updateUserStatus(&notifyUserStatusMsg)
		case message.SmsMessageType:
			//显示接收到的群发消息
			outputGroupMessage(&msg)
		default:
			fmt.Println("服务器接收到未知的消息类型")
		}
	}
}
