package main

import "fmt"

/*
说明：
1、使用 map[string]map[string]string 的 map 类型
2、key：表示用户名，唯一，不可重复
3、如果某个用户名存在，就将其密码修改为 88888888，如果不存在，就增加这个用户的信息（包括昵称和密码）
4、编写一函数：modifyUser(users map[string]map[string]string, name string) 完成上述功能
*/

func modifyUser(users map[string]map[string]string, name string, passwd string) {
	//根据用户名获取信息
	userInfo := users[name]
	//判断是否存在
	if userInfo == nil {
		//如果不存在，就新建一个用户
		newUserInfo := map[string]string{
			"nickname": name,
			"passwd":   passwd,
		}
		users["name"] = newUserInfo
		return
	}

	//如果存在，就修改密码
	userInfo["passwd"] = "88888888"
}

func main() {
	users := make(map[string]map[string]string, 2)
	userInfo1 := make(map[string]string, 2)
	userInfo2 := make(map[string]string, 2)

	userInfo1["nickname"] = "tony"
	userInfo1["passwd"] = "123"

	userInfo2["nickname"] = "tom"
	userInfo2["passwd"] = "456"

	users["tony"] = userInfo1
	users["tom"] = userInfo2

	modifyUser(users, "jerry", "789")

	fmt.Println(users)
}
