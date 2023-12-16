package main

import (
	"fmt"
	"strconv"
	"time"
)

//此函数输出 hello world
func helloWorld() {
	for i := 0; i < 10; i++ {
		fmt.Println("hello world " + strconv.Itoa(i))
		//休眠 1 秒
		time.Sleep(time.Second)
	}
}

func main() {
	//开启了一个协程
	go helloWorld()

	//主线程中输出 hello golang
	for i := 0; i < 10; i++ {
		fmt.Println("hello golang " + strconv.Itoa(i))
		//休眠 1 秒
		time.Sleep(time.Second)
	}
}
