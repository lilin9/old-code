package main

import (
	"github.com/garyburd/redigo/redis"
	"time"
)

//定义全局化 pool
var pool *redis.Pool

//对 pool 的初始化工作
func initPool(maxIdle, maxActive int, idleTimeout time.Duration, address string) {
	pool = &redis.Pool{
		MaxIdle:     maxIdle,     //最大空闲连接数
		MaxActive:   maxActive,   //表示和数据库的最大连接数，0 表示没有限制
		IdleTimeout: idleTimeout, //最大空闲时间
		Dial: func() (redis.Conn, error) { //初始化连接的代码
			return redis.Dial("tcp", address)
		},
	}
}
