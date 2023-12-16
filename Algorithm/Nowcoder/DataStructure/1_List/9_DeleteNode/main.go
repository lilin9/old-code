package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

// PrintList 打印链表
func (this *ListNode) PrintList() {
	//判断链表为空
	if this == nil {
		fmt.Println("链表为空")
		return
	}

	//遍历链表
	temp := this
	for {
		if temp.Next == nil {
			fmt.Printf("%d ", temp.Val)
			break
		}
		fmt.Printf("%d ", temp.Val)
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

	if this.Val != 0 && temp.Next == nil {
		//将值添加到链表末尾
		temp.Next = newNode
	} else {
		temp.Val = newNode.Val
	}
}

//删除链表的节点
func deleteNode(head *ListNode, val int) *ListNode {
	//判断输入不能为空
	if head == nil {
		return nil
	}

	//如果要删除的节点是头节点
	if head.Val == val {
		head = head.Next
		return head
	}

	//遍历链表
	temp := head
	for {
		//快指针指向nil结束循环
		if temp == nil {
			break
		}
		//当找到了要删除的节点
		if temp.Next.Val == val {
			//删除
			temp.Next = temp.Next.Next
			//结束循环
			break
		}
		temp = temp.Next
	}

	return head
}

func main() {
	//定义头节点
	headNode := &ListNode{}

	//生成链表
	headNode.InsertToListTail(&ListNode{Val: 2})
	headNode.InsertToListTail(&ListNode{Val: 3})
	headNode.InsertToListTail(&ListNode{Val: 4})
	headNode.InsertToListTail(&ListNode{Val: 0})
	headNode.InsertToListTail(&ListNode{Val: 1})

	fmt.Print("输入：")
	headNode.PrintList()
	fmt.Println()

	fmt.Print("输出：")
	deleteNode(headNode, 0).PrintList()
}
