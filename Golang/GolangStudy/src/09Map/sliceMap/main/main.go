package main

import "fmt"

func main() {
	//声明一个 map 切片
	var monsters []map[string]string
	//准备放入两个妖怪信息
	monsters = make([]map[string]string, 2)

	//增加第一个妖怪信息
	if monsters[0] == nil {
		monsters[0] = make(map[string]string, 2)
		monsters[0]["name"] = "狐狸精"
		monsters[0]["age"] = "1000"
	}

	//增加第二个妖怪信息
	if monsters[1] == nil {
		monsters[1] = make(map[string]string, 2)
		monsters[1]["name"] = "蝎子精"
		monsters[1]["age"] = "500"
	}

	newMonsters := map[string]string{
		"name": "孙悟空",
		"age":  "1000",
	}
	monsters = append(monsters, newMonsters)

	fmt.Println(monsters)
}
