package main

import (
	"fmt"
	"sort"
)

func main() {
	mapTest := map[int]int{
		2: 200,
		3: 300,
		4: 400,
		1: 100,
	}

	//将 map 的 key 存放到切片中
	var keys []int
	for key := range mapTest {
		keys = append(keys, key)
	}

	//对切片中的 key 进行排序
	sort.Ints(keys)

	//遍历输出 map
	for _, key := range keys {
		fmt.Printf("mapTest[%v]=%v \n", key, mapTest[key])
	}
}
