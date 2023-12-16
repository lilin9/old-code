package main

import (
	"fmt"
	"sync"
)

var (
	myMap = make(map[int]int)
	//定义一个全局变量，类型是 互斥锁 类型
	lock sync.Mutex
)

//这个函数用来计算数的阶乘
func test(n int) {
	res := 1
	//计算阶乘
	for i := 1; i <= n; i++ {
		res *= i
	}

	//在写操作之前进行加锁
	lock.Lock()
	//将计算结果保存到 map 中
	myMap[n] = res
	//在写操作结束后进行解锁
	lock.Unlock()
}

func main() {
	//开启 200 个协程进行计算
	for i := 1; i <= 200; i++ {
		go test(i)
	}

	//将打印操作设置到程序结束时运行
	//保证所有的计算操作完成
	defer func() {
		//打印计算结果
		lock.Lock()
		for index, value := range myMap {
			fmt.Printf("map[%d] = %d\n", index, value)
		}
		lock.Unlock()
	}()
}
