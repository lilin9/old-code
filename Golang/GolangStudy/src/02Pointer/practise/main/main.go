package main

// import "fmt"
// import "unsafe"
import (
	"fmt"
)

/*
1、写一个程序，获取一个 int 变量 num 的地址，并显示到终端
2、将 num 的地址赋给指针 ptr，并通过 ptr 去修改 num 的值
*/
func main() {
	var num int = 32
	//获取变量的地址
	fmt.Println("num 的地址: ", &num)

	//将 num 的地址赋给指针 ptr
	var ptr *int = &num
	*ptr = 23

	fmt.Println("ptr 的值: ", ptr)
	fmt.Println("num 的值: ", num)
}
