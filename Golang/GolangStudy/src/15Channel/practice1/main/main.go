package main

import "fmt"

type Cat struct {
	Name string
	Age  int
}

func main() {
	//创建 cat 实例
	cat1 := Cat{"lucy", 1}
	cat2 := Cat{"tom", 2}

	//创建 channel
	var myChannel chan interface{}
	myChannel = make(chan interface{}, 10)

	//往 channel 写入数据
	myChannel <- cat1
	myChannel <- cat2
	myChannel <- 10
	myChannel <- "hello"

	//取出数据
	cat3 := <-myChannel
	cat4 := cat3.(Cat)
	fmt.Printf("%T\n", cat3)
	//fmt.Println(cat3.Name)	//error
	fmt.Println(cat4.Name)
}
