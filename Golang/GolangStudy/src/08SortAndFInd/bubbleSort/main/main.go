package main

import (
	"fmt"
)

func main() {
	arr := [10]int{5, 1, 8, 3, 0, 4, 9, 2, 7, 6}

	var count = 0
	for i := 0; i < len(arr)-1; i++ {
		for j := 0; j < len(arr)-1-i; j++ {
			if arr[j] > arr[j+1] {
				temp := arr[j]
				arr[j] = arr[j+1]
				arr[j+1] = temp

				//计算循环次数
				count++
			}
		}
	}

	fmt.Println(arr)
	fmt.Println("count=", count)
}
