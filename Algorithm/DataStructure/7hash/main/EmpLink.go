package main

import (
	"fmt"
)

// EmpLink 主链表节点
// EmpLink 不带表头，第一个节点就用来存放数据
type EmpLink struct {
	Head *Emp //指向支链表的头节点
}

// Show 显示链表
func (this *EmpLink) Show(no int) {
	if this.Head == nil {
		fmt.Printf("第 %d 链表为空\n", no)
		return
	}

	//循环遍历链表
	temp := this.Head
	fmt.Printf("第 %d 链表: ", no)
	for {
		if temp != nil {
			fmt.Printf("[id=%d,name=%s] ", temp.Id, temp.Name)
		} else {
			break
		}
		temp = temp.Next
	}

	fmt.Println()
}

func (this *EmpLink) Insert(e *Emp) {
	//定义两个辅助指针
	temp := this.Head
	var pre *Emp = nil

	//判断支链表是否为空
	if temp == nil {
		//直接赋值
		this.Head = e
		return
	}

	//支链不为空，循环遍历支链
	for {
		//判断是否遍历到支链末尾
		if temp == nil {
			break
		} else if temp.Id >= e.Id { //判断 temp 指向的节点 id 是否大于传进来的节点 id
			break
		}

		//否则，pre 和 temp 都指向各自的下一个节点
		pre = temp //这里保证 pre 始终指向 temp 的前一个节点
		temp = temp.Next
	}

	//添加节点
	pre.Next = e
	e.Next = temp
}

// Delete 根据 Id 删除节点信息
func (this *EmpLink) Delete(id int) {
	temp := this.Head
	var pre *Emp = nil
	isEnd := false

	//判断支链表是否为空
	if this.Head == nil {
		fmt.Printf("ID=%d 的节点不存在，无法删除\n", id)
		return
	}

	//如果支链表只有一个节点，或者要删除的节点就是头节点
	if temp.Id == id {
		this.Head = temp.Next
		fmt.Printf("ID为 %d 的节点已被删除\n", id)
		return
	}

	//循环遍历链表
	for {
		if temp == nil {
			isEnd = true
			break
		} else if temp.Id == id {
			break
		}

		pre = temp
		temp = temp.Next
	}

	if isEnd {
		fmt.Printf("ID=%d 的节点不存在，无法删除\n", id)
	} else {
		pre.Next = temp.Next
		fmt.Printf("ID为 %d 的节点已被删除\n", id)
	}
}

func (this *EmpLink) GetById(id int) *Emp {
	temp := this.Head
	for {
		if temp != nil && temp.Id == id {
			return temp
		} else if temp == nil {
			break
		}
		temp = temp.Next
	}
	return nil
}
