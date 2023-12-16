package main

import "fmt"

// HashTable 主链表
type HashTable struct {
	LinkArr [7]EmpLink
}

// Show 显示hash表
func (this *HashTable) Show() {
	for i := 0; i < len(this.LinkArr); i++ {
		this.LinkArr[i].Show(i)
	}
}

// Insert 添加数据
func (this *HashTable) Insert(emp *Emp) {
	//使用散列函数，对 id 取模
	linkNo := this.hashFun(emp.Id)
	//添加数据
	this.LinkArr[linkNo].Insert(emp)
}

// Delete 根据 id 删除节点
func (this *HashTable) Delete(id int) {
	//使用散列函数，对 id 取模
	linkNo := this.hashFun(id)
	//添加数据
	this.LinkArr[linkNo].Delete(id)
}

// GetById 根据 id 查找节点信息
func (this *HashTable) GetById(id int) *Emp {
	//使用散列函数，对 id 取模
	linkNo := this.hashFun(id)
	//查找节点信息
	return this.LinkArr[linkNo].GetById(id)
}

//此函数用于取模
func (this *HashTable) hashFun(id int) int {
	return id % 7
}

func main() {
	hashTable := HashTable{}

	//创建 emp
	smith := Emp{
		Id:   7,
		Name: "smith",
	}
	tom := Emp{
		Id:   14,
		Name: "tom",
	}
	jerry := Emp{
		Id:   15,
		Name: "jerry",
	}
	tony := Emp{
		Id:   21,
		Name: "tony",
	}

	//添加 emp
	hashTable.Insert(&smith)
	hashTable.Insert(&tom)
	hashTable.Insert(&jerry)
	hashTable.Insert(&tony)
	//显示 hash 链表
	hashTable.Show()

	//查找 emp
	fmt.Println("\n查找节点信息:")
	id := 14
	e := hashTable.GetById(id)
	if e != nil {
		//打印节点信息
		e.showEmp()
	} else {
		fmt.Printf("ID=%d 的节点信息不存在\n", id)
	}

	//删除 emp
	fmt.Println("\n删除节点:")
	hashTable.Delete(15)
	//显示 hash 链表
	hashTable.Show()
}
