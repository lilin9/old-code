package main

import (
	"fmt"
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

	//添加
	//user1 := &User{Name: "tom", Age: 12}
	//db.Create(user1)

	//查询
	var user *User
	db.First(&user)
	fmt.Printf("Id: %d\nName: %s\nAge: %d\nCreatedAt: %v\nUpdatedAt: %v\nDeletedAt: %v\n\n",
		user.ID, user.Name, user.Age, user.CreatedAt, user.UpdatedAt, user.DeletedAt)

	//条件查询
	var user1 *User
	db.Where("name = ?", "tom").First(&user1)
	fmt.Printf("Id: %d\nName: %s\nAge: %d\nCreatedAt: %v\nUpdatedAt: %v\nDeletedAt: %v",
		user.ID, user.Name, user.Age, user.CreatedAt, user.UpdatedAt, user.DeletedAt)
}
