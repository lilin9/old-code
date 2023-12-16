package main

import "fmt"

/*
说明：
请完成 goroutine 和 channel 协同工作的案例，具体要求：
1. 开启一个 writeData 协程，向管道 intChan 中写入 50 个整数
2. 开启一个 readData 协程，从管道 intChan 中读取 writeData 写入的数据
3. 注意：writeData 和 readData 操作的是同一个管道
4. 主线程需要等待 writeData 和 readData 协程都完成才能退出管道
*/

//往管道中写入数据
func writeData(intChan chan int) {
	for i := 1; i <= 50; i++ {
		intChan <- i
	}
	close(intChan)
}

//从管道中读取数据
func readData(intChan chan int, existChan chan bool) {
	for value := range intChan {
		fmt.Printf("value = %d\n", value)
	}

	existChan <- true
	close(existChan)
}

func main() {
	//创建两个管道
	var intChan chan int
	intChan = make(chan int, 50)

	var existChan chan bool
	existChan = make(chan bool, 1)

	//开启两个协程
	go writeData(intChan)
	go readData(intChan, existChan)

	for {
		_, ok := <-existChan
		if !ok {
			break
		}
	}
}
