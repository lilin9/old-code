package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var result = true

func isSymmetrical(pRoot *TreeNode) bool {
	//判断参数是否为空
	//判断参数是否只有一个节点
	if pRoot == nil || (pRoot.Left == nil && pRoot.Right == nil) {
		return true
	}

	//调用递归函数
	recursion(pRoot.Left, pRoot.Right)
	return result
}

//递归左右子树
func recursion(lNode *TreeNode, rNode *TreeNode) {
	//当左右节点都不为空时才继续往下执行
	if lNode != nil && rNode != nil {
		//当左右节点的值不相等时
		if lNode.Val != rNode.Val {
			result = false
			return
		}
		//继续递归左右子节点
		recursion(lNode.Left, rNode.Right)
		recursion(lNode.Right, rNode.Left)
	} else {
		//当左右节点都为空时
		if lNode == nil && rNode == nil {
			return
		}
		//当左右节点其中一方为空另一方不为空，说明就不是镜像二叉树
		result = false
	}
}

func main() {
	node1 := &TreeNode{Val: 1} //true
	lNode2 := &TreeNode{Val: 2}
	lNode3 := &TreeNode{Val: 3}
	lNode4 := &TreeNode{Val: 4}
	rNode2 := &TreeNode{Val: 2}
	rNode3 := &TreeNode{Val: 3}
	rNode4 := &TreeNode{Val: 4}
	node1.Left = lNode2
	node1.Right = rNode2
	lNode2.Left = lNode3
	lNode2.Right = lNode4
	rNode2.Left = rNode4
	rNode2.Right = rNode3

	//node1 := &TreeNode{Val: 1} //false
	//lNode2 := &TreeNode{Val: 2}
	//lNode3 := &TreeNode{Val: 3}
	//rNode2 := &TreeNode{Val: 2}
	//rNode3 := &TreeNode{Val: 3}
	//node1.Left = lNode2
	//node1.Right = rNode2
	//lNode2.Right = lNode3
	//rNode2.Right = rNode3

	//node1 := &TreeNode{Val: 1} //false
	//node2 := &TreeNode{Val: 2}
	//node3 := &TreeNode{Val: 3}
	//node1.Left = node2
	//node1.Right = node3

	//node1 := &TreeNode{Val: 1}	//false
	//lNode2 := &TreeNode{Val: 2}
	//lNode3 := &TreeNode{Val: 3}
	//rNode2 := &TreeNode{Val: 2}
	//rNode3 := &TreeNode{Val: 3}
	//node1.Left = lNode2
	//node1.Right = rNode3
	//lNode2.Left = lNode3
	//rNode3.Left = rNode2

	fmt.Println(isSymmetrical(node1))
}
