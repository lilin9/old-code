package main

import (
	"errors"
	"fmt"
)

type Stack struct {
	arr    [5]int
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
func (this *Stack) push(num int) (err error) {
	//判断栈是否已满
	if this.top == this.maxTop-1 {
		return errors.New("堆栈已满")
	}

	//添加数据
	this.top++
	this.arr[this.top] = num
	return
}

//出栈
func (this *Stack) pop() (val int, err error) {
	if this.top == -1 {
		err = errors.New("堆栈为空")
		return
	}

	//出栈
	val = this.arr[this.top]
	this.top--
	return
}

func main() {
	//初始化栈
	stack := &Stack{
		maxTop: 5,
		top:    -1,
	}

	fmt.Println("入栈: ")
	//往栈里面添加值
	for i := 10; i <= 50; i = i + 10 {
		err := stack.push(i)
		if err != nil {
			fmt.Printf("往栈内添加数字 %d 时出现错误: %s\n", i, err)
		}
	}
	//展示栈数据
	stack.show()

	fmt.Println("\n出栈: ")
	//出栈
	for i := 10; i <= 50; i = i + 10 {
		if stack.top != -1 {
			//展示栈数据
			stack.show()
		}

		_, err := stack.pop()
		if err != nil {
			fmt.Printf("将数字 %d 出栈时出现错误: %s\n", i, err)
			continue
		}
	}
}
