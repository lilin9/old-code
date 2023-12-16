package main

import (
	"flag"
	"fmt"
)

func main() {
	//定义参数
	var user string
	var pwd string
	var host string
	var port int

	//设置获取参数规则
	//flag.StringVar(参数值, 指定参数, 默认值, 说明注释)
	flag.StringVar(&user, "u", "", "用户名，默认为空")
	flag.StringVar(&pwd, "pwd", "", "密码，默认为空")
	flag.StringVar(&host, "h", "localhost", "主机名，默认为localhost")
	flag.IntVar(&port, "port", 3306, "端口号，默认为3306")

	//这个操作十分重要，必须调用该方法
	flag.Parse() //转换
	fmt.Printf("user = %v\npwd = %v\nhost = %v\nport = %v\n",
		user, pwd, host, port)
}
