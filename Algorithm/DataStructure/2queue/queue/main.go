package queue

import (
	"errors"
	"fmt"
	"os"
)

/*
使用数组实现队列的思路：
1. 创建一个数组 array，作为队列的一个字段
2. front 初始化为 -1
3. real 表示队列尾部，初始化为 -1
4. 完成队列的基本查找：
	AddQueue	//加入数据到队列
	GetQueue	//从队列取出数据
	ShowQueue	//显示队列
*/

type Queue struct {
	maxSize  int //队列最大值
	valueArr []int
	front    int //首指针，默认值是 -1
	real     int //尾指针，默认值是 -1
}

// AddQueue 加入数据到队列之中
func (this *Queue) AddQueue(val int) (err error) {
	//判断队列是否已满
	if this.maxSize-1 == this.real {
		err = errors.New("队列已满，无法再添值")
		return
	}
	//尾指针加 1
	this.real++
	//将数据添加进队列
	this.valueArr[this.real] = val
	return
}

// GetQueue 从队列取出数据
func (this *Queue) GetQueue() (val int, err error) {
	//判断队列是否为空
	if this.front == this.real {
		err = errors.New("队列为空，无法再取值")
		return
	}

	this.front++
	val = this.valueArr[this.front]
	return
}

// ShowQueue 显示队列
func (this *Queue) ShowQueue() {
	fmt.Println("队列当前的情况是:")
	//this.front 不包括队列第一个元素
	for i := this.front + 1; i <= this.real; i++ {
		fmt.Printf("queue[%d]=%d\n", i, this.valueArr[i])
	}
}

func main() {
	queue := &Queue{
		maxSize:  4,
		valueArr: make([]int, 4),
		front:    -1,
		real:     -1,
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
				fmt.Printf("取出了一个值: queue[%d]=%d\n", queue.front, val)
			}
		case 3:
			queue.ShowQueue()
		case 4:
			fmt.Println("退出程序……")
			os.Exit(0)
		}
	}
}
