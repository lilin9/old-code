package main

import (
	"fmt"
	"os"
)

func main() {
	file, err := os.Open("D:\\Golang\\Study\\Golang\\src\\12File\\static\\text.txt")
	if err != nil {
		fmt.Println("open file error --> ", err)
	}

	//从输出可以看出 file 是一个指针
	fmt.Println("file --> ", file)
	err = file.Close()
	if err != nil {
		fmt.Println("close file error --> ", err)
	}
}
