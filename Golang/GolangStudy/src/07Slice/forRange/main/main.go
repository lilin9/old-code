package main

import "fmt"

func main() {
	var slice = []int{1, 2, 3, 4, 5}

	//for 循环遍历切片
	for i := 0; i < len(slice); i++ {
		fmt.Printf("slice[%v]=%v, ", i, slice[i])
	}
	fmt.Println()
	//for-range 遍历切片
	for index, value := range slice {
		fmt.Printf("slice[%v]=%v, ", index, value)
	}
}
