package main

import (
	"Project/ChatRoom/src/client/process"
	"fmt"
	"os"
)

/*
main.go 处理：
1. 显示第一级菜单
2. 根据用户的输入去调用对应的处理器
*/

//UserId 全局变量：用户id
var UserId int

//UserPwd 全局变量：用户密码
var UserPwd string

//UserName 全局变量：用户名
var UserName string

func main() {
	//接收用户选择
	var key int
	for {
		fmt.Println("---------欢迎登录多人聊天系统---------")
		fmt.Println("\t\t1. 登录聊天系统")
		fmt.Println("\t\t2. 注册用户")
		fmt.Println("\t\t3. 退出系统")
		fmt.Print("请选择（1-3）: ")
		fmt.Println()

		fmt.Scanf("%d\n", &key)
		switch key {
		case 1:
			fmt.Println("登录聊天系统")

			//提示用户输入id和密码
			fmt.Println("输入用户ID")
			fmt.Scanf("%d\n", &UserId)
			fmt.Println("输入用户密码")
			fmt.Scanf("%s\n", &UserPwd)

			//实现登录功能
			//1. 创建 UserProcess 实例
			userProcess := &process.UserProcess{}
			err := userProcess.Login(UserId, UserPwd)
			if err != nil {
				fmt.Println("登录失败，请重新登录")
			}
		case 2:
			fmt.Println("注册用户")
			fmt.Println("输入用户ID")
			fmt.Scanf("%d\n", &UserId)
			fmt.Println("输入用户密码")
			fmt.Scanf("%s\n", &UserPwd)
			fmt.Println("输入用户名")
			fmt.Scanf("%s\n", &UserName)

			//实现注册功能
			//1. 创建 UserProcess 实例
			userProcess := &process.UserProcess{}
			err := userProcess.Register(UserId, UserName, UserPwd)
			if err != nil {
				fmt.Println("注册失败，请重新注册")
			}
		case 3:
			fmt.Println("退出系统……")
			os.Exit(0)
		default:
			fmt.Println("输入有误，请重新输入!")
		}
	}
}
