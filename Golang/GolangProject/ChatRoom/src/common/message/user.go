package message

//User 定义 user 结构体
type User struct {
	UserId     int    `json:"userId"`
	UserName   string `json:"userName"`
	UserPwd    string `json:"userPwd"`
	UserStatus int    `json:"userStatus"`
}
