package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

// EntryNodeOfLoop 求出有环链表的环的入口结点
func EntryNodeOfLoop(pHead *ListNode) *ListNode {
	//判断链表是否为空，以及判断是否只有一个结点但是无环
	if pHead == nil || pHead.Next == nil {
		return nil
	}

	//如果只有一个结点而且有环
	if pHead.Next == pHead {
		return pHead
	}

	//循环遍历链表
	temp := pHead
	var entryNode *ListNode = nil
	for {
		if temp.Next == nil {
			break
		} else if temp.Next.Val < temp.Val {
			entryNode = temp.Next
			break
		}
		temp = temp.Next
	}
	return entryNode
}

func main() {
	//头结点
	pHead := &ListNode{Val: 1}
	//pHead.Next = pHead

	//结点值
	node2 := &ListNode{Val: 2}
	node3 := &ListNode{Val: 3}
	node4 := &ListNode{Val: 4}
	node5 := &ListNode{Val: 5}

	//创建环形结点
	pHead.Next = node2
	node2.Next = node3
	node3.Next = node4
	node4.Next = node5
	node5.Next = node3

	//求出有环链表的入口结点
	entrynode := EntryNodeOfLoop(pHead)
	if entrynode != nil {
		fmt.Printf("入口节点: [Label:%d, Next:%d]\n", entrynode.Val, entrynode.Next)
	} else {
		fmt.Println("入口节点: nil")
	}
}
