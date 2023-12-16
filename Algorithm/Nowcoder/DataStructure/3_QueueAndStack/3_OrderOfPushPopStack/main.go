package main

import "fmt"

type Stack struct {
	Values []int
	//指针，指向栈顶元素
	p int
}

// Push 将value压入栈中
func (this Stack) Push(node int) Stack {
	//指针指向栈顶
	this.p += 1
	//将 node 压入栈中
	this.Values[this.p] = node

	return this
}

// Pop 弹出栈顶元素
func (this Stack) Pop() Stack {
	//判断栈是否为空
	if this.p == -1 {
		return this
	}

	//弹出栈顶元素，栈指针指向上一个元素
	this.p -= 1

	return this
}

func IsPopOrder(pushV []int, popV []int) bool {
	//定义一个栈
	stack := Stack{make([]int, 10), -1}

	//获取 pushV 的长度
	length := len(pushV)
	//定义索引 index，指向 pushV第一个元素
	index := 0
	//定义指针 pop，指向 popV 的第一个元素
	pop := 0
	//储存最终结果
	result := false

	for {
		//如果栈的当前元素和 pop 指针指向的值相等
		if stack.p != -1 && stack.Values[stack.p] == popV[pop] {
			//将栈当前元素弹出
			stack = stack.Pop()
			//pop 指针后移
			pop += 1

		} else {
			//将 pushV 数据入栈
			stack = stack.Push(pushV[index])
			//index 指针后移
			index += 1
		}

		//结束循环
		if pop >= length {
			result = true
			break
		}
		if index >= length && stack.Values[stack.p] != popV[pop] {
			break
		}
	}
	return result
}

func main() {
	pushV := []int{1, 2, 3, 4, 5}
	popV := []int{4, 5, 3, 2, 1}
	fmt.Println(IsPopOrder(pushV, popV)) //true

	//pushV := []int{1, 2, 3, 4, 5}
	//popV := []int{4, 3, 5, 1, 2}
	//fmt.Println(IsPopOrder(pushV, popV)) //false

	//pushV := []int{2, 1, 0}
	//popV := []int{1, 2, 0}
	//fmt.Println(IsPopOrder(pushV, popV)) //true
}
