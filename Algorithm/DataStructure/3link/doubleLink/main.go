package main

import "fmt"

// DoubleLink 定义一个节点
type DoubleLink struct {
	no   int
	name string
	next *DoubleLink //尾指针
	pre  *DoubleLink //头指针
}

//打印链表信息
func show(headNode *DoubleLink) {
	tempNode := headNode
	//判断是否是空链表
	if tempNode.next == nil {
		fmt.Println("此链表为空……")
		return
	}

	//不是空链表，tempNode 指向下一个节点
	tempNode = tempNode.next
	//循环遍历所有链表
	for {
		//如果是最后一个节点
		if tempNode.next == nil {
			//打印最后的节点信息
			fmt.Printf("排名: %d\t英雄: %s\n", tempNode.no, tempNode.name)
			return
		}
		//如果不是最后一个节点
		//打印当前节点信息
		fmt.Printf("排名: %d\t英雄: %s\n", tempNode.no, tempNode.name)
		//tempNode 指向下一个节点
		tempNode = tempNode.next
	}
}

//往链表里添加节点
func push(headNode, heroNode *DoubleLink) {
	tempNode := headNode
	//循环查找链表的各个节点
	for {
		if tempNode.next == nil {
			//如果这是最后一个节点，就结束循环
			break
		}
		//否则继续查找
		tempNode = tempNode.next
	}

	//让 tempNode 指向要添加的新节点 heroNode
	tempNode.next = heroNode
	heroNode.pre = tempNode
}

//按从小打到的顺序往链表里添加节点 (有序链表)
func pushByOrder(headNode, heroNode *DoubleLink) {
	tempNode := headNode

	//循环查找链表的各个节点
	for {
		//判断是否是最后一个节点
		if tempNode.next == nil {
			break
		} else if heroNode.no <= tempNode.next.no {
			//而且判断 heroNode 的排名是否小于 tempNode.next 的排名
			break
		} else {
			//如果都不是, 让 tempNode 指向下一个节点
			tempNode = tempNode.next
		}
	}

	//heroNode 的前后指针指向 tempNode 节点和 tempNode.next 节点
	heroNode.next = tempNode.next
	heroNode.pre = tempNode
	//tempNode 节点和 tempNode.next 节点的前后指针指向 heroNode
	//判断当前节点是否是最后一个节点
	if tempNode.next != nil {
		tempNode.next.pre = heroNode
	}
	tempNode.next = heroNode

}

//根据 节点的排名(no) 删除节点
func remove(headNode *DoubleLink, no int) {
	tempNode := headNode
	//此标签用于确认是否找到了节点: true 找到; false 没找到
	flag := true

	//查找链表的各个节点
	for {
		//判断是否是最后一个节点
		if tempNode.next == nil {
			flag = false
			break
		} else if no == tempNode.next.no {
			//判断此节点是否是要删除的节点
			break
		} else {
			//如果都不是, 让 tempNode 指向下一个节点
			tempNode = tempNode.next
		}
	}

	//判断是否找到了要删除的节点
	if !flag {
		//没找到
		fmt.Printf("没找到需要删除的 no=%d 的节点……\n", no)
	} else {
		//找到了要删除的节点后，将找到的节点删除
		tempNode.next = tempNode.next.next
		//判断当前节点是否是最后的一个节点
		if tempNode.next != nil {
			tempNode.next.pre = tempNode
		}
		fmt.Printf("删除 no=%d 的节点成功\n", no)
	}
}

func main() {
	//创建头节点
	headLink := &DoubleLink{}

	//创建英雄节点
	captain := &DoubleLink{
		no:   1,
		name: "Captain",
	}
	ironMan := &DoubleLink{
		no:   2,
		name: "IronMan",
	}
	spiderMan := &DoubleLink{
		no:   3,
		name: "SpiderMan",
	}
	thor := &DoubleLink{
		no:   4,
		name: "Thor",
	}

	pushByOrder(headLink, captain)
	pushByOrder(headLink, thor)
	pushByOrder(headLink, ironMan)
	pushByOrder(headLink, spiderMan)

	show(headLink)

	fmt.Println()

	remove(headLink, 2)
	show(headLink)

}
