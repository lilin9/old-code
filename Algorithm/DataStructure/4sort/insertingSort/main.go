package main

import "fmt"

//插入排序
func insertingSorting(arr *[5]int) {
	for i := 1; i <= len(arr)-1; i++ {
		//保存需要比较的值
		insertVal := arr[i]
		//保存下标: 总是在要进行比较的值的前一位
		insertIndex := i - 1

		//如果insertIndex 需要始终大于 0, 而且下标 insertIndex 处的值始终小于 insertVal,
		//循环就继续
		for insertIndex >= 0 && arr[insertIndex] < insertVal {
			arr[insertIndex+1] = arr[insertIndex]
			insertIndex--
		}

		//如果 insertVal 在比较后将要插入的位置就是它现在的位置，就不用进行替换了
		if insertIndex+1 != i {
			arr[insertIndex+1] = insertVal
		}

		fmt.Printf("第 %d 次排序后的数组: %v\n", i, *arr)
	}
}

func main() {
	arr := [5]int{23, 0, 12, 56, 34}
	//排序前
	fmt.Println("排序前: ", arr)

	//排序
	insertingSorting(&arr)
	//排序后
	//fmt.Println("排序后:\n", arr)

}
