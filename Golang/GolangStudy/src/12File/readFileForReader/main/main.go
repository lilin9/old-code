package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main() {
	//打开一个文件
	file, err := os.Open("D:/Golang/Study/Golang/src/12File/static/text.txt")
	if err != nil {
		fmt.Println("open file error --> ", err)
	}

	//用 defer 保证退出函数时文件的关闭
	defer file.Close()

	//创建一个 reader，reader默认的大小是 4096 个字节
	reader := bufio.NewReader(file)
	//循环读取文件内容
	for {
		str, err := reader.ReadString('\n') //读取到一个换行结束
		if err == io.EOF {                  //io.EOF 表示文件的末尾
			break
		}
		fmt.Println(str)
	}
	fmt.Println("end of file reading")
}
