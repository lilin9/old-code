package main

import (
	"fmt"
	"strings"
)

type Stack struct {
	Values []string
	//指针，指向栈顶元素
	p int
}

var stack = Stack{Values: make([]string, 10), p: -1}

// Push 将value压入栈中
func Push(node string) {
	//指针指向栈顶
	stack.p += 1
	//将 node 压入栈中
	stack.Values[stack.p] = node
}

// Pop 弹出栈顶元素
func Pop() string {
	//判断栈是否为空
	if stack.p == -1 {
		return ""
	}

	//弹出栈顶元素
	node := stack.Values[stack.p]
	//栈指针指向上一个元素
	stack.p -= 1

	return node
}

func ReverseSentence(str string) string {
	//判断参数为空
	if str == "" {
		return ""
	}

	//将字符串切割成数组
	strArr := strings.Split(str, " ")
	//取得数组长度
	length := len(strArr)

	//遍历数组，将其放入栈中
	for _, item := range strArr {
		Push(item)
	}

	//从栈中取得元素，并拼接
	result := ""
	for i := 0; i < length; i++ {
		result += Pop() + " "
	}
	return strings.TrimSpace(result)
}

func main() {
	//str := "nowcoder. a am I"
	str := ""

	fmt.Println(ReverseSentence(str))
}
