package main

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

// PrintList 打印链表
func PrintList(head *ListNode) {
	//判断链表为空
	if head == nil || head.Next == nil {
		fmt.Println("链表为空")
		return
	}

	//遍历链表
	temp := head.Next
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

	//将值添加到链表末尾
	temp.Next = newNode
}

// Merge 合并两个排序的链表
func Merge(pHead1 *ListNode, pHead2 *ListNode) *ListNode {
	//判断链表是否为 nil
	if pHead1 == nil && pHead2 == nil {
		fmt.Println("链表不能为空")
		return nil
	}

	//定义一个存放比较结果的链表
	resultHead := &ListNode{}
	//调用 Judge 方法合并排序链表
	Judge(pHead1.Next, pHead2.Next, resultHead)

	return resultHead
}

// Judge 这个方法用来判断节点的大小
func Judge(node1, node2, endNode *ListNode) {
	//当有一个链表遍历完后就结束递归
	if node1 != nil || node2 != nil {
		//定义辅助节点
		temp1 := node1.Next
		temp2 := node2.Next

		//判断 node1 和 node2 的大小
		if node1.Val <= node2.Val {
			endNode.Next = node1
			endNode.Next.Next = node2
		} else {
			endNode.Next = node2
			endNode.Next.Next = node1
		}
		//node 节点往后移
		node1 = temp1
		node2 = temp2
		//endNode 后移两位，始终指向最后一个节点
		endNode = endNode.Next.Next

		//递归调用
		Judge(node1, node2, endNode)
	}
}

func main() {
	//定义两个头节点
	pHead1 := &ListNode{}
	pHead2 := &ListNode{}

	node1 := &ListNode{Val: -1}
	node2 := &ListNode{Val: 1}
	node3 := &ListNode{Val: 2}
	node4 := &ListNode{Val: 3}
	node5 := &ListNode{Val: 4}
	node6 := &ListNode{Val: 4}

	//生成链表 pHead1
	InsertToListTail(pHead1, node1)
	InsertToListTail(pHead1, node3)
	InsertToListTail(pHead1, node5)
	//生成链表 pHead2
	InsertToListTail(pHead2, node2)
	InsertToListTail(pHead2, node4)
	InsertToListTail(pHead2, node6)

	//打印链表
	fmt.Printf("pHead1 = ")
	PrintList(pHead1)
	fmt.Printf("\npHead2 = ")
	PrintList(pHead2)

	//合并两个有序链表
	resultHead := Merge(pHead1, pHead2)
	//打印链表
	fmt.Printf("\nresultHead = ")
	PrintList(resultHead)
}
