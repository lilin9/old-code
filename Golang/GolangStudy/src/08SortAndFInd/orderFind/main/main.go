package main

import "fmt"

/*
顺序查找完成猜数游戏
*/
func main() {
	strSort := [4]string{"白眉鹰王", "金毛狮王", "紫衫龙王", "青翼蝠王"}

	var inputStr string
	fmt.Print("输入：")
	fmt.Scanln(&inputStr)

	index := -1
	for i := 0; i < len(strSort); i++ {
		if inputStr == strSort[i] {
			index = 1
			break
		}
	}

	if index != -1 {
		fmt.Println("找到了")
	} else {
		fmt.Println("找不到")
	}
}
