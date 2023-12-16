package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

// PrintList 打印链表
func PrintList(pHead *ListNode) {
	temp := pHead
	if temp.Val == 0 {
		fmt.Println("链表为空")
		return
	}

	fmt.Print("[")
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
func InsertToListTail(pHead *ListNode, newNode *ListNode) {
	//判断是否是空链表
	if pHead.Val == 0 {
		pHead.Val = newNode.Val
		return
	}

	temp := pHead
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

// ReverseList 反转链表
func ReverseList(pHead *ListNode) *ListNode {
	//判断链表是否为空
	if pHead == nil || pHead.Val == 0 {
		fmt.Println("{}") //说明链表为空
		return nil
	}

	//如果链表只有一个节点
	if pHead.Next == nil {
		fmt.Printf("{%d}", pHead.Val)
		return pHead
	}

	//定义三个指针
	var pre *ListNode
	middle := pHead
	end := pHead.Next

	//遍历链表
	for {
		//当 middle 指针指向最后一个节点时
		if end == nil {
			break
		}

		//pre、middle、end三个指针分别指向后一个节点
		pre = middle
		middle = end
		end = end.Next

		//让middle指针指向的节点指向它的前一个节点，即 pre 指针指向的节点
		middle.Next = pre
	}

	//将反转后的链表的最后节点指向空
	pHead.Next = nil

	//结束循环后，将头节点返回，即middle指针指向的节点
	return middle
}

func main() {
	//头指针
	headNode := &ListNode{}
	//值节点
	node1 := &ListNode{Val: 1}
	node2 := &ListNode{Val: 2}
	node3 := &ListNode{Val: 3}
	node4 := &ListNode{Val: 4}

	//创建正序链表
	InsertToListTail(headNode, node1)
	InsertToListTail(headNode, node2)
	InsertToListTail(headNode, node3)
	InsertToListTail(headNode, node4)
	//打印链表
	fmt.Print("正序链表: ")
	PrintList(headNode)

	//反转链表
	headNode = ReverseList(headNode)
	//打印链表
	fmt.Print("\n反序链表: ")
	PrintList(headNode)
}
