package main

import "fmt"

type RandomListNode struct {
	Label  int
	Next   *RandomListNode
	Random *RandomListNode
}

// PrintList 打印链表
func (this *RandomListNode) PrintList() {
	//判断链表为空
	if this.Label == 0 && this.Next == nil {
		fmt.Println("链表为空")
		return
	}

	//遍历链表
	temp := this
	for {
		if temp.Next == nil {
			fmt.Printf("%d ", temp.Label)
			break
		}
		fmt.Printf("%d ", temp.Label)
		temp = temp.Next
	}
}

// InsertToListTail 添加值到链表的末尾
func (this *RandomListNode) InsertToListTail(newNode *RandomListNode) {
	temp := this
	for {
		//如果是最后一个节点
		if temp.Next == nil {
			break
		}
		//temp 指向下一个节点
		temp = temp.Next
	}

	if this.Label != 0 && temp.Next == nil {
		//将值添加到链表末尾
		temp.Next = newNode
	} else {
		temp.Label = newNode.Label
	}
}

// Clone 复杂链表的复制
func Clone(head *RandomListNode) *RandomListNode {
	//判断链表是否为空
	if head == nil {
		return nil
	}

	//如果链表只有一个节点
	if head.Next == nil {
		return head
	}

	//将链表每个节点都复制一个
	temp := head
	for {
		//如果 temp 指向 nil 结束循环
		if temp == nil {
			break
		}
		//复制当前节点
		newNode := *temp
		//将复制出来的节点加入到链表中
		newNode.Next = temp.Next
		temp.Next = &newNode

		//temp 指向下一个节点
		temp = temp.Next.Next
	}

	//让复制的节点的Random指针指向正确的节点
	temp = head.Next
	for {
		//如果 temp 指向 nil 结束循环
		if temp.Next == nil {
			break
		}
		//修改被复制出来的节点的 Random 指针指向
		if temp.Random != nil {
			temp.Random = temp.Random.Next
		}

		//temp 指针往后移
		temp = temp.Next.Next
	}

	//将复制出来的节点提取出来
	newHead := head.Next
	temp = head.Next
	for {
		//如果是最后一个节点，就退出循环
		if temp.Next == nil {
			break
		}

		//提取出复制的节点
		temp.Next = temp.Next.Next
		//temp 指针往后移
		temp = temp.Next
	}

	return newHead
}

func main() {
	//头指针
	headNode := &RandomListNode{Label: 1}

	//生成链表
	node2 := &RandomListNode{Label: 2}
	node3 := &RandomListNode{Label: 3}
	node4 := &RandomListNode{Label: 4}
	node5 := &RandomListNode{Label: 5}

	headNode.Next = node2
	node2.Next = node3
	node3.Next = node4
	node4.Next = node5

	//将普通链表修改为复杂链表
	headNode.Random = node3
	node2.Random = node5
	node3.Random = nil
	node4.Random = node2

	//打印链表
	fmt.Println("原来的链表:")
	headNode.PrintList()

	//复制链表
	fmt.Println("\n复制的链表:")
	newHead := Clone(headNode)
	newHead.PrintList()

}
