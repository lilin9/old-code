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

	user := &User{}
	user.ID = 1

	//删除
	//db.Debug().Delete(user)

	//条件删除
	//db.Where("name=?", "jerry").Delete(&User{})

	//物理删除
	db.Unscoped().Delete(user)
}
