package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func Print(pRoot *TreeNode) [][]int {
	if pRoot == nil {
		return nil
	}

	//声明最终要返回的数组
	resultArr := [][]int{}
	//声明一个管道
	ch := make(chan *TreeNode, 100)
	//将根节点放入管道
	ch <- pRoot
	for {
		//获取管道长度
		length := len(ch)
		//管道为空时结束循环
		if length == 0 {
			break
		}

		//遍历管道
		itemArr := []int{}
		for i := 0; i < length; i++ {
			//将管道数据存入数组
			node := <-ch
			itemArr = append(itemArr, node.Val)

			//将当前层节点的下一个层放入管道
			if node.Left != nil {
				ch <- node.Left
			}
			if node.Right != nil {
				ch <- node.Right
			}
		}

		//将 itemArr 放入 resultArr 中
		resultArr = append(resultArr, itemArr)
	}
	return resultArr
}

func main() {
	//node1 := &TreeNode{Val: 1}
	//node2 := &TreeNode{Val: 2}
	//node3 := &TreeNode{Val: 3}
	//node4 := &TreeNode{Val: 4}
	//node5 := &TreeNode{Val: 5}
	//node1.Left = node2
	//node1.Right = node3
	//node3.Left = node4
	//node3.Right = node5
	//
	//resultArr := Print(node1)
	//fmt.Println(resultArr)

	//node8 := &TreeNode{Val: 8}
	//node6 := &TreeNode{Val: 6}
	//node10 := &TreeNode{Val: 10}
	//node5 := &TreeNode{Val: 5}
	//node7 := &TreeNode{Val: 7}
	//node9 := &TreeNode{Val: 9}
	//node11 := &TreeNode{Val: 11}
	//node8.Left = node6
	//node8.Right = node10
	//node6.Left = node5
	//node6.Right = node7
	//node10.Left = node9
	//node10.Right = node11
	//
	//resultArr := Print(node8)
	//fmt.Println(resultArr)

	node1 := &TreeNode{Val: 1}
	node2 := &TreeNode{Val: 2}
	node3 := &TreeNode{Val: 3}
	node4 := &TreeNode{Val: 4}
	node5 := &TreeNode{Val: 5}
	node1.Left = node2
	node1.Right = node3
	node2.Left = node4
	node2.Right = node5

	resultArr := Print(node1)
	fmt.Println(resultArr)
}
