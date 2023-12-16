package main

import (
	"database/sql"
	"gorm/utils"
)

type User struct {
	ID   int
	Name sql.NullString `gorm:"default:user"`
	Age  int
}

// TableName 生成表时禁用复数形式
func (User) TableName() string {
	return "user"
}

func main() {
	db := utils.DB
	//迁移数据库表
	err := db.AutoMigrate(&User{})
	if err != nil {
		panic("utils.DB.AutoMigrate(&User{}) error")
		return
	}

	//user1 := &User{Age: 19}
	////插入数据
	//db.Create(&user1)

	//当 Name 属性设置了默认值时，往数据表user中插入Name为空的数据
	user2 := &User{
		Name: sql.NullString{
			String: "",   //插入一个空值
			Valid:  true, //true表示不使用结构体的默认值
		},
		Age: 27,
	}
	db.Create(&user2)
}
