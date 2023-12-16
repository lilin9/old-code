package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var arr = []int{}

func KthNode(pRoot *TreeNode, k int) int {
	//确保输入正确
	if pRoot == nil || k == 0 {
		return -1
	}
	findKthNode(pRoot)

	//获取二叉树的节点个数
	size := len(arr)
	//如果k大于二叉树的节点个数，返回-1
	if size < k {
		return -1
	}

	return arr[k-1]
}

func findKthNode(node *TreeNode) {
	if node != nil {
		//递归左子树
		findKthNode(node.Left)
		arr = append(arr, node.Val)
		//递归右子树
		findKthNode(node.Right)
	}
}

func main() {
	pRoot := &TreeNode{Val: 5}

	node3 := &TreeNode{Val: 3}
	node7 := &TreeNode{Val: 7}
	node2 := &TreeNode{Val: 2}
	node4 := &TreeNode{Val: 4}
	node6 := &TreeNode{Val: 6}
	node8 := &TreeNode{Val: 8}

	pRoot.Left = node3
	pRoot.Right = node7
	node3.Left = node2
	node3.Right = node4
	node7.Left = node6
	node7.Right = node8

	res := KthNode(pRoot, 3)
	fmt.Printf("返回值：%d\n", res)
}
