package main

import "fmt"

/*
说明：要求统计 1-8000 的数字中，哪些是素数？
*/

//判断是否是素数
func isPrime(num int) bool {
	//从2遍历到n-1，看看是否有因子
	for i := 2; i < num; i++ {
		if num%i == 0 {
			//发现一个因子被整除
			return false
		}
	}
	return true
}

//往 intChan 写入数字
func writeNum(intChan chan int) {
	for i := 1; i <= 8000; i++ {
		intChan <- i
	}
	close(intChan)
}

//从 intChan 中取出数字并计算是否是素数，是，就将其写入 primeChan
func judgePrime(intChan chan int, primeChan chan int, existChan chan bool) {
	for {
		num, ok := <-intChan
		//取完退出循环
		if !ok {
			break
		}

		//判断是否是素数
		if isPrime(num) {
			//如果是就写入管道
			primeChan <- num
		}
	}
	//所有工作完成后往 existChan 写入一个标志
	existChan <- true

	fmt.Println("有一个 judgePrime 结束了工作")
}

func main() {
	//存放 1-8000 内的数字
	intChan := make(chan int, 2000)
	//存放 1-8000 内的素数
	primeChan := make(chan int, 1000)
	//存放每个协程是否结束的标志
	existChan := make(chan bool, 4)

	//将 1-8000 的数写入管道
	go writeNum(intChan)

	//开启4个 judgePrime 去读取并判断 1-8000 之内的素数，
	//再将判断出来的素数写入 primeChan
	for i := 0; i < 4; i++ {
		go judgePrime(intChan, primeChan, existChan)
	}

	go func() {
		//判断4个 judgePrime 是否全部写入完成
		for i := 0; i < 4; i++ {
			<-existChan
		}

		//当4个 judgePrime 全部写入成功后，就关闭管道
		close(primeChan)
	}()

	//将 primeChan 内的素数读取出来
	for {
		res, ok := <-primeChan
		if !ok {
			break
		}
		fmt.Println("1-8000 以内的素数有：", res)
	}
}
