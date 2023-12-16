package main

import "fmt"

func main() {
	var arr [3]int
	fmt.Printf("arr %p", &arr)
	fmt.Println("\narr[0] ", &arr[0])
	fmt.Println("arr[1] ", &arr[1])
}
