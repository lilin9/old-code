package main

import (
	"fmt"
	"math"
)

/*判断是不是平衡二叉树*/

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func IsBalancedSolution(pRoot *TreeNode) bool {
	//如果是空树
	if pRoot == nil {
		return true
	}

	//判断当前节点的左右子节点高度差的绝对值是否小于1
	if math.Abs(getChildTreeHeight(pRoot.Left, 0)-
		getChildTreeHeight(pRoot.Right, 0)) > 1 {
		return false
	}

	//递归后序节点
	return IsBalancedSolution(pRoot.Left) && IsBalancedSolution(pRoot.Right)
}

//获取子树的高度
func getChildTreeHeight(childNode *TreeNode, height float64) float64 {
	//如果节点为空
	if childNode == nil {
		return 0
	}

	//每递归一个节点，深度加1
	height++
	//到叶子节点
	if childNode.Left == nil && childNode.Right == nil {
		return height
	}

	left := getChildTreeHeight(childNode.Left, height)
	right := getChildTreeHeight(childNode.Right, height)

	if left > right {
		return left
	} else {
		return right
	}
}

func main() {
	//pRoot := &TreeNode{Val: 1} //true
	//node2 := &TreeNode{Val: 2}
	//node3 := &TreeNode{Val: 3}
	//node4 := &TreeNode{Val: 4}
	//node5 := &TreeNode{Val: 5}
	//node6 := &TreeNode{Val: 6}
	//node7 := &TreeNode{Val: 7}
	//pRoot.Left = node2
	//pRoot.Right = node3
	//node2.Left = node4
	//node2.Right = node5
	//node3.Left = node6
	//node3.Right = node7

	//pRoot := &TreeNode{Val: 1} //false
	//node2 := &TreeNode{Val: 2}
	//node3 := &TreeNode{Val: 3}
	//node4 := &TreeNode{Val: 4}
	//node5 := &TreeNode{Val: 5}
	//node6 := &TreeNode{Val: 6}
	//pRoot.Left = node2
	//pRoot.Right = node3
	//node2.Left = node4
	//node3.Right = node5
	//node4.Left = node6

	pRoot := &TreeNode{Val: 1} //true
	node2 := &TreeNode{Val: 2}
	node3 := &TreeNode{Val: 3}
	node4 := &TreeNode{Val: 4}
	node5 := &TreeNode{Val: 5}
	node6 := &TreeNode{Val: 6}
	node7 := &TreeNode{Val: 7}
	pRoot.Left = node2
	pRoot.Right = node3
	node2.Left = node4
	node2.Right = node5
	node3.Right = node6
	node5.Left = node7

	//fmt.Println(getChildTreeHeight(node3, 0))
	fmt.Println(IsBalancedSolution(pRoot))
}
