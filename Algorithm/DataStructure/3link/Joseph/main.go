package main

import "fmt"

/*
Josephu 问题：
设编号为 1，2，……，n 的 n 个人围坐一圈，约定编号为 k(1 <= k <= n) 的人从 1 开始报数，数到 m 的那个人出列，
他的下一位又从 1 开始报数，数到 m 的那个人又出列……以此类推，直到所有人出列为止，由此产生了一个出队编号的序列。

提示：
用一个不带头结点的循环链表来处理 Josephu 问题：先构成一个有 n 个结点的单循环链表，然后由 k 结点起从 1 开始计数，
计到 m 时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从 1 开始计数，
知道最后一个结点从链表中删除算法结束。
*/

type Children struct {
	id   int
	next *Children
}

//显示 Children 的环形链表
func showChildrenLink(headNode *Children) {
	tempNode := headNode
	//如果是一个空链表
	if tempNode.next == nil {
		fmt.Println("传入的链表为空……")
		return
	}

	//遍历环形链表
	for {
		fmt.Printf("小孩的序号是: id=%d\n", tempNode.id)
		//如果是最后一个结点，就结束循环
		if tempNode.next == headNode {
			break
		}
		//让 tempNode 指向下一个结点
		tempNode = tempNode.next

	}
}

//得到 Children 的环形链表
func getChildrenLink(num int) *Children {
	//对 num 值进行判断
	if num < 1 {
		fmt.Println("num 必须大于等于1")
		return nil
	}

	//定义头节点
	headNode := &Children{}
	//定义一个辅助结点
	tempNode := &Children{}

	//循环生成 Children
	for i := 1; i <= num; i++ {
		childrenNode := &Children{id: i}

		//如果是第一个结点
		if i == 1 {
			headNode = childrenNode //首指针不能改动
			tempNode = childrenNode
			//让第一个结点的指针指向自己
			tempNode.next = headNode
			continue
		} else {
			//让 tempNode 指向新添加的结点
			tempNode.next = childrenNode
			//让 tempNode 指针指向最新的结点
			tempNode = childrenNode
			//让最新添加的结点指向第一个结点
			tempNode.next = headNode
		}
	}
	//将头节点返回
	return headNode
}

//计算链表大小
func getSize(head *Children) int {
	temp := head
	size := 0

	//判断链表是否为空
	if head.next == nil {
		fmt.Println("传入的链表为空……")
		return 0
	}

	//循环遍历链表
	for {
		size++
		if temp.next == head {
			//到最后一个节点时退出循环
			break
		}
		//temp 指向下一个节点
		temp = temp.next
	}

	//将 size 返回
	return size
}

// Joseph 约瑟夫问题的实际代码解决
func Joseph(head *Children, startId int, countNum int) {
	//判断链表是否为空
	if head.next == nil {
		fmt.Println("传入的链表为空……")
		return
	}

	//判断 startId 是否大于链表总数
	if startId > getSize(head) {
		fmt.Println("参数 startId 不能大于链表的总数")
		return
	}

	temp := head
	tail := head

	//temp 从 startId 的位置开始移动
	for i := 1; i <= startId-1; i++ {
		temp = temp.next
		tail = tail.next
	}

	//循环遍历链表
	for {
		//如果找到了最后一个节点
		if tail.next == head {
			break
		}
		tail = tail.next
	}

	count := 1
	//再次循环遍历链表
	for {
		//当temp和tail都指向同一个节点时，说明链表只有一个节点
		if temp.next == tail.next {
			//打印节点信息
			fmt.Printf("被删除的小孩序号是: id=%d\n", temp.id)
			temp.next = nil
			break
		}

		//如果 count==countNum, 就将当前节点删除
		if count == countNum {
			//重置count
			count = 1
			//打印节点信息
			fmt.Printf("被删除的小孩序号是: id=%d\n", temp.id)
			//让 temp 指向下一个节点
			temp = temp.next
			//删除当前节点
			tail.next = temp
		} else {
			//如果不等于，就让 temp 和 tail 分别指向下一个节点
			temp = temp.next
			tail = tail.next
		}
		//count 累加
		count++
	}
}

func main() {
	//得到 children 的环形链表
	headChildren := getChildrenLink(400)
	//打印环形链表
	showChildrenLink(headChildren)

	fmt.Printf("\n约瑟夫问题解决:\n")

	Joseph(headChildren, 4, 40)
}
