package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func copyFile(distFileName string, srcFileName string) (written int64, err error) {
	//打开 srcFileName
	srcFile, err := os.Open(srcFileName)
	if err != nil {
		fmt.Println("open file error -> ", err)
	}
	//关闭句柄
	defer srcFile.Close()
	//获取 reader
	reader := bufio.NewReader(srcFile)

	//打开 distFileName
	distFile, err := os.Open(distFileName)
	if err != nil {
		fmt.Println("open file error -> ", err)
	}
	//关闭句柄
	defer distFile.Close()
	//获取 write
	write := bufio.NewWriter(distFile)

	return io.Copy(write, reader)
}

func main() {
	srcFilePath := "D:\\Golang\\Study\\Golang\\src\\12File\\static\\images\\21.jpg"
	distFilePath := "D:\\Golang\\Study\\Golang\\src\\12File\\static\\1.jpg"

	_, err := copyFile(distFilePath, srcFilePath)
	if err != nil {
		fmt.Println("copy file error -> ", err)
	}
}
