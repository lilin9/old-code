package main

import "fmt"

func main() {
	fmt.Println("input 1")
	fmt.Println("input 2")
	goto label
	fmt.Println("input 3")
	fmt.Println("input 4")
	label:
	fmt.Println("input 5")
	fmt.Println("input 6")
}