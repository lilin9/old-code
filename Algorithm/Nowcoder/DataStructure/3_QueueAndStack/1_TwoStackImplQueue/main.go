package main

import "fmt"

//stack1 用来存放数据
var stack1 []int

//stack2 用来作为取数据的中转站
var stack2 []int

//指针，指向两个栈的数据最顶层
var len1 = 0
var len2 = 0

func Push(node int) {
	//将 node 存入 stack1
	stack1 = append(stack1, node)
	len1 += 1
}

func Pop() int {
	//转移 stack1 数据至 stack2
	for i := 1; i <= len1; i++ {
		stack2 = append(stack2, stack1[len1-i])
		len2 += 1
	}

	node := stack2[len2-1]
	len2 -= 1
	return node
}

func main() {
	Push(2)
	fmt.Printf("%d ", Pop())
	Push(1)
	fmt.Printf("%d ", Pop())
}
