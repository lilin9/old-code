package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func Mirror(pRoot *TreeNode) *TreeNode {
	if pRoot != nil {
		newNode := &TreeNode{Val: pRoot.Val}
		//递归右子树
		if pRoot.Right != nil {
			newNode.Left = Mirror(pRoot.Right)
		}
		//递归左子树
		if pRoot.Left != nil {
			newNode.Right = Mirror(pRoot.Left)
		}

		return newNode
	} else {
		return pRoot
	}
}

//打印二叉树：按层次遍历
func printTree(root *TreeNode) {
	if root == nil {
		return
	}

	//定义一个管道
	ch := make(chan *TreeNode, 6)
	//将根节点放入管道
	ch <- root
	//循环持续遍历
	for {
		//获取管道的大小
		size := len(ch)
		//管道为空结束循环
		if size == 0 {
			//关闭管道
			close(ch)
			break
		}

		//遍历管道
		for i := 0; i < size; i++ {
			//弹出节点
			node := <-ch
			//打印node的值
			fmt.Printf("%d ", node.Val)
			//将node的左右子节点放入管道
			if node.Left != nil {
				ch <- node.Left
			}
			if node.Right != nil {
				ch <- node.Right
			}
		}

	}
}

func main() {
	pRoot := &TreeNode{Val: 8}
	node6 := &TreeNode{Val: 6}
	node10 := &TreeNode{Val: 10}
	node5 := &TreeNode{Val: 5}
	node7 := &TreeNode{Val: 7}
	node9 := &TreeNode{Val: 9}
	node11 := &TreeNode{Val: 11}

	pRoot.Left = node6
	pRoot.Right = node10
	node6.Left = node5
	node6.Right = node7
	node10.Left = node9
	node10.Right = node11

	fmt.Println("原二叉树：")
	printTree(pRoot)

	pRoot = Mirror(pRoot)

	fmt.Println("\n\n镜像二叉树：")
	printTree(pRoot)
}
