package main

import (
	"fmt"
	"math/rand"
	"sort"
)

/*
说明：实现对 Hero 结构体切片的排序：sort.Sort(data Interface)
*/

// Hero 声明一个结构体
type Hero struct {
	Name string
	Age  int
}

// HeroSlice 声明一个切片类型
type HeroSlice []Hero

// Len 实现 interface 方法
func (hs HeroSlice) Len() int {
	return len(hs)
}

// Less 方法就是决定使用什么标准进行排序
//这里按 Hero 的年龄从大到小排序
func (hs HeroSlice) Less(i, j int) bool {
	return hs[i].Age < hs[j].Age
}

func (hs HeroSlice) Swap(i, j int) {
	//temp := hs[i]
	//hs[i] = hs[j]
	//hs[j] = temp
	hs[i], hs[j] = hs[j], hs[i]
}

func main() {
	//声明一个 Hero 结构体切片
	var heroSlice HeroSlice
	for i := 0; i < 10; i++ {
		hero := Hero{
			Name: fmt.Sprintf("英雄-%d", rand.Intn(100)),
			Age:  rand.Intn(100),
		}
		heroSlice = append(heroSlice, hero)
	}

	//未排序前的 heroSlice
	for _, value := range heroSlice {
		fmt.Println(value)
	}

	fmt.Println()

	//对 heroSlice 进行排序
	sort.Sort(heroSlice)
	for _, value := range heroSlice {
		fmt.Println(value)
	}
}
