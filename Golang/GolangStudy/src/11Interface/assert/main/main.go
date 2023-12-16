package main

import "fmt"

func JudgeType(params ...interface{}) {
	for _, param := range params {
		switch param.(type) {
		case bool:
			fmt.Printf("%v 是一个 bool 类型的参数\n", param)
		case int:
			fmt.Printf("%v 是一个 int 类型的参数\n", param)
		case int32:
			fmt.Printf("%v 是一个 int32 类型的参数\n", param)
		case int64:
			fmt.Printf("%v 是一个 int64 类型的参数\n", param)
		case float32:
			fmt.Printf("%v 是一个 float32 类型的参数\n", param)
		case float64:
			fmt.Printf("%v 是一个 float64 类型的参数\n", param)
		case string:
			fmt.Printf("%v 是一个 string 类型的参数\n", param)
		default:
			fmt.Printf("无法判断 %v 的参数类型\n", param)
		}
	}
}

func main() {
	JudgeType(int32(1), int64(8), float32(1.2), float64(3.4), true, "hello")
}
