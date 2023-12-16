package main

import (
	"errors"
	"fmt"
	"os"
)

type CircleQueue struct {
	maxSize  int //队列最大值
	valueArr []int
	head     int //首指针，默认值是 -1
	tail     int //尾指针，默认值是 -1
}

// AddQueue 加入数据到队列之中
func (this *CircleQueue) AddQueue(val int) (err error) {
	//判断队列是否已满
	if this.isFull() {
		return errors.New("环形队列已满……")
	}

	//添加元素入队列
	this.valueArr[this.tail] = val
	//如果尾指针指向了队列最后一个位置
	//if this.tail == this.maxSize-1 {
	//	this.tail = -1
	//}
	//this.tail++
	this.tail = (this.tail + 1) % this.maxSize

	return
}

// GetQueue 从队列取出数据
func (this *CircleQueue) GetQueue() (val int, err error) {
	//判断队列是否为空
	if this.isEmpty() {
		err = errors.New("环形队列已空……")
		return
	}

	//判断头指针是否指向了队列最后一个位置
	//if this.head == this.maxSize {
	//	this.head = -1
	//}
	//
	//val = this.valueArr[this.head]
	//this.head++

	val = this.valueArr[this.head]
	this.head = (this.head + 1) % this.maxSize

	return
}

// ShowQueue 显示队列
func (this *CircleQueue) ShowQueue() {
	//判断队列是否为空
	if this.getSize() == 0 {
		fmt.Println("环形队列当前的情况是:\n当前环形队列为空")
		return
	}

	fmt.Println("环形队列当前的情况是:")
	tempHead := this.head
	for i := 0; i < this.getSize(); i++ {
		fmt.Printf("queue[%d]=%d\n", tempHead, this.valueArr[tempHead])
		tempHead = (tempHead + 1) % this.maxSize
	}
}

// isFull 判断队列是否已满: true为满, false为未满
func (this *CircleQueue) isFull() (is bool) {
	return (this.tail+1)%this.maxSize == this.head
}

// isEmpty 判断队列是否为空: true为是, false为不是
func (this *CircleQueue) isEmpty() (is bool) {
	return this.tail == this.head
}

// getSize 获取队列的元素个数
func (this *CircleQueue) getSize() int {
	return (this.tail + this.maxSize - this.head) % this.maxSize
}

func main() {
	queue := &CircleQueue{
		maxSize:  4,
		valueArr: make([]int, 4),
		head:     0,
		tail:     0,
	}

	fmt.Println("1 往队列中添加一个值")
	fmt.Println("2 从队列中取出一个值")
	fmt.Println("3 显示队列")
	fmt.Println("4 退出程序")
	for {
		var key int
		var val int
		fmt.Print("请选择(1-4): ")
		_, _ = fmt.Scanln(&key)

		switch key {
		case 1:
			fmt.Print("请输入一个值: ")
			_, _ = fmt.Scanln(&val)
			err := queue.AddQueue(val)
			if err != nil {
				fmt.Println(err.Error())
			} else {
				fmt.Println("添加成功")
			}
		case 2:
			val, err := queue.GetQueue()
			if err != nil {
				fmt.Println(err.Error())
			} else {
				fmt.Printf("取出了一个值: queue[%d]=%d\n", queue.head, val)
			}
		case 3:
			queue.ShowQueue()
		case 4:
			fmt.Println("退出程序……")
			os.Exit(0)
		}
	}
}
