package main

import (
	"Project/ChatRoom/src/server/model"
	"fmt"
	"net"
	"time"
)

/*
main.go 主要处理：
1. 监听
2. 等待客户端的连接
3. 初始化的工作
*/

func init() {
	//在程序启动时初始化 redis 连接池
	initPool(16, 0, 300*time.Second, "192.168.200.129:6379")
	initUserDao()
}

func main() {
	//给出提示信息
	fmt.Println("服务器在 8889 端口上监听……")

	//监听端口
	listen, err := net.Listen("tcp", "0.0.0.0:8889")
	if err != nil {
		fmt.Println("监听 8889 端口出错: ", err)
		return
	}

	//延时关闭 listen
	defer func() {
		err = listen.Close()
		if err != nil {
			fmt.Println("延时关闭 listen 错误: ", err)
		}
	}()

	//循环监听客户端的连接
	for {
		fmt.Println("等待客户端的连接……")
		conn, err := listen.Accept()
		if err != nil {
			fmt.Println("客户端连接错误: ", err)
		}

		//连接成功，为连接成功的客户端开启一个协程
		go control(conn)
	}
}

//此函数用来初始化 userDao
func initUserDao() {
	model.MyUserDao = model.NewUserDao(pool)
}

//此函数处理与客户端的通讯操作
func control(conn net.Conn) {
	//延时关闭 conn
	defer func() {
		err := conn.Close()
		if err != nil {
			fmt.Println("延时关闭 processes conn 错误: ", err)
		}
	}()

	//创建 总控 并调用
	processor := &Processor{
		Conn: conn,
	}
	err := processor.control()
	if err != nil {
		fmt.Println("服务端和客户端协程错误: ", err)
		return
	}
}
