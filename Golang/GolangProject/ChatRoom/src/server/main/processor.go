package main

import (
	"Project/ChatRoom/src/common/message"
	"Project/ChatRoom/src/server/processes"
	"Project/ChatRoom/src/server/utils"
	"fmt"
	"io"
	"net"
)

/*
processor.go(总处理器) 主要处理：
根据客户端的请求，调用相应的处理器，完成对应的任务
*/

type Processor struct {
	Conn net.Conn
}

//根据客户端发送的消息种类不同，来决定调用哪个函数
func (this *Processor) serverProcessMessage(msg *message.Message) (err error) {
	//通过 msg 判断要发送的数据体的类型
	switch msg.Type {
	case message.LoginMessageType:
		//创建 UserProcess 实例
		userProcess := &processes.UserProcess{
			Conn: this.Conn,
		}

		//处理登录逻辑
		err = userProcess.ServerProcessLogin(msg)
	case message.RegisterMessageType:
		//创建 UserProcess 实例
		userProcess := &processes.UserProcess{
			Conn: this.Conn,
		}

		//处理注册逻辑
		err = userProcess.ServerProcessRegister(msg)
	case message.SmsMessageType:
		//转发消息
		smsProcess := &processes.SmsProcess{}
		smsProcess.SendGroupMessage(msg)
	default:
		fmt.Println("消息类型不存在，无法处理……")
	}
	return
}

func (this *Processor) control() (err error) {
	//循环读取客户端发送的消息
	for {
		//创建 Transfer 实例完成读包任务
		transfer := &utils.Transfer{
			Conn: this.Conn,
		}
		//将读取数据包操作，直接封装成一个函数 readPkg()，返回 message、err
		msg, err := transfer.ReadPkg()
		if err != nil {
			if err == io.EOF {
				fmt.Println("客户端已退出连接……")
				return err
			} else {
				fmt.Println("readPkg 错误: ", err)
				return err
			}
		}
		//fmt.Println("读取到的消息体: msg=", msg)
		err = this.serverProcessMessage(&msg)
		if err != nil {
			return err
		}
	}
}
