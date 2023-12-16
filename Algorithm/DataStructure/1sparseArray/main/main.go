package main

import "fmt"

type Node struct {
	row   int
	col   int
	value int
}

func main() {
	//先创建一个原始数组
	var arr [11][11]int
	arr[1][2] = 1
	arr[2][3] = 2

	fmt.Println("原始数组: ")

	//遍历原始数组
	for _, v := range arr {
		for _, v2 := range v {
			fmt.Printf("%d\t", v2)
		}
		fmt.Println()
	}

	fmt.Println("\n稀疏数组: ")

	//创建稀疏数组
	var sparseArra []Node

	//稀疏数组第一个节点用于存放原始数组的规模信息
	nodeValue := Node{
		row:   11,
		col:   11,
		value: 0,
	}
	sparseArra = append(sparseArra, nodeValue)

	for i, v := range arr {
		for j, v2 := range v {
			if v2 != 0 {
				nodeValue = Node{
					row:   i,
					col:   j,
					value: v2,
				}
				sparseArra = append(sparseArra, nodeValue)
			}
		}
	}

	//打印稀疏数组
	for _, node := range sparseArra {
		fmt.Printf("%d\t%d\t%d\n", node.row, node.col, node.value)
	}

	//将稀疏数组解析为原始数组
	fmt.Println("\n解析稀疏数组:")

	//创建原始数组（声明为一个切片）
	arr2 := make([][11]int, sparseArra[0].row)

	//遍历稀疏数组
	for i, v := range sparseArra {
		//跳过稀疏数组的第一行数据
		if i == 0 {
			continue
		}
		arr2[v.row][v.col] = v.value
	}

	//遍历原始数组
	for _, v := range arr2 {
		for _, v2 := range v {
			fmt.Printf("%d\t", v2)
		}
		fmt.Println()
	}
}
