package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

//全局变量
var pathVal = [][]int{}     //储存路径
var oldNode = []*TreeNode{} //储存已经被遍历过的节点

func FindPath(root *TreeNode, expectNumber int) [][]int {
	//如果二叉树为空
	if root == nil {
		return nil
	}

	//如果 expectNumber=0 返回 nil
	if expectNumber == 0 {
		return nil
	}

	//如果只有一个节点，直接进行比较即可
	if root.Left == nil && root.Right == nil {
		if root.Val == expectNumber {
			temp := []int{}
			temp = append(temp, root.Val)
			return append(pathVal, temp)
		} else {
			return nil
		}
	}

	//递归遍历二叉树
	detail(root, root, 0, expectNumber, []int{})

	return pathVal
}

//详细代码实现
func detail(node, root *TreeNode, nodeSum int, sum int, arr []int) {
	//判断当前节点是否已经被遍历过
	for _, item := range oldNode {
		//如果等于，直接退出程序
		if item == node {
			return
		}
	}
	//将当前值加入到数组之中
	arr = append(arr, node.Val)
	//将当前节点存入 oldNode 数组中
	oldNode = append(oldNode, node)
	//nodeSum加上当前节点值
	nodeSum += node.Val
	//判断是否是叶子节点
	if node.Left == nil && node.Right == nil {
		//判断nodeSum是否等于sum
		if nodeSum == sum {
			//将arr数组添加到二维数组中
			pathVal = append(pathVal, arr)
		}
		//从根节点重新递归
		detail(root, root, 0, sum, []int{})
	} else {
		//如果不是叶子节点，继续递归左右子节点
		if node.Left != nil {
			detail(node.Left, root, nodeSum, sum, arr)
		}
		if node.Right != nil {
			detail(node.Right, root, nodeSum, sum, arr)
		}
	}
}

func main() {
	//root := &TreeNode{Val: 10}
	//node5 := &TreeNode{Val: 5}
	//node12 := &TreeNode{Val: 12}
	//node4 := &TreeNode{Val: 4}
	//node7 := &TreeNode{Val: 7}
	//root.Left = node5
	//root.Right = node12
	//node5.Left = node4
	//node5.Right = node7

	//root := &TreeNode{Val: 10}
	//node5 := &TreeNode{Val: 5}
	//node12 := &TreeNode{Val: 12}
	//node4 := &TreeNode{Val: 4}
	//node7 := &TreeNode{Val: 7}
	//root.Left = node5
	//root.Right = node12
	//node5.Left = node4
	//node5.Right = node7

	//root := &TreeNode{Val: 2}
	//node3 := &TreeNode{Val: 3}
	//root.Left = node3

	//root := &TreeNode{Val: 1}
	//node3 := &TreeNode{Val: 3}
	//node4 := &TreeNode{Val: 4}
	//root.Left = node3
	//root.Right = node4

	root := &TreeNode{Val: 5}
	node4 := &TreeNode{Val: 4}
	node8 := &TreeNode{Val: 8}
	node11 := &TreeNode{Val: 11}
	node13 := &TreeNode{Val: 13}
	node4_ := &TreeNode{Val: 4}
	node7 := &TreeNode{Val: 7}
	node2 := &TreeNode{Val: 2}
	node5 := &TreeNode{Val: 5}
	node1 := &TreeNode{Val: 1}
	root.Left = node4
	root.Right = node8
	node4.Left = node11
	node11.Left = node7
	node11.Right = node2
	node8.Left = node13
	node8.Right = node4_
	node4_.Left = node5
	node4_.Right = node1

	resArr := FindPath(root, 22)
	fmt.Println(resArr)

}
