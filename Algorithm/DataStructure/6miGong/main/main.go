package main

import "fmt"

//寻路函数
func getWay(migongMap *[8][7]int, i, j int) bool {
	//当找到终点后
	if migongMap[6][5] == 2 {
		return true
	} else {
		//如果不是墙，即前方的道路为 0
		if migongMap[i][j] == 0 {
			//假设当前停留的一点是可以通过的
			migongMap[i][j] = 2
			//对当前点的上下左右四个方向进行探测
			if getWay(migongMap, i+1, j) { //下
				return true
			} else if getWay(migongMap, i, j+1) { //右
				return true
			} else if getWay(migongMap, i-1, j) { //上
				return true
			} else if getWay(migongMap, i, j-1) { //左
				return true
			} else { //四个方向都不通，说明是死路
				migongMap[i][j] = 3
				return false
			}
		} else {
			//返回 false，说明前路不通
			return false
		}
	}
}

func main() {
	//定义数组
	var migongMap [8][7]int

	//构建游戏地图
	//将最上和最下置为 1
	for i := 0; i < 7; i++ {
		migongMap[0][i] = 1
		migongMap[7][i] = 1
	}

	//将最左和最右置为 1
	for i := 0; i < 8; i++ {
		migongMap[i][0] = 1
		migongMap[i][6] = 1
	}
	migongMap[2][1] = 1
	migongMap[2][2] = 1

	//打印地图
	for _, v := range migongMap {
		for _, v1 := range v {
			fmt.Printf("%d  ", v1)
		}
		fmt.Println()
	}

	fmt.Println("\n探索地图:")

	//开始走迷宫
	getWay(&migongMap, 1, 1)
	//打印地图
	for _, v := range migongMap {
		for _, v1 := range v {
			fmt.Printf("%d  ", v1)
		}
		fmt.Println()
	}

}
