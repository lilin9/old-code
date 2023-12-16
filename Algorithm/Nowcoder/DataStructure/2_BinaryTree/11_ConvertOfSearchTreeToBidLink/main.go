package main

import (
	"fmt"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var result = []*TreeNode{}

func Convert(pRootOfTree *TreeNode) *TreeNode {
	if pRootOfTree == nil {
		return nil
	}

	//获得中序遍历二叉树的数组
	getMiddleList(pRootOfTree)

	//遍历数组，将数组元素串成链表
	for i := 0; i < len(result)-1; i++ {
		result[i].Right = result[i+1]
		result[i+1].Left = result[i]
	}

	//将链表头返回
	return result[0]
}

//得到二叉树中序遍历的数组
func getMiddleList(pRootOfTree *TreeNode) {
	//判空
	if pRootOfTree == nil {
		return
	}
	//中序递归二叉树
	getMiddleList(pRootOfTree.Left)
	result = append(result, pRootOfTree)
	getMiddleList(pRootOfTree.Right)
}

//打印链表
func printLink(pRoot *TreeNode) {
	if pRoot != nil {
		fmt.Printf("%d ", pRoot.Val)
		printLink(pRoot.Right)
	}
}

func main() {
	pRoot := &TreeNode{Val: 10}
	node6 := &TreeNode{Val: 6}
	node14 := &TreeNode{Val: 14}
	node4 := &TreeNode{Val: 4}
	node8 := &TreeNode{Val: 8}
	node12 := &TreeNode{Val: 12}
	node16 := &TreeNode{Val: 16}
	pRoot.Left = node6
	pRoot.Right = node14
	node6.Left = node4
	node6.Right = node8
	node14.Left = node12
	node14.Right = node16

	root := Convert(pRoot)
	printLink(root)
}
