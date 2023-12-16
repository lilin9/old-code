package main

import "fmt"

func main() {
	num := 10
	num1 := new(int)

	fmt.Printf("num的类型 %T，num的值 %v，num的地址 %v\n", num, num, &num)
	fmt.Printf("num1的类型 %T，num1的值 %v，num1的地址 %v", num1, num1, &num1)
}
