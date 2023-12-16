package main

import "fmt"

/*
对有序数组进行二分查找
*/
func binaryFind(nums *[5]int, inputNum int, leftIndex int, rightIndex int) {
	//如果 leftIndex 大于 rightIndex，就说明找不到
	if leftIndex > rightIndex {
		fmt.Print("找不到")
		return
	}

	//先找到中间下标
	middleIndex := (leftIndex + rightIndex) / 2
	if inputNum > (*nums)[middleIndex] {
		//如果 inputNum 大于 中间的数，就往右边查找
		binaryFind(nums, inputNum, middleIndex+1, rightIndex)

	} else if inputNum < (*nums)[middleIndex] {
		//如果 inputNum 小于 中间的数，就往左边查找
		binaryFind(nums, inputNum, leftIndex, middleIndex-1)

	} else {
		//如果找到了，就把下标打印到控制台
		fmt.Print("找到了，下标是 ", middleIndex)
	}
}

func main() {
	nums := [5]int{11, 22, 33, 44, 55}

	binaryFind(&nums, 40, 0, len(nums)-1)
}
