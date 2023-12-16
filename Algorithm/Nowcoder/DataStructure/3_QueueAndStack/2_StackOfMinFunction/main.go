package main

import "fmt"

type Stack struct {
	Values []int
	//指针，指向栈顶元素
	p int
}

var stack Stack

// Push 将value压入栈中
func Push(node int) {
	//如果 stack 内容为空，就初始化 stack
	if len(stack.Values) == 0 {
		stack.Values = make([]int, 5)
		//指针初始化为 -1
		stack.p = -1
	}

	//指针指向栈顶
	stack.p += 1
	//将 node 压入栈中
	stack.Values[stack.p] = node
}

// Pop 弹出栈顶元素
func Pop() {
	//判断栈是否为空
	if stack.p == -1 {
		return
	}

	//弹出栈顶元素，栈指针指向上一个元素
	stack.p -= 1
}

// Top 获取栈顶元素
func Top() int {
	//判断栈是否为空
	if stack.p == -1 {
		return 0
	}

	//返回栈顶元素
	return stack.Values[stack.p]
}

// Min 获取栈中最小元素
func Min() int {
	//判断栈是否为空
	if stack.p == -1 {
		return 0
	}

	//获取栈元素的长度
	length := stack.p + 1
	//复制一份栈数据
	values := stack.Values

	//遍历栈中数据，找到最小值
	min := values[0]
	for i := 0; i < length; i++ {
		if min > values[i] {
			min = values[i]
		}
	}
	return min
}

func main() {
	//Push(-1)
	//fmt.Println("栈元素: ", stack.Values) //-1
	//Push(2)
	//fmt.Println("栈元素: ", stack.Values) //2, -1
	//fmt.Println("最小值: ", Min())        //-1
	//fmt.Println("栈顶元素: ", Top())       //2
	//Pop()
	//fmt.Println("弹出栈顶元素后: ", stack.Values) //-1
	//Push(1)
	//fmt.Println("将 1 压入栈中: ", stack.Values) //1, -1
	//fmt.Println("获取栈顶元素: ", Top())          //1
	//fmt.Println("最小值: ", Min())             //-1

	//Push(9)
	//Push(1)
	//Push(6)
	//fmt.Println("最小值:\t", Min()) //1
	//Pop()
	//Pop()
	//fmt.Println("最小值:\t", Min()) //9

	Push(1)
	Push(0)
	Push(-3)
	fmt.Println(stack.Values)
	fmt.Println("最小值:\t", Min()) //-3
	Pop()
	fmt.Println("栈顶值:\t", Top()) //0
	fmt.Println("最小值:\t", Min()) //0
}
