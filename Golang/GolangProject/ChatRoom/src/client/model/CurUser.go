package model

import (
	"Project/ChatRoom/src/common/message"
	"net"
)

type CurUser struct {
	Conn         net.Conn      `json:"conn"` //连接服务器的连接
	message.User `json:"user"` //用户信息
}
