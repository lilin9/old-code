package main

import "fmt"

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func reConstructBinaryTree(pre []int, vin []int) *TreeNode {
	//参数为空
	if len(pre) == 0 || len(vin) == 0 {
		return nil
	}

	//如果只有一个节点
	if len(pre) == 1 && len(vin) == 1 {
		return &TreeNode{Val: pre[0]}
	}

	//根节点
	rootNode := &TreeNode{Val: pre[0]}
	constructBinaryTree(rootNode, pre, vin)
	return rootNode

}

//重构二叉树详细逻辑
func constructBinaryTree(root *TreeNode, pre []int, vin []int) {
	//前序遍历列表和中序遍历列表为空时结束递归
	if len(pre) != 0 && len(vin) != 0 {
		//获取中序列表中的左子树列表
		vinLeft := getVin(root.Val, vin, true)
		//获取中序列表中的右子树列表
		vinRight := getVin(root.Val, vin, false)
		//获取前序列表中的左子树列表
		preLeft := getPre(pre, getCount(vinLeft), true)
		//获取前序列表中的右子树列表
		preRight := getPre(pre, getCount(vinRight), false)

		//判断根节点是否存在左子树
		if vinLeft != nil {
			if len(vinLeft) != 0 {
				//重构左子树
				root.Left = &TreeNode{Val: preLeft[0]}
				//递归
				constructBinaryTree(root.Left, preLeft, vinLeft)
			}
		}
		//判断根节点是否存在右子树
		if vinRight != nil {
			if len(vinRight) != 0 {
				//重构右子树
				root.Right = &TreeNode{Val: preRight[0]}
				//递归
				constructBinaryTree(root.Right, preRight, vinRight)
			}
		}
	}
}

//根据中序遍历结果获取左右子树列表
func getVin(root int, vin []int, isLeft bool) []int {
	//获取根节点在中序遍历列表中的下标索引
	index := getIndex(root, vin)
	//判断是想要左子树还是右子树
	if isLeft {
		return vin[:index]
	} else {
		return vin[index+1:]
	}
}

//获得左右子树的前序列表
func getPre(pre []int, count int, isLeft bool) []int {
	//如果 count==0 直接退出函数
	if count == 0 {
		return nil
	}

	//删除列表中的第一个元素
	pre = pre[1:]
	//根据count，即子树的节点个数，获取前序列表中相应子树的节点值列表
	if isLeft { //当想要获取左子树的列表时
		return pre[:count]
	} else { //当想要获取右子树的列表时
		//判断 count 奇偶性
		if len(pre)%2 != 0 {
			return pre[count-1:]
		} else {
			return pre[count:]
		}
	}
}

//获取子树的节点个数，根据中序遍历列表
func getCount(vin []int) int {
	var val int
	for _, value := range vin {
		if value != 0 {
			val++
		}
	}
	return val
}

//获得元素在切片中的索引
func getIndex(val int, arr []int) (index int) {
	for i, v := range arr {
		if v == val {
			index = i
		}
	}
	return
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
	//pre := []int{1, 2, 3, 4, 5, 6, 7}
	//vin := []int{3, 2, 4, 1, 6, 5, 7} //1, 2, 5, 3, 4, 6, 7
	//pre := []int{1, 2, 4, 7, 3, 5, 6, 8}
	//vin := []int{4, 7, 2, 1, 5, 3, 8, 6} // 1, 2, 3, 4, 5, 6, 7, 8
	pre := []int{1, 2, 3, 4}
	vin := []int{1, 2, 3, 4}

	root := reConstructBinaryTree(pre, vin)
	fmt.Print("输出: ")
	printTree(root)
}
