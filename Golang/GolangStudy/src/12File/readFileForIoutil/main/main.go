package main

import (
	"fmt"
	"io/ioutil"
)

func main() {
	filePath := "D:\\Golang\\Study\\Golang\\src\\12File\\static\\text.txt"
	//一次性读取文件
	content, err := ioutil.ReadFile(filePath)

	if err != nil {
		fmt.Println("err --> ", err)
	}

	fmt.Println(string(content))
}
