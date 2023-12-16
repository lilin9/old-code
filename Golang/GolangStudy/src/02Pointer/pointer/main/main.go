package main

// import "fmt"
// import "unsafe"
import (
	"fmt"
)

func main() {
	var num int = 10
	//获取 num 的地址
	fmt.Println("num 的地址: ", &num)

	var ptr *int = &num
	fmt.Println("prt -> ", ptr)
	fmt.Println("ptr 的地址: ", &ptr)
	fmt.Println("ptr 指向的值: ", *ptr)
}
