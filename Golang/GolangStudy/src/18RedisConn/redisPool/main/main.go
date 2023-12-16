package main

import (
	"fmt"
	"github.com/garyburd/redigo/redis"
)

//定义一个全部的连接池常量
var pool *redis.Pool

//当程序启动时，就初始化 redis 连接池
func init() {
	pool = &redis.Pool{
		MaxIdle:     8,   //最大空闲连接数
		MaxActive:   0,   //表示和数据库的最大连接数，0 表示没有限制
		IdleTimeout: 100, //最大空闲时间
		Dial: func() (redis.Conn, error) { //初始化连接的代码
			return redis.Dial("tcp", "192.168.200.129:6379")
		},
	}
}

func main() {
	//从连接池中获取一个连接
	coon := pool.Get()
	defer coon.Close()

	_, err := coon.Do("Set", "name2", "smith")
	if err != nil {
		fmt.Println("set error: ", err)
		return
	}

	fmt.Println("set success")

	name, err := redis.String(coon.Do("Get", "name2"))
	if err != nil {
		fmt.Println("get error: ", err)
		return
	}
	fmt.Println("get success and name is ", name)
}
