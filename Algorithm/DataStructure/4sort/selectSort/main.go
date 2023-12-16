package main

import "fmt"

//用 选择排序法 进行排序
func selectSort(arr *[5]int) {
	for i := 0; i < len(arr)-1; i++ {
		//假设数组中第一个数字是最大值
		maxNum := arr[i]
		maxIndex := i

		for j := i + 1; j < len(arr); j++ {
			//如果这个最大值还小于它之后的数字
			if maxNum < arr[j] {
				//就将真正的最大值重新赋值给 maxNum 和 maxIndex
				maxNum = arr[j]
				maxIndex = j
			}
		}

		//当最大值不是假设的第一个数字的时候
		if maxIndex != i {
			arr[i], arr[maxIndex] = arr[maxIndex], arr[i]
		}
	}

}

func main() {
	arr := [5]int{90, 85, 70, 25, 100}

	fmt.Println("排序前:\n", arr)

	selectSort(&arr)

	fmt.Println("排序后:\n", arr)
}
