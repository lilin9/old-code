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

// DeleteDuplication 删除链表中重复的节点
func DeleteDuplication(pHead *ListNode) *ListNode {
	//判断链表为空
	if pHead == nil {
		return nil
	}
	//如果链表只有一个节点
	if pHead.Next == nil {
		return pHead
	}

	//创建辅助指针
	deferNode := pHead     //慢指针
	fastNode := pHead.Next //快指针

	//遍历链表
	for {
		//当快指针指向nil的时候结束循环
		if fastNode.Next == nil {
			break
		}
		//判断慢指针是否指向头节点
		if deferNode != pHead {
			//判断快指针指向的当前节点和其下一个节点是否相等
			if fastNode.Val == fastNode.Next.Val {
				//相等，就找到重复节点的结束位置
				fastNode = findDifference(fastNode)
				deferNode.Next = fastNode
			} else {
				//不相等，快慢指针都往后移动
				fastNode = fastNode.Next
				deferNode = deferNode.Next
			}

		} else {
			//指向头节点，判断头节点是否需要删除
			if deferNode.Val == deferNode.Next.Val {
				//找到重复节点的结束位置
				deferNode = findDifference(deferNode)
				//头节点指向结束位置
				pHead = deferNode
				//判断慢指针是否为空
				if deferNode != nil {
					fastNode = deferNode.Next
				} else {
					fastNode = nil
				}
			} else {
				//头节点不需要删除，快慢指针都往后移动
				fastNode = fastNode.Next
				deferNode = deferNode.Next
			}
		}
	}

	return pHead
}

//找到链表中重复节点的结束位置
func findDifference(node *ListNode) *ListNode {
	for {
		if node.Next != nil {
			//如果当前节点不是最后一个节点
			if node.Val == node.Next.Val {
				node = node.Next
			} else {
				break
			}
		} else {
			//如果是最后一个节点，直接返回nil
			return nil
		}
	}
	return node.Next
}

func main() {
	//头节点
	pHead := &ListNode{}

	//生成链表
	pHead.InsertToListTail(&ListNode{Val: 1})
	pHead.InsertToListTail(&ListNode{Val: 2})
	pHead.InsertToListTail(&ListNode{Val: 3})
	pHead.InsertToListTail(&ListNode{Val: 3})
	pHead.InsertToListTail(&ListNode{Val: 4})
	pHead.InsertToListTail(&ListNode{Val: 4})
	pHead.InsertToListTail(&ListNode{Val: 5})

	//打印原链表
	fmt.Println("原来的链表:")
	pHead.PrintList()

	//删除重复节点
	fmt.Println("\n删除重复节点后的链表:")
	DeleteDuplication(pHead).PrintList()
}
