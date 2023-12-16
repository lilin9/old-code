package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func lowestCommonAncestor(root *TreeNode, p int, q int) int {
	if root == nil {
		return 0
	}

	//如果 root 值等于 p 或 q，直接返回 root
	if root.Val == p || root.Val == q {
		return root.Val
	}

	result := 0
	//如果 p 和 q 都小于 root 值，说明 p 和 q 都在 root 右子树上
	if p < root.Val && q < root.Val {
		result = lowestCommonAncestor(root.Left, p, q)
	}
	//如果 p 和 q 都大于 root 值，说明 p 和 q 都在 root 左子树上
	if p > root.Val && q > root.Val {
		result = lowestCommonAncestor(root.Right, p, q)
	}
	//这种情况说明 p 和 q 分别位于 root 左右子树上，root 即为 p、q 最近公共祖先
	if (p > root.Val && q < root.Val) || (p < root.Val && q > root.Val) {
		result = root.Val
	}
	return result
}

func main() {
	node7 := &TreeNode{Val: 7}
	node1 := &TreeNode{Val: 1}
	node12 := &TreeNode{Val: 12}
	node0 := &TreeNode{Val: 0}
	node4 := &TreeNode{Val: 4}
	node11 := &TreeNode{Val: 11}
	node14 := &TreeNode{Val: 14}
	node3 := &TreeNode{Val: 3}
	node5 := &TreeNode{Val: 5}
	node7.Left = node1
	node7.Right = node12
	node1.Left = node0
	node1.Right = node4
	node12.Left = node11
	node12.Right = node14
	node4.Left = node3
	node4.Right = node5

	//fmt.Println(lowestCommonAncestor(node7, 1, 12)) //7
	fmt.Println(lowestCommonAncestor(node7, 12, 11)) //12
	//fmt.Println(lowestCommonAncestor(node7, 0, 3)) //1
}
