package main

import (
	"fmt"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

// PrintList 打印链表
func (this *ListNode) PrintList() {
	//判断链表为空
	if this.Val == 0 && this.Next == nil {
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

// FindKthToTailForOne 返回该链表中倒数第k个节点：快慢指针解决
func FindKthToTailForOne(pHead *ListNode, k int) *ListNode {
	//判断链表是否为空
	if pHead == nil {
		return nil
	}

	//判断链表长度是否小于 k
	if GetListLength(pHead) < k {
		return nil
	}

	//定义两个辅助指针
	deferNode := pHead //慢指针
	fastNode := pHead  //快指针

	//快指针先走 k 步
	for i := 1; i < k; i++ {
		fastNode = fastNode.Next
	}

	//然后快慢指针同时开始走
	for {
		//当快指针指向链表最后一个节点时，就结束循环
		if fastNode.Next == nil {
			break
		}
		//快慢指针一起往后走
		deferNode = deferNode.Next
		fastNode = fastNode.Next
	}

	//此时慢指针指向的节点就是目标节点
	return deferNode

}

//定义一个全局变量用来接收找到的目标节点
var resNode *ListNode

// FindKthToTailForTwo 返回该链表中倒数第k个节点：栈解决
func FindKthToTailForTwo(pHead *ListNode, k int) *ListNode {
	temp := pHead
	//判断链表是否为空
	if temp == nil {
		return nil
	}

	//判断链表长度是否小于 k
	if GetListLength(temp) < k {
		return nil
	}

	//找到链表中倒数第k个节点
	GetKthToTail(temp, &k)
	return resNode
}

// GetListLength 求链表长度
func GetListLength(pHead *ListNode) int {
	//判断是不是空链表
	if pHead.Val == 0 && pHead.Next == nil {
		fmt.Println("链表不能为空")
		return -1
	}

	//循环遍历链表
	temp := pHead
	var count int
	for {
		//如果是最后一个节点
		if temp == nil {
			break
		}
		//count 加 1
		count++
		//否则 temp 指向下一个节点
		temp = temp.Next
	}

	return count
}

// GetKthToTail 返回该链表中倒数第k个节点具体实现逻辑
func GetKthToTail(node *ListNode, k *int) {
	//如果node不是最后一个节点，始终递归
	if node.Next != nil {
		GetKthToTail(node.Next, k)
	}

	//当k等于1的时候就说明找到了目标节点
	if *k == 1 {
		resNode = node
	}
	//k不断减1
	*k--
}

func main() {
	//头节点
	pHead := &ListNode{}
	//添加值
	pHead.InsertToListTail(&ListNode{Val: 1})
	pHead.InsertToListTail(&ListNode{Val: 2})
	pHead.InsertToListTail(&ListNode{Val: 3})
	pHead.InsertToListTail(&ListNode{Val: 4})
	pHead.InsertToListTail(&ListNode{Val: 5})
	//打印生成的链表
	fmt.Println("原链表")
	pHead.PrintList()

	fmt.Println("\n\n寻找链表中倒数最后k个结点")
	node := FindKthToTailForOne(pHead, 2)
	if node != nil {
		node.PrintList()
	} else {
		fmt.Println("找不到……")
	}
}
