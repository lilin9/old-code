package main

import (
	"fmt"
	"github.com/garyburd/redigo/redis"
)

//getAndSet 往 redis 写入和读取数据
func getAndSet(conn redis.Conn) {
	//要记得关闭 conn
	defer conn.Close()

	//set
	_, err := conn.Do("Set", "name", "jerry")
	if err != nil {
		fmt.Println("set error:  ", err)
		return
	}
	fmt.Println("set success")

	//get
	//接收到的 result 是 interface{}，需要转换成 string
	name, err := redis.String(conn.Do("Get", "name"))
	if err != nil {
		fmt.Println("get error:  ", err)
		return
	}

	fmt.Println("get success, name=", name)
}

//hash 操作 hash 数据类型
func hash(conn redis.Conn) {
	//要记得关闭 conn
	defer conn.Close()

	//set hash
	_, err := conn.Do("HSet", "user", "name", "tom")
	if err != nil {
		fmt.Println("set hash error:  ", err)
		return
	}

	fmt.Println("set hash success")

	//get hash
	name, err := redis.String(conn.Do("HGet", "user", "name"))
	if err != nil {
		fmt.Println("get hash error:  ", err)
		return
	}

	fmt.Println("get hash success, name=", name)
}

func main() {
	conn, err := redis.Dial("tcp", "192.168.200.129:6379")

	if err != nil {
		fmt.Println("连接 redis 出错: ", err)
		return
	}
	//写入和读取数据
	//getAndSet(conn)
	//操作 hash
	hash(conn)
}
