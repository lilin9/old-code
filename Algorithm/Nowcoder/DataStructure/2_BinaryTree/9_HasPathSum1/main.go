package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var res bool

func hasPathSum(root *TreeNode, sum int) bool {
	if root == nil {
		return false
	}

	//如果只有一个节点，直接进行比较即可
	if root.Left == nil && root.Right == nil {
		if root.Val == sum {
			return true
		} else {
			return false
		}
	}

	detail(root, 0, sum)
	return res
}

//详细代码实现
func detail(node *TreeNode, nodeSum int, sum int) {
	//如果已经确定答案，即 res=true，则不再执行后面的代码
	if res {
		return
	}

	//判断当前节点是叶子节点
	if node.Left == nil && node.Right == nil {
		//nodeSum 加上当前节点值
		nodeSum = nodeSum + node.Val
		//判断 nodeSum 是否和 sum 相等
		if nodeSum == sum {
			res = true
		} else {
			res = false
		}
		return
	}

	//nodeSum 加上当前节点值
	nodeSum = nodeSum + node.Val
	//递归左右子节点
	if node.Left != nil {
		detail(node.Left, nodeSum, sum)
	}
	if node.Right != nil {
		detail(node.Right, nodeSum, sum)
	}

}

func main() {
	//root := &TreeNode{Vals: 5}
	//node4 := &TreeNode{Vals: 4}
	//node8 := &TreeNode{Vals: 8}
	//node1 := &TreeNode{Vals: 1}
	//node11 := &TreeNode{Vals: 11}
	//node9 := &TreeNode{Vals: 9}
	//node2 := &TreeNode{Vals: 2}
	//node7 := &TreeNode{Vals: 7}
	//sum := 22
	//root.Left = node4
	//root.Right = node8
	//node4.Left = node1
	//node4.Right = node11
	//node8.Right = node9
	//node11.Left = node2
	//node11.Right = node7

	//root := &TreeNode{Vals: 1}
	//node2 := &TreeNode{Vals: 2}
	//root.Left = node2
	//sum := 1

	//root := &TreeNode{Vals: 0}
	//node2 := &TreeNode{Vals: 2}
	//node8 := &TreeNode{Vals: 8}
	//node2_ := &TreeNode{Vals: -2}
	//root.Left = node2
	//root.Right = node8
	//node2.Left = node2_
	//sum := 0

	root := &TreeNode{Val: -2}
	node2 := &TreeNode{Val: -3}
	root.Right = node2
	sum := -2

	res := hasPathSum(root, sum)
	fmt.Println("res=", res)
}
