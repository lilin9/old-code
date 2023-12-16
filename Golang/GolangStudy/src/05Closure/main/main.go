package main

import (
	"fmt"
	"strings"
)

/*
1、编写一个函数 makeSuffix(suffix string) 可以接受一个文件后缀名（比如.jpg），并返回一个闭包
2、调用闭包，可以传入一个文件名，如果该文件名没有指定的后缀（比如.jpg），则返回 文件名.jpg，如果已经
有 .jpg 后缀，则返回原文件名
3、要求使用闭包的方式完成
*/

func makeSuffix(suffix string) func(suffix string) string {
	return func(fileName string) string {
		//判断文件名是否有指定后缀名
		if strings.HasSuffix(fileName, suffix) {
			return fileName
		}
		return fileName + suffix
	}
}

func main() {
	result := makeSuffix(".jpg")
	fmt.Println(result("log.jpg"))
	fmt.Println(result("log.txt"))
}
