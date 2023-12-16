package main

import (
	"errors"
	"fmt"
)

/*队列实现-start*/

type Queue struct {
	maxSize  int //队列最大值
	valueArr []*TreeNode
	head     int //首指针，默认值是 0
	tail     int //尾指针，默认值是 0
}

// AddQueue 加入数据到队列之中
func (this *Queue) AddQueue(val *TreeNode) (err error) {
	//判断队列是否已满
	if this.isFull() {
		return errors.New("环形队列已满……")
	}

	//添加元素入队列
	this.valueArr[this.tail] = val
	//如果尾指针指向了队列最后一个位置
	this.tail = (this.tail + 1) % this.maxSize

	return
}

// GetQueue 从队列取出数据
func (this *Queue) GetQueue() (val *TreeNode, err error) {
	//判断队列是否为空
	if this.isEmpty() {
		err = errors.New("环形队列已空……")
		return
	}

	//判断头指针是否指向了队列最后一个位置
	val = this.valueArr[this.head]
	this.head = (this.head + 1) % this.maxSize

	return
}

// isFull 判断队列是否已满: true为满, false为未满
func (this *Queue) isFull() (is bool) {
	return (this.tail+1)%this.maxSize == this.head
}

// isEmpty 判断队列是否为空: true为是, false为不是
func (this *Queue) isEmpty() (is bool) {
	return this.tail == this.head
}

// GetSize 获取队列的元素个数
func (this *Queue) GetSize() int {
	return (this.tail + this.maxSize - this.head) % this.maxSize
}

/*队列实现-end*/

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

// Print 之字形打印二叉树-队列实现
func Print(pRoot *TreeNode) [][]int {
	//确保参数不为空
	if pRoot == nil {
		return nil
	}

	//定义一个队列
	treeQueue := &Queue{maxSize: 10, head: 0, tail: 0, valueArr: make([]*TreeNode, 10)}
	//定义一个数组，用来存放所有的值
	allValues := [][]int{}
	//定义一个level，记录遍历的二叉树的层次
	level := 1
	//将根节点加入队列
	_ = treeQueue.AddQueue(pRoot)

	for {
		//只要队列不为空就一直循环
		if treeQueue.head == treeQueue.tail {
			break
		}
		//定义一个数组，用来储存二叉树每一层的节点值
		size := treeQueue.GetSize()
		var row = make([]int, 0)
		//遍历队列
		for i := 0; i < size; i++ {
			//弹出队列里存放的节点
			node, _ := treeQueue.GetQueue()
			//将节点的左右子节点加入队列
			if node.Left != nil {
				_ = treeQueue.AddQueue(node.Left)
			}
			if node.Right != nil {
				_ = treeQueue.AddQueue(node.Right)
			}
			//将当前节点的值存入数组中
			row = append(row, node.Val)
		}
		//判断当前二叉树的层数是奇数还是偶数
		if level%2 == 0 {
			//如果是偶数，将 row 数组反转后存入 allValues 数组
			allValues = append(allValues, reverse(row))
		} else {
			//如果是奇数，直接将 row 数组存入 allValues 数组
			allValues = append(allValues, row)
		}
		//level层数加1
		level++

	}
	return allValues
}

//反转切片函数
func reverse(s []int) []int {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
	return s
}

func main() {
	pRoot := &TreeNode{Val: 1}

	node2 := &TreeNode{Val: 2}
	node3 := &TreeNode{Val: 3}
	node4 := &TreeNode{Val: 4}
	node5 := &TreeNode{Val: 5}

	pRoot.Left = node2
	pRoot.Right = node3
	node2.Left = node4
	node2.Right = node5

	res := Print(pRoot)

	for _, item := range res {
		fmt.Printf("%v ", item)
	}
}
