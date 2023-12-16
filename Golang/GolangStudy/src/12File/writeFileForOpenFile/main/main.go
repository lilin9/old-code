package main

import (
	"bufio"
	"fmt"
	"os"
)

func main() {
	filePath := "D:\\Golang\\Study\\Golang\\src\\12File\\static\\text.txt"
	file, err := os.OpenFile(filePath, os.O_WRONLY|os.O_CREATE, 0666)

	if err != nil {
		fmt.Println("open file error -> ", err)
		return
	}

	//关闭文件
	defer file.Close()

	//往文件里面写入字符串
	write := bufio.NewWriter(file)

	for i := 0; i < 5; i++ {
		_, err = write.WriteString("hello world\n")
		if err != nil {
			fmt.Println("write file error -> ", err)
		}
	}

	/*
		因为 write 是带缓存的，因此在调用 writeString 方法的时候，内容会先写入到缓存当中，所以需要调用 Flush 方法，
		将缓存的数据真正写入到文件之中，否则文件中将会没有数据
	*/
	err = write.Flush()
	if err != nil {
		fmt.Println("flush file error -> ", err)
	}
}
