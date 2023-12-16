package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func PrintFromTopToBottom(root *TreeNode) []int {
	if root == nil {
		return nil
	}

	//空数组
	result := []int{}
	//定义一个队列（管道）
	queue := make(chan *TreeNode, 10)
	//将二叉树根节点加入队列
	queue <- root
	//第一个for循环，用来遍历整个二叉树
	for {
		//获取队列大小
		size := len(queue)
		//当队列为空结束循环
		if size == 0 {
			break
		}
		//第二个for循环，遍历同一层次的二叉树节点
		for i := 0; i < size; i++ {
			//取出队列中的节点
			node := <-queue
			//将节点值存入数组中
			result = append(result, node.Val)
			//将节点的左右子树放入队列
			if node.Left != nil {
				queue <- node.Left
			}
			if node.Right != nil {
				queue <- node.Right
			}
		}
	}
	//将数组返回
	return result
}

func main() {
	//root := &TreeNode{Vals: 8}
	//node6 := &TreeNode{Vals: 6}
	//node10 := &TreeNode{Vals: 10}
	//node2 := &TreeNode{Vals: 2}
	//node1 := &TreeNode{Vals: 1}
	//
	//root.Left = node6
	//root.Right = node10
	//node10.Left = node2
	//node10.Right = node1

	root := &TreeNode{Val: 5}
	node4 := &TreeNode{Val: 4}
	node3 := &TreeNode{Val: 3}
	node2 := &TreeNode{Val: 2}
	node1 := &TreeNode{Val: 1}

	root.Left = node4
	node4.Left = node3
	node3.Left = node2
	node2.Left = node1

	res := PrintFromTopToBottom(root)
	fmt.Println(res)
}
