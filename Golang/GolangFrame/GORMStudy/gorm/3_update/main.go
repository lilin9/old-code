package main

import (
	"gorm.io/gorm"
	"gorm/utils"
)

type User struct {
	gorm.Model
	Name string
	Age  int
}

func main() {
	db := utils.DB

	//关联数据库
	err := db.AutoMigrate(&User{})
	if err != nil {
		panic("连接数据库失败")
	}

	var user User
	//修改
	user.Name = "tom"
	user.Age = 12
	//查找
	db.First(&user)

	//db.Debug().Save(&user1)
	db.Debug().Model(&user).Update("name", "smith")

}
