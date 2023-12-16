package main

import "fmt"

func main() {
	str := "helloWorld"
	fmt.Println(str)
	strSlice := []byte(str)
	strSlice[0] = 'g'
	str = string(strSlice)
	fmt.Println(str)
}
