package process

import (
	"Project/ChatRoom/src/client/model"
	"Project/ChatRoom/src/common/message"
	"fmt"
)

//客户端需要维护的 map
var onlineUsers = make(map[int]*message.User, 10)

//在用户登录成功后，对 CurUser 进行初始化
var curUser model.CurUser

//在客户端显示当前在线用户
func outputOnlineUser() {
	fmt.Println("当前在线的用户列表: ")
	//遍历 onlineUsers
	for id := range onlineUsers {
		fmt.Println("id = ", id)
	}
}

//此函数用来处理返回的 NotifyUserStatusMes
func updateUserStatus(notifyUserStatusMsg *message.NotifyUserStatusMessage) {
	userId := notifyUserStatusMsg.UserId

	user, ok := onlineUsers[userId]
	if !ok {
		user = &message.User{
			UserId: userId,
		}
	}
	user.UserStatus = notifyUserStatusMsg.Status
	onlineUsers[userId] = user

	outputOnlineUser()
}
