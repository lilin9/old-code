package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

/*
这里有三种情况：
	第1种：o1、o2 有一个公共父节点；
	第2种：o1 为父节点，o2 为子节点
	第3种：o2 为父节点，o1 为子节点
*/
func lowestCommonAncestor(root *TreeNode, o1 int, o2 int) int {
	if root == nil {
		return 0
	}

	//如果 root 等于 o1 或 o2
	if root.Val == o1 || root.Val == o2 {
		return root.Val
	}

	//递归左右子树
	leftVal := lowestCommonAncestor(root.Left, o1, o2)
	rightVal := lowestCommonAncestor(root.Right, o1, o2)

	//对 leftVal 和 rightVal 进行判断
	if leftVal == 0 && rightVal == 0 {
		return 0
	}
	if leftVal == 0 && rightVal != 0 {
		return rightVal
	}
	if leftVal != 0 && rightVal == 0 {
		return leftVal
	}
	return root.Val
}

func main() {
	node0 := &TreeNode{Val: 0} //3
	node1 := &TreeNode{Val: 1}
	node2 := &TreeNode{Val: 2}
	node3 := &TreeNode{Val: 3}
	node4 := &TreeNode{Val: 4}
	node5 := &TreeNode{Val: 5}
	node6 := &TreeNode{Val: 6}
	node7 := &TreeNode{Val: 7}
	node8 := &TreeNode{Val: 8}
	node3.Left = node5
	node3.Right = node1
	node5.Left = node6
	node5.Right = node2
	node1.Left = node0
	node1.Right = node8
	node2.Left = node7
	node2.Right = node4
	//fmt.Println(lowestCommonAncestor(node3, 5, 1)) //3
	fmt.Println(lowestCommonAncestor(node3, 2, 7)) //2
}
