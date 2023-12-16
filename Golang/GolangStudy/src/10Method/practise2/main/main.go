package main

import "fmt"

/*
说明：
1. 编写方法：判断一个数是奇数还是偶数
2. 根据行、列、字符打印对应的行数和列数的字符。比如：行：3，列：2，字符*，则打印相应的效果
3. 定义小小的计算器结构体，实现加减乘除四个功能
实现形式1：分四个方法完成
实现形式2：用一个方法搞定
*/

type MethodUtils struct {
	//运算符
	Operator string
}

func (m *MethodUtils) judge(num int) {
	if num%2 == 0 {
		fmt.Printf("%v 是偶数", num)
	} else {
		fmt.Printf("%v 是奇数", num)
	}
}

func (m *MethodUtils) printf(a int, b int, key string) {
	for i := 0; i < a; i++ {
		for j := 0; j < b; j++ {
			fmt.Print(key)
		}
		fmt.Println()
	}
}

func (m *MethodUtils) calculator(num ...float32) float32 {
	switch (*m).Operator {
	case "+":
		var sum float32
		for _, value := range num {
			sum += value
		}
		return sum
	case "-":
		var sum float32
		for _, value := range num {
			sum -= value
		}
		return sum
	case "*":
		var sum float32
		for i := 0; i < len(num)-1; i++ {
			sum = num[i] * num[i+1]
		}
		return sum
	case "/":
		var sum float32
		for i := 0; i < len(num)-1; i++ {
			sum = num[i] / num[i+1]
		}
		return sum
	default:
		return -1
	}
}

func main() {
	var m MethodUtils
	(&m).judge(11)
	fmt.Println()
	(&m).printf(3, 4, "h")
	fmt.Println()

	//计算器
	(&m).Operator = "*"
	res := (&m).calculator(3, 4)
	if res != -1 {
		fmt.Println("计算结果是：", res)
	} else {
		fmt.Println("计算错误")
	}
}
