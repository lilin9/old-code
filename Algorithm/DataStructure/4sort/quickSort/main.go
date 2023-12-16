package main

import "fmt"

//快速排序
//right: 表示数组右边的下标; left: 表示数组左边的下标
func quickSort(left, right int, arr *[6]int) {
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
	arr := [6]int{-9, 78, 0, 23, -567, 79}
	fmt.Println("排序前: ", arr)

	//快速排序
	quickSort(0, len(arr)-1, &arr)
	fmt.Println("排序后: ", arr)

}
