package main

import (
	"fmt"
	"math"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func TreeDepth(pRoot *TreeNode) int {
	//判断输入是否为空
	if pRoot == nil {
		return 0
	}

	//分别递归左子树和右子树
	left := TreeDepth(pRoot.Left)
	right := TreeDepth(pRoot.Right)

	//将递归左右子树得到的深度中，较大的深度加 1 返回
	return int(math.Max(float64(left), float64(right)) + 1)
}

func main() {
	//根节点
	rootNode := &TreeNode{Val: 1}
	node2 := &TreeNode{Val: 2}
	node3 := &TreeNode{Val: 3}
	node4 := &TreeNode{Val: 4}
	node5 := &TreeNode{Val: 5}
	node6 := &TreeNode{Val: 6}
	node7 := &TreeNode{Val: 7}

	//连接成树
	rootNode.Left = node2
	rootNode.Right = node3
	node2.Left = node4
	node2.Right = node5
	node3.Right = node6
	node5.Left = node7

	//获得二叉树的深度
	fmt.Println("二叉树的深度是: ", TreeDepth(rootNode))
}
