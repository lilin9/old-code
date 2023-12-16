package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func HasSubtree(pRoot1 *TreeNode, pRoot2 *TreeNode) bool {
	//如果有参数为空，直接结束程序
	if pRoot1 == nil || pRoot2 == nil {
		return false
	}

	//根据 pRoot1 取得其层次遍历后的数组
	arr := getListForTree(pRoot1)
	//遍历数组
	for _, item := range arr {
		//根据 pRoot2 的头节点值，找到其在 arr 数组中的位置
		if item.Val == pRoot2.Val {
			//判断两个二叉树当前节点所属的子树是否相等
			if judgeEqual(item, pRoot2) {
				//相等直接返回 true 值
				return true
			}
		}
	}
	return false
}

//根据两个根节点，判断两个子树是否相等，具体逻辑实现
func judgeEqual(pRoot1 *TreeNode, pRoot2 *TreeNode) bool {
	//获取两个子树的层次遍历结果
	arr1 := getListForTree(pRoot1)
	arr2 := getListForTree(pRoot2)

	//确保 arr1 的长度大于 arr2 的长度，即保证 arr2 是 arr1 的子集
	if len(arr2) > len(arr1) {
		return false
	}

	//定义一个标签变量，用来记录两个数组是否有不相等的元素
	isEqual := true
	//遍历两个数组
	for i := 0; i < len(arr2); i++ {
		//如果有不相等的元素
		if arr1[i].Val != arr2[i].Val {
			//将 isEqual 置为 false
			isEqual = false
			//结束循环
			break
		}
	}

	//将标签变量返回
	return isEqual
}

//层次遍历二叉树，返回一个数组
func getListForTree(pRoot *TreeNode) []*TreeNode {
	if pRoot == nil {
		return nil
	}

	//定义一个切片数组
	arr := []*TreeNode{}
	//定义一个管道
	ch := make(chan *TreeNode, 6)
	//将根节点放入管道
	ch <- pRoot
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
			//将 node 放入数组
			arr = append(arr, node)
			//将node的左右子节点放入管道
			if node.Left != nil {
				ch <- node.Left
			}
			if node.Right != nil {
				ch <- node.Right
			}
		}
	}

	//将数组返回
	return arr
}

func main() {
	pRoot1 := &TreeNode{Val: 8}
	node8 := &TreeNode{Val: 8}
	node7 := &TreeNode{Val: 7}
	node9 := &TreeNode{Val: 9}
	node2 := &TreeNode{Val: 2}
	node4 := &TreeNode{Val: 4}
	node7_ := &TreeNode{Val: 7}

	pRoot1.Left = node8
	pRoot1.Right = node7
	node8.Left = node9
	node8.Right = node2
	node2.Left = node4
	node2.Right = node7_

	pRoot2 := &TreeNode{Val: 8}
	node9_ := &TreeNode{Val: 9}
	node2_ := &TreeNode{Val: 2}

	pRoot2.Left = node9_
	pRoot2.Right = node2_

	//pRoot1 := &TreeNode{Vals: 1}
	//node2 := &TreeNode{Vals: 2}
	//node3 := &TreeNode{Vals: 3}
	//node4 := &TreeNode{Vals: 4}
	//node5 := &TreeNode{Vals: 5}
	//node6 := &TreeNode{Vals: 6}
	//pRoot1.Left = node2
	//pRoot1.Right = node3
	//node2.Left = node4
	//node2.Right = node5
	//node5.Left = node6
	//
	//pRoot2 := &TreeNode{Vals: 1}
	//node2_ := &TreeNode{Vals: 2}
	//node4_ := &TreeNode{Vals: 4}
	//node5_ := &TreeNode{Vals: 5}
	//pRoot2.Left = node2_
	//node2.Left = node4_
	//node2.Right = node5_

	arr := getListForTree(pRoot1)
	for _, item := range arr {
		fmt.Printf("%d ", item.Val)
	}
	fmt.Println()

	isEqual := HasSubtree(pRoot1, pRoot2)
	fmt.Println(isEqual)
}
