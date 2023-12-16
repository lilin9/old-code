package main

import "fmt"

/*
题目要求：
1. 判断是不是二叉搜索树
2. 判断是不是后序遍历结果

可能排列顺序：
1. 左孩子，右孩子，根节点
2. 全左孩子，根节点
3. 全右孩子，根节点

不可能的排列顺序：
1. 右孩子，左孩子，根节点

假设题目给的数组就是二叉树的后序遍历结果，那么此方法就是判断此二叉树是不是二叉搜索树
判断方法是根据二叉搜索树的后序遍历可能的排列顺序来判断：
	1. 左孩子，右孩子，根节点 [1, 3, 2, 5, 7, 6, 4]
	2. 全左孩子，根节点 [1, 3, 2, 4]
	3. 全右孩子，根节点 [5, 7, 6, 4]
*/

func VerifySequenceOfBST(sequence []int) bool {
	//判断数组是否为空
	if sequence == nil || len(sequence) == 0 {
		return false
	}

	//如果数组只有 1 到 2 个数，直接返回true
	if len(sequence) == 1 || len(sequence) == 2 {
		return true
	}

	return detailFunc(sequence)
}

//详细代码逻辑实现
func detailFunc(sequence []int) bool {
	//如果参数为空，说明递归结束
	if len(sequence) == 0 {
		return true
	}
	//如果数组只有一个数
	if len(sequence) == 1 || len(sequence) == 2 {
		return true
	}

	//定义两个数组，分别存放比根节点大和小的值
	minArr := []int{} //小数组
	maxArr := []int{} //大数组
	//获取数组的长度
	length := len(sequence)
	//获取二叉树的根节点
	rootVal := sequence[length-1]
	//对数组重新赋值，去除根节点
	sequence = sequence[:length-1]

	//将数组的左右孩子部分分别存入两个数组
	length = len(sequence)
	for i := 0; i < length; i++ {
		//找到比根节点大的第一个数的位置
		if sequence[i] > rootVal {
			//将左孩子部分存入 minArr
			minArr = sequence[:i]
			//将右孩子部分存入 maxArr
			maxArr = sequence[i:]
			break
		}
	}

	//经过上面的循环后，如果maxArr没有值，说明原数组中除根节点外，其他数都比更节点小，此时
	if len(maxArr) == 0 {
		//将数组中出根节点外所有数存入minArr
		minArr = sequence
	}

	//遍历maxArr，判断是否全部数都大于根节点
	if len(maxArr) != 0 {
		length = len(maxArr)
		for i := 0; i < length; i++ {
			//如果找到了比根节点小的数，就直接返回 false 即可
			if maxArr[i] < rootVal {
				return false
			}
		}
	}

	//继续递归两个数组
	return detailFunc(minArr) && detailFunc(maxArr)
}

func main() {
	//arr := []int{1, 3, 2} //true
	//arr := []int{3, 1, 2} //false
	//arr := []int{5, 7, 6, 9, 11, 10, 8} //true
	//arr := []int{1, 2, 7, 4, 6, 5, 3} //false
	arr := []int{1, 2, 3, 4, 5} //true

	res := VerifySequenceOfBST(arr)
	fmt.Println(res)
}
