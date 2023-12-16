package main

import (
	"fmt"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

// PrintList 打印链表
func PrintList(head *ListNode) {
	//判断链表为空
	if head.Val == 0 && head.Next == nil {
		fmt.Println("链表为空")
		return
	}

	//遍历链表
	temp := head
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
func InsertToListTail(pHead *ListNode, newNode *ListNode) {
	temp := pHead
	for {
		//如果是最后一个节点
		if temp.Next == nil {
			break
		}
		//temp 指向下一个节点
		temp = temp.Next
	}

	if pHead.Val != 0 && temp.Next == nil {
		//将值添加到链表末尾
		temp.Next = newNode
	} else {
		temp.Val = newNode.Val
	}
}

// FindFirstCommonNode1 使用暴力嵌套法（时间复杂度 O(mn)）
func FindFirstCommonNode1(pHead1 *ListNode, pHead2 *ListNode) *ListNode {
	//判断链表为空
	if pHead1 == nil || pHead2 == nil {
		fmt.Println("链表为空")
		return nil
	}

	//定义两个辅助结点
	temp1 := pHead1
	temp2 := pHead2
	//第一个公共结点
	var firstCommon *ListNode = nil

	//遍历 pHead2 链表
	for {
		if temp2 == nil {
			break
		}
		//遍历 pHead1 链表
		for {
			if temp1 == nil {
				//重置 temp1，让 temp1 重新指向头节点
				temp1 = pHead1
				break
			}
			//如果节点值相等，以及它们的下一个节点值也相等
			if temp1.Val == temp2.Val {
				firstCommon = temp2
				return firstCommon
			}
			//没找到继续遍历
			temp1 = temp1.Next
		}
		//temp2 继续遍历
		temp2 = temp2.Next
	}

	return firstCommon
}

// FindFirstCommonNode2 使用快慢指针，让慢指针先走完两个链表的长度差
func FindFirstCommonNode2(pHead1 *ListNode, pHead2 *ListNode) *ListNode {
	//判断链表为空
	if pHead1 == nil || pHead2 == nil {
		fmt.Println("链表为空")
		return nil
	}

	//定义两个辅助结点
	temp1 := pHead1
	temp2 := pHead2
	//第一个公共结点
	var firstCommon *ListNode = nil

	//让两个指针持平
	length := GetListLength(pHead1) - GetListLength(pHead2)
	if length > 0 {
		for i := 0; i < length; i++ {
			temp1 = temp1.Next
		}
	} else {
		for i := 0; i < (-length); i++ {
			temp2 = temp2.Next
		}
	}

	//寻找公共节点
	for {
		if temp1 == nil || temp2 == nil {
			break
		}
		if temp1.Val == temp2.Val {
			firstCommon = temp1
			break
		}

		//往下遍历
		temp1 = temp1.Next
		temp2 = temp2.Next
	}

	return firstCommon
}

// GetListLength 获取链表长度
func GetListLength(head *ListNode) int {
	length := 0
	//遍历链表
	temp := head
	for {
		if temp == nil {
			break
		}
		length++
		temp = temp.Next
	}
	return length
}

func main() {
	//创建头节点
	pHead1 := &ListNode{}
	pHead2 := &ListNode{}
	//var pHead3 *ListNode = nil

	//生成链表
	InsertToListTail(pHead1, &ListNode{Val: 1})
	InsertToListTail(pHead1, &ListNode{Val: 2})
	InsertToListTail(pHead1, &ListNode{Val: 3})
	InsertToListTail(pHead1, &ListNode{Val: 6})
	InsertToListTail(pHead1, &ListNode{Val: 7})

	InsertToListTail(pHead2, &ListNode{Val: 4})
	InsertToListTail(pHead2, &ListNode{Val: 5})
	InsertToListTail(pHead2, &ListNode{Val: 6})
	InsertToListTail(pHead2, &ListNode{Val: 7})

	//打印链表
	fmt.Print("pHead1 = ")
	PrintList(pHead1)
	fmt.Print("\npHead2 = ")
	PrintList(pHead2)

	//查找第一个公共节点
	firstCommon := FindFirstCommonNode2(pHead1, pHead2)
	if firstCommon != nil {
		fmt.Print("\n\n公共链表 = ")
		PrintList(firstCommon)
	} else {
		fmt.Println("\n\npHead1 和 pHead2 没有公共节点")
	}
}
