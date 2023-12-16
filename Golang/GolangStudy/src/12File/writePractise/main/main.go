package main

import (
	"fmt"
	"io/ioutil"
	"os"
)

/*
说明：编写一个程序，将一个文件的内容，写入到另外一个文件中（两个文件已经存在）
注意：使用 ioutil.ReadFile / ioutil.WriteFile 完成写文件的任务
*/

//PathExists 判断文件或者目录是否存在
func PathExists(path string) (bool, error) {
	_, err := os.Stat(path)
	//文件或者目录存在
	if err == nil {
		return true, nil
	}
	//文件或者目录不存在
	if os.IsNotExist(err) {
		return false, nil
	}
	return false, err
}

func main() {
	//1.首先将 text.txt 的内容读取到内存中
	//2.将读取到的内容写入到 text2.txt
	filePath := "D:\\Golang\\Study\\Golang\\src\\12File\\static\\text.txt"
	file1Path := "D:\\Golang\\Study\\Golang\\src\\12File\\static\\text2.txt"

	data, err := ioutil.ReadFile(filePath)
	if err != nil {
		fmt.Println("read file error -> ", err)
		return
	}

	//判断要写入的文件或目录是否存在
	if exist, _ := PathExists(file1Path); !exist {
		fmt.Println("要写入的文件不存在")
		return
	}

	err = ioutil.WriteFile(file1Path, data, 0666)
	if err != nil {
		fmt.Println("write file error -> ", err)
		return
	}
	fmt.Println("操作成功")
}
