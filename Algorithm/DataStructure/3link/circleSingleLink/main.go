package main

import (
	"fmt"
)

type CatNode struct {
	id   int
	name string
	next *CatNode
}

//打印单向环形链表
func showLink(head *CatNode) {
	temp := head

	//判断是不是空链表
	if temp.next == nil {
		fmt.Println("此链表为空……")
		return
	}

	//循环打印环形链表
	for {
		//打印节点的信息
		fmt.Printf("小猫的序号: %d\t小猫的名字: %s\n", temp.id, temp.name)

		//如果遍历到最后一个节点，就结束循环
		if temp.next == head {
			break
		}
		//temp 指向下一个节点
		temp = temp.next
	}
}

//添加节点到单向环形链表尾部
func insertToEnd(head *CatNode, catNode *CatNode) {
	//判断 head 是否为空
	if head.next == nil {
		head.id = catNode.id
		head.name = catNode.name
		head.next = head
		return
	}

	temp := head
	//不为空, 循环遍历环形链表
	for {
		//判断是不是最后一个节点
		if temp.next == head {
			break
		}
		//否则 temp 指向下一个节点
		temp = temp.next
	}

	catNode.next = head
	temp.next = catNode
}

//根据从小到大的顺序将节点添加到链表中
func insertByOrder(head *CatNode, catNode *CatNode) {
	//判断 head 是否为空
	if head.next == nil {
		head.id = catNode.id
		head.name = catNode.name
		head.next = head
		return
	}

	temp := head
	//不为空, 循环遍历环形链表
	for {
		//判断是不是最后一个节点
		if temp.next == head {
			break
		} else if temp.next.id >= catNode.id {
			//判断 temp 的下一个节点的 id 是否大于等于要插入的节点的 id
			//如果是就结束循环
			break
		} else {
			//否则 temp 指向下一个节点
			temp = temp.next
		}
	}
	//将节点添加到链表中
	catNode.next = head
	temp.next = catNode
}

//根据节点的 id 删除节点
func deleteNode(head *CatNode, id int) *CatNode {
	temp := head
	helper := head
	isFind := true

	//判断是不是空链表
	if temp.next == nil {
		fmt.Println("此链表为空……")
		return head
	}

	//如果只有一个节点
	if temp.next == head {
		temp.next = nil
		return head
	}

	//将 helper 定位到环形链表的最后一个节点
	for {
		if helper.next == head {
			break
		}
		helper = helper.next
	}

	//循环遍历链表
	for {
		//如果没有找到节点就结束循环
		if temp.next == head {
			//没有找到
			isFind = false
			break
		} else if temp.id == id {
			//如果当前节点 id 等于传入的 id 说明找到了
			//如果要删除的节点刚好就是头节点
			if temp == head {
				//就将指向头结点的指针指向下一个节点
				head = head.next
			}
			//此时删除节点
			helper.next = temp.next
			fmt.Printf("id=%d 的小猫已被删除\n", id)
			break
		}
		//否则 temp、helper 指向下一个节点
		temp = temp.next
		helper = helper.next

	}

	//如果没有找到目标节点
	if !isFind {
		if temp.id == id {
			//此时删除节点
			helper.next = temp.next
			fmt.Printf("id=%d 的小猫已被删除\n", id)
		} else {
			fmt.Printf("没有找到要删除的 id=%d 的小猫\n", id)
		}
	}
	return head

}

func main() {
	//创建头节点
	head := &CatNode{}

	//创建小猫节点
	whiteCat := &CatNode{
		id:   1,
		name: "whiteCat-smith",
	}
	blackCat := &CatNode{
		id:   2,
		name: "blackCat-tom",
	}
	orangeCat := &CatNode{
		id:   3,
		name: "orangeCat-jenny",
	}
	tiger := &CatNode{
		id:   4,
		name: "tiger-jerry",
	}

	//添加节点
	//insertToEnd(head, whiteCat)
	//insertToEnd(head, blackCat)
	//insertToEnd(head, orangeCat)
	insertByOrder(head, whiteCat)
	insertByOrder(head, orangeCat)
	insertByOrder(head, blackCat)
	insertByOrder(head, tiger)
	//打印链表
	showLink(head)

	fmt.Println()

	//删除节点
	head = deleteNode(head, 1)
	//打印节点
	showLink(head)
}
