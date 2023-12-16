package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

// PrintList 打印链表
func (this *ListNode) PrintList() {
	temp := this
	if temp.Next == nil {
		fmt.Println("链表为空")
		return
	}

	temp = temp.Next
	for {
		//说明是最后一个节点
		if temp.Next == nil {
			fmt.Printf("%d]", temp.Val)
			break
		}
		fmt.Printf("%d ", temp.Val)
		//temp 指向下一个节点
		temp = temp.Next
	}
}

// InsertToListTail 添加值到链表的末尾
func (this *ListNode) InsertToListTail(newNode *ListNode) {
	temp := this

	for {
		//如果是最后一个节点
		if temp.Next == nil {
			break
		}
		//temp 指向下一个节点
		temp = temp.Next
	}

	//将值添加到链表末尾
	temp.Next = newNode
}

// ValList 创建一个切片，存放倒序数组
var ValList = make([]int, 0)

// PrintListFromTailToHead 从尾到头打印链表
func PrintListFromTailToHead(head *ListNode) []int {
	temp := head.Next

	//如果不是最后一个节点
	if temp.Next != nil {
		PrintListFromTailToHead(temp)
	}
	ValList = append(ValList, temp.Val)
	return ValList
}

func main() {
	//头节点
	headNode := &ListNode{}

	//生成链表
	node1 := &ListNode{Val: 1}
	node2 := &ListNode{Val: 2}
	node3 := &ListNode{Val: 3}
	headNode.InsertToListTail(node1)
	headNode.InsertToListTail(node2)
	headNode.InsertToListTail(node3)

	//顺序打印链表数据
	fmt.Print("顺序: [")
	headNode.PrintList()

	//逆序打印链表数据
	ValList = PrintListFromTailToHead(headNode)
	fmt.Println("\n倒序:", ValList)
}
