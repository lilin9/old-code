package main

import "fmt"

func main() {
	var intArr = [...]int{1, 2, 3, 4, 5, 6, 7, 8, 9}
	sliceArr := intArr[1:4]
	fmt.Println("intArr = ", intArr)
	fmt.Println("sliceArr 的元素是 ", sliceArr)
	fmt.Println("sliceArr 的元素个数是 ", len(sliceArr))
	fmt.Println("sliceArr 的容量是 ", cap(sliceArr))
}
