package main

import (
	"fmt"
)

// HeroNode 定义一个节点
type HeroNode struct {
	no   int
	name string
	next *HeroNode
}

//往链表里添加节点 (无序链表)
func push(headNode, heroNode *HeroNode) {
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
}

//按从小打到的顺序往链表里添加节点 (有序链表)
func pushByOrder(headNode, heroNode *HeroNode) {
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

	//heroNode 的 next 指针指向 tempNode 的下一个节点
	heroNode.next = tempNode.next
	//tempNode 的 next 指针指向 heroNode 节点
	tempNode.next = heroNode

}

//根据 节点的排名(no) 删除节点
func remove(headNode *HeroNode, no int) {
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
		fmt.Printf("删除 no=%d 的节点成功\n", no)
	}
}

//打印链表信息
func show(headNode *HeroNode) {
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

func main() {
	//创建头节点
	headNode := &HeroNode{}

	//创建英雄节点
	captain := &HeroNode{
		no:   1,
		name: "Captain",
	}
	ironMan := &HeroNode{
		no:   2,
		name: "IronMan",
	}
	spiderMan := &HeroNode{
		no:   3,
		name: "SpiderMan",
	}
	thor := &HeroNode{
		no:   2,
		name: "Thor",
	}

	//push(headNode, captain)
	//push(headNode, ironMan)
	//push(headNode, spiderMan)
	pushByOrder(headNode, spiderMan)
	pushByOrder(headNode, ironMan)
	pushByOrder(headNode, captain)
	pushByOrder(headNode, thor)
	show(headNode)

	fmt.Println()

	//删除节点2
	remove(headNode, 6)
	show(headNode)
}
