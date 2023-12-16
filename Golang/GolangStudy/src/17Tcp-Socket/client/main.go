package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
	"strings"
)

func main() {
	conn, err := net.Dial("tcp", "192.168.0.8:8080")
	if err != nil {
		fmt.Println("连接失败: ", err)
		return
	}

	for {
		//从客户端发送单行数据，然后退出
		reader := bufio.NewReader(os.Stdin) //os.Stdin 代表标准输入（终端）

		//从终端读取一行输入
		line, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println("reader.ReadString error: ", err)
		}

		//如果输入 exit 就退出输入
		line = strings.Trim(line, " \r\n")
		if line == "exit" {
			fmt.Println("客户端退出……")
			return
		}

		//将获取的输入发送给服务器
		_, err = conn.Write([]byte(line + "\n"))
		if err != nil {
			fmt.Println("conn.Write error: ", err)
		}
	}
}
