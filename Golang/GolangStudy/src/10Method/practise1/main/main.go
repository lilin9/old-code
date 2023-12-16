package main

import "fmt"

/*
说明：
1.编写结构体（MethodUtils）编程一个方法，方法不需要参数，在方法中打印一个 10*8 的矩形，在main方法中
调用该方法
2.编写一个方法，提供m和n两个参数，方法中打印一个m*n的矩形
3.编写一个方法，计算该矩形的面积（可以接受长len，宽width），将其作为方法返回值，在main方法中调用该方法，
接受返回的面积并打印
*/

type MethodUtils struct {
	Len   float32
	Width float32
}

func (m *MethodUtils) printRect() {
	for i := 0; i < 10; i++ {
		fmt.Println()
		for j := 0; j < 8; j++ {
			fmt.Print("*")
		}
	}
}

func (m *MethodUtils) printRect1(a int, b int) {
	for i := 0; i < b; i++ {
		fmt.Println()
		for j := 0; j < a; j++ {
			fmt.Print("*")
		}
	}
}

func (m *MethodUtils) getRectArea() float32 {
	return (*m).Len * (*m).Width
}

func main() {
	m := MethodUtils{}
	m.printRect()
	fmt.Println()
	m.printRect1(3, 2)

	//计算矩形面积
	m.Len = 10.0
	m.Width = 5.0
	res := (&m).getRectArea()
	fmt.Println("\n面积：", res)
}
