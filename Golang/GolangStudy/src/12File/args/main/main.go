package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Println("命令行参数有：")
	for index, value := range os.Args {
		fmt.Printf("args[%v] = %v\n", index, value)
	}
}
