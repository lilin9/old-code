package main

import "fmt"

// Emp 支链表节点
type Emp struct {
	Id   int
	Name string
	Next *Emp
}

func (this *Emp) showEmp() {
	fmt.Printf("在第 %d 条链表中 ID=%d 的节点信息是: [id=%d,name=%s]\n",
		this.hashFun(this.Id), this.Id, this.Id, this.Name)
}

//此函数用于取模
func (this *Emp) hashFun(id int) int {
	return id % 7
}
