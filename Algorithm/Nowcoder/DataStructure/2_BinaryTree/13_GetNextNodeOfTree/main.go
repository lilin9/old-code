package main

import "fmt"

type TreeLinkNode struct {
	Val   int
	Left  *TreeLinkNode
	Right *TreeLinkNode
	Next  *TreeLinkNode
}

var resultArr = []*TreeLinkNode{}

func GetNext(pNode *TreeLinkNode) *TreeLinkNode {
	//判断是否为空值，以及判断是否只有一个节点
	if pNode == nil || (pNode.Left == nil && pNode.Right == nil && pNode.Next == nil) {
		return nil
	}

	//获取根节点
	pRoot := getRootNode(pNode)
	//通过根节点，获得整个二叉树的中序遍历数组
	getTraversalArr(pRoot)

	//根据中序遍历数组找到目标节点的下一个节点，返回
	for i := 0; i < len(resultArr)-1; i++ {
		if resultArr[i].Val == pNode.Val {
			return resultArr[i+1]
		}
	}
	return nil
}

//找到二叉树真正的根节点
func getRootNode(pNode *TreeLinkNode) *TreeLinkNode {
	temp := pNode
	for {
		if temp.Next == nil {
			return temp
		}

		temp = temp.Next
	}
}

//根据输入的根节点，得到中序遍历数组
func getTraversalArr(node *TreeLinkNode) {
	if node != nil {
		getTraversalArr(node.Left)
		resultArr = append(resultArr, node)
		getTraversalArr(node.Right)
	}
}

func main() {
	//node8 := &TreeLinkNode{Val: 8}
	//node6 := &TreeLinkNode{Val: 6}
	//node10 := &TreeLinkNode{Val: 10}
	//node5 := &TreeLinkNode{Val: 5}
	//node7 := &TreeLinkNode{Val: 7}
	//node9 := &TreeLinkNode{Val: 9}
	//node11 := &TreeLinkNode{Val: 11}
	//node8.Left = node6
	//node8.Right = node10
	//node6.Left = node5
	//node6.Right = node7
	//node10.Left = node9
	//node10.Right = node11
	//node5.Next = node6
	//node7.Next = node6
	//node9.Next = node10
	//node11.Next = node10
	//node6.Next = node8
	//node10.Next = node8

	node1 := &TreeLinkNode{Val: 1}
	node2 := &TreeLinkNode{Val: 2}
	node3 := &TreeLinkNode{Val: 3}
	node4 := &TreeLinkNode{Val: 4}
	node1.Left = node2
	node2.Right = node3
	node3.Right = node4
	node4.Next = node3
	node3.Next = node2
	node2.Next = node1

	resultNode := GetNext(node4)
	fmt.Println(resultNode.Val)
}
