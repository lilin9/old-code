package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

//记录符合条件的路径个数
var pathCount = 0

/*
	两次递归求出符合要求的路径，第一次递归遍历所有节点，即路径起始节点
*/
func FindPath(root *TreeNode, sum int) int {
	if root != nil {
		//根据当前节点查找符合条件的路径
		findRightPath(root, sum)
		//递归左子树
		FindPath(root.Left, sum)
		//递归右子树
		FindPath(root.Right, sum)
	}
	return pathCount
}

/*
	第二次递归，根据第一次递归提供的起始节点，找到符合要求的路径
	(依旧是前序遍历方式)
*/
func findRightPath(start *TreeNode, sum int) {
	if start != nil {
		//如果 sum 等于了 当前节点值，说明找到了目标路径
		if sum == start.Val {
			pathCount++
		}
		//每次递归，sum就减去当前节点值
		sum = sum - start.Val
		//递归左子树
		findRightPath(start.Left, sum)
		//递归右子树
		findRightPath(start.Right, sum)
	}
}

func main() {
	//node1 := &TreeNode{Val: 1}	// 3
	//node2 := &TreeNode{Val: 2}
	//tNode3 := &TreeNode{Val: 3}
	//lNode4 := &TreeNode{Val: 4}
	//node5 := &TreeNode{Val: 5}
	//rNode4 := &TreeNode{Val: 4}
	//bNode3 := &TreeNode{Val: 3}
	//node1_ := &TreeNode{Val: -1}
	//node1.Left = node2
	//node1.Right = tNode3
	//node2.Left = rNode4
	//node2.Right = node5
	//tNode3.Left = lNode4
	//tNode3.Right = bNode3
	//node5.Left = node1_
	//sum := 6

	//node1 := &TreeNode{Val: 0}	// 2
	//node2 := &TreeNode{Val: 1}
	//node0.Left = node1
	//sum := 1

	//node1 := &TreeNode{Val: 1} // 2
	//node2 := &TreeNode{Val: 2}
	//node3 := &TreeNode{Val: 3}
	//node1.Left = node2
	//node2.Left = node3
	//sum := 3

	node1 := &TreeNode{Val: 1} // 13
	node2 := &TreeNode{Val: 0}
	node3 := &TreeNode{Val: 1}
	node4 := &TreeNode{Val: 1}
	node5 := &TreeNode{Val: 2}
	node6 := &TreeNode{Val: 0}
	node7 := &TreeNode{Val: -1}
	node8 := &TreeNode{Val: 0}
	node9 := &TreeNode{Val: 1}
	node10 := &TreeNode{Val: -1}
	node11 := &TreeNode{Val: 0}
	node12 := &TreeNode{Val: -1}
	node13 := &TreeNode{Val: 0}
	node14 := &TreeNode{Val: 1}
	node15 := &TreeNode{Val: 0}
	node1.Left = node2
	node1.Right = node3
	node2.Left = node4
	node2.Right = node5
	node3.Left = node6
	node3.Right = node7
	node4.Left = node8
	node4.Right = node9
	node5.Left = node10
	node5.Right = node11
	node6.Left = node12
	node6.Right = node13
	node7.Left = node14
	node7.Right = node15
	sum := 2

	fmt.Println(FindPath(node1, sum))
}
