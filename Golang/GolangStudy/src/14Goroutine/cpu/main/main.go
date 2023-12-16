package main

import (
	"fmt"
	"runtime"
)

func main() {
	num := runtime.NumCPU()
	fmt.Println("cpu num: ", num)

	//设置运行的 CPU 个数
	runtime.GOMAXPROCS(num - 1)
	fmt.Println("ok")
}
