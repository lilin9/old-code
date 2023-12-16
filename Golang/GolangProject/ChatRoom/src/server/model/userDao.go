package model

import (
	"Project/ChatRoom/src/common/message"
	"encoding/json"
	"fmt"
	"github.com/garyburd/redigo/redis"
)

/*
定义 UserDao 结构体，完成对 User 结构体的各种操作
*/

//MyUserDao 定义全局变量，以便在程序各处使用
var (
	MyUserDao *UserDao
)

type UserDao struct {
	pool *redis.Pool
}

//NewUserDao 使用工厂模式，创建一个 userDao 实例
func NewUserDao(pool *redis.Pool) (userDao *UserDao) {
	userDao = &UserDao{
		pool: pool,
	}
	return
}

//根据用户 id 返回一个用户实例或者 error
func (this *UserDao) getUserById(conn redis.Conn, id int) (user *User, err error) {
	//根据用户 id 查询 redis 中的用户信息
	result, err := redis.String(conn.Do("HGet", "users", id))
	if err != nil || err == redis.ErrNil { //err == redis.ErrNil 根据用户id在redis中没有找到对应的数据
		err = ErrorUserNotExists
		return
	}

	//将 查询出的结果 result 反序列化为对应的结构体
	user = &User{}
	err = json.Unmarshal([]byte(result), user)
	if err != nil {
		fmt.Println("server userDao getUserById() 反序列化失败: ", err)
		return
	}
	return
}

/*
Login 此方法完成登录校验功能：
1. 如果用户的 id 和 pwd 都正确，返回一个 user 实例
2. 如果用户的 id 和 pwd 有错误，返回对应的错误信息
*/
func (this *UserDao) Login(userId int, userPwd string) (user *User, err error) {
	//先从 redis 连接池中取出一个 conn
	conn := this.pool.Get()

	//从 redis 中获取用户实例
	user, err = this.getUserById(conn, userId)
	if err != nil {
		return
	}

	//比较用户密码是否正确
	if user.UserPwd != userPwd {
		//错误返回一个 err
		err = ErrorUserPwd
		return
	}
	return
}

/*
Register 此方法完成注册功能
*/
func (this *UserDao) Register(user *message.User) (err error) {
	//先从 redis 连接池中取出一个 conn
	conn := this.pool.Get()

	//从 redis 中获取用户实例
	_, err = this.getUserById(conn, user.UserId)
	if err == nil {
		err = ErrorUserExists
		return
	}

	//数据库中用户不存在，可以进行注册
	//序列化用户数据
	data, err := redis.String(json.Marshal(user))
	if err != nil {
		fmt.Println("server UserDao Register() 序列化用户数据失败: ", err)
		return
	}

	//将用户数据保存入数据库
	_, err = conn.Do("HSet", "users", user.UserId, data)
	if err != nil {
		fmt.Println("server UserDao Register() 写入用户数据到数据库失败: ", err)
	}
	return
}
