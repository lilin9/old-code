package main

import (
	"fmt"
	"net"
)

func process(conn net.Conn) {
	//保证使用完 conn 后将其关闭
	defer func() {
		err := conn.Close()
		if err != nil {
			fmt.Println("关闭 conn 出错: ", err)
		}
	}()

	//循环接收客户端的输入
	for {
		buf := make([]byte, 1024)
		//1. 等待客户端通过 conn 发送数据
		//2. 如果客户端没有发送，则协程就会在此阻塞
		//fmt.Printf("服务端正在等待客户端 %s 的数据\n", conn.RemoteAddr().String())
		n, err := conn.Read(buf)
		if err != nil {
			fmt.Println("远程客户端已退出 ")
			return
		}

		//显示客户端发送的数据到终端
		fmt.Print(string(buf[:n]))
	}
}

func main() {
	fmt.Println("服务器开始监听")

	listen, err := net.Listen("tcp", "0.0.0.0:8080")
	//如果监听失败
	if err != nil {
		fmt.Println("监听服务器失败: ", err)
		return
	}
	//保证使用完 listen 后将其关闭
	defer func() {
		err = listen.Close()
		if err != nil {
			fmt.Println("关闭 listen 出错: ", err)
		}
	}()

	//一直监听端口知道接收到请求
	for {
		fmt.Println("服务器端等待连接……")
		conn, err := listen.Accept()

		if err != nil {
			fmt.Println("Accept() 出错: ", err)
		} else {
			fmt.Println("conn=", conn)
		}

		//创建协程，去接受客户端传输的数据
		go process(conn)
	}
}
