package main

import (
	_ "errors"
	"fmt"
	"strconv"
)

type Stack struct {
	arr    [20]int
	maxTop int //栈最大储存数
	top    int //栈顶指针, 因为栈底固定不变, 所以可以省略
}

//打印栈内数据
func (this *Stack) show() {
	if this.top == -1 {
		fmt.Println("栈为空，无法展示栈数据")
		return
	}

	for i := this.top; i > -1; i-- {
		fmt.Printf("arr[%d]=%d\t", i, this.arr[i])
	}
	fmt.Println()
}

//入栈
func (this *Stack) push(num int) {
	//判断栈是否已满
	if this.top == this.maxTop-1 {
		fmt.Println("堆栈已满")
		return
	}

	//添加数据
	this.top++
	this.arr[this.top] = num
	return
}

//出栈
func (this *Stack) pop() int {
	if this.top == -1 {
		fmt.Println("堆栈为空")
		return 0
	}

	//出栈
	val := this.arr[this.top]
	this.top--
	return val
}

//判断字符是否是一个运算符 [+, -, *, /]
func (this *Stack) isOperator(val int) bool {
	if val == 42 || val == 43 || val == 45 || val == 47 {
		return true
	} else {
		return false
	}
}

// Cal 进行数字的运算
func (this *Stack) Cal(num1, num2, operator int) int {
	res := 0

	switch operator {
	case 42:
		res = num2 * num1
	case 43:
		res = num2 + num1
	case 45:
		res = num2 - num1
	case 47:
		if num1 == 0 {
			fmt.Println("除数不能为 0")
			return 0
		}
		res = num2 / num1
	default:
		fmt.Println("运算符类型不存在")
	}
	return res
}

// Priority 返回运算符优先级
func (this *Stack) Priority(operator int) int {
	res := 0
	if operator == 43 || operator == 45 {
		//如果是 +, - 返回0
		res = 0
	} else if operator == 42 || operator == 47 {
		//如果是 /, * 返回1
		res = 1
	} else {
		//其他情况返回 -1
		res = -1
	}
	return res
}

func main() {
	//此栈用于储存数字
	numStack := Stack{
		top:    -1,
		maxTop: 20,
	}
	//此栈用于储存运算符
	operStack := Stack{
		top:    -1,
		maxTop: 20,
	}

	//需要计算的表达式
	express := "30+2*6-2"
	//定义一个 index 帮助扫描 express
	index := 0

	//定义变量
	topOper := 0
	num1 := 0
	num2 := 0
	res := 0
	oper := 0
	keepNum := ""
	//循环遍历表达式
	for {
		char := express[index : index+1]

		//将字符转成对应的 ASCII 码
		temp := int([]byte(char)[0])
		//如果 char 是运算符
		if operStack.isOperator(temp) {
			//判断 operStack 是不是空栈
			if operStack.top == -1 {
				//是空栈直接入栈
				operStack.push(temp)
			} else {
				//不是空栈, 查看 operStack 的栈顶运算符
				topOper = operStack.arr[operStack.top]
				//如果 topOper 的运算符优先级大于 temp 的优先级
				if operStack.Priority(topOper) >= operStack.Priority(temp) {
					//就从 numStack 中取出两个数
					num1 = numStack.pop()
					num2 = numStack.pop()
					//从 operStack 中取出运算符
					oper = operStack.pop()

					//对num1 num2 进行运算
					res = operStack.Cal(num1, num2, oper)
					//将运算结果重新入栈 numStack
					numStack.push(res)
					//将 temp 入栈 operStack
					operStack.push(temp)
				} else {
					//如果 topOper 的运算符优先级小于 temp 的优先级
					//将运算符 temp 直接入栈
					operStack.push(temp)
				}
			}
		} else {
			//如果 char 是数字

			//处理多位数的思路:
			//1. 定义一个变量 keepNum string, 用来做拼接
			//2. 每次向 index 的后一位字符测试一个, 判断是否是运算符, 然后处理

			//如果后一位字符是数字，就将其与当前字符拼接
			keepNum += char

			//判断是否已经到了表达式的最后一位
			if index == len(express)-1 {
				val, _ := strconv.ParseInt(keepNum, 10, 64)
				numStack.push(int(val))
			} else {
				//判断 index 的后一位字符是否是数字
				if numStack.isOperator(int([]byte(express[index+1 : index+2])[0])) {
					//如果后一位不是数字是字符，直接将当前字符入栈
					val, _ := strconv.ParseInt(keepNum, 10, 64)
					numStack.push(int(val))
					//清空 keepNum
					keepNum = ""
				}
			}
		}

		//如果 index+1 等于了表达式长度, 说明已经遍历完表达式的字符
		//结束循环
		if index+1 == len(express) {
			break
		}
		//遍历表达式下一个字符
		index++
	}

	//遍历 numStack 和 operStack, 计算最终的结果
	for {
		//如果 operStack 为空时, 结束循环
		if operStack.top == -1 {
			break
		}

		num1 = numStack.pop()
		num2 = numStack.pop()
		oper = operStack.pop()

		//对 num1, num2 进行计算
		res = numStack.Cal(num1, num2, oper)
		//将计算结果入栈 numStack
		numStack.push(res)
	}

	//将 numStack 中的最后一个数取出，就是表达式的运算结果
	res = numStack.pop()
	fmt.Printf("%s = %d\n", express, res)

}
