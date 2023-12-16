package main

import (
	"fmt"
	"math/rand"
	"time"
)

//冒泡排序
func bubbleSort(arr *[100000]int) {
	for i := 0; i < len(arr)-1; i++ {
		for j := 0; j < len(arr)-1-i; j++ {
			if arr[j] > arr[j+1] {
				temp := arr[j]
				arr[j] = arr[j+1]
				arr[j+1] = temp
			}
		}
	}
}

//用 选择排序法 进行排序
func selectSort(arr *[100000]int) {
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

//插入排序
func insertingSorting(arr *[100000]int) {
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
	}
}

//快速排序
//right: 表示数组右边的下标; left: 表示数组左边的下标
func quickSort(left, right int, arr *[100000]int) {
	l := left
	r := right
	//pivot 是中轴, 表示最中间的数
	pivot := arr[(left+right)/2]

	//for 循环的目标是将比 pivot 小的数移动到左边
	//比 pivot 大的数移动到右边
	for l < r {
		//从 pivot 的左边找到大于等于 pivot 的值
		for arr[l] < pivot {
			l++
		}
		//从 pivot 的右边找到小于等于 pivot 的值
		for arr[r] > pivot {
			r--
		}
		//l >= r 表明本次分解任务完成
		if l >= r {
			break
		}

		//交换
		arr[l], arr[r] = arr[r], arr[l]

		//做一个优化
		if arr[l] == pivot {
			r--
		}
		if arr[r] == pivot {
			l++
		}
	}
	//如果 l==r 再移动下
	if l == r {
		l++
		r--
	}
	//向左递归
	if left < r {
		quickSort(left, r, arr)
	}
	//向右递归
	if right > l {
		quickSort(l, right, arr)
	}
}

func main() {
	var arr1 [100000]int
	var arr2 [100000]int
	var arr3 [100000]int
	var arr4 [100000]int

	for i := 1; i < 100000; i++ {
		arr1[i] = rand.Intn(100000)
		arr2[i] = rand.Intn(100000)
		arr3[i] = rand.Intn(100000)
		arr4[i] = rand.Intn(100000)
	}

	//冒泡排序
	start := time.Now().Unix()
	bubbleSort(&arr1)
	end := time.Now().Unix()
	fmt.Printf("冒泡排序算法耗时 %d s\n", end-start)

	//选择排序
	start = time.Now().Unix()
	selectSort(&arr2)
	end = time.Now().Unix()
	fmt.Printf("选择排序算法耗时 %d s\n", end-start)

	//插入排序
	start = time.Now().Unix()
	insertingSorting(&arr3)
	end = time.Now().Unix()
	fmt.Printf("选择排序算法耗时 %d s\n", end-start)

	//快速排序
	start = time.Now().Unix()
	quickSort(0, len(arr4)-1, &arr4)
	end = time.Now().Unix()
	fmt.Printf("快速排序算法耗时 %d s\n", end-start)
}
