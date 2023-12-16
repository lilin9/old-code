package utils

import (
	"Project/ChatRoom/src/common/message"
	"encoding/binary"
	"encoding/json"
	"errors"
	"fmt"
	"net"
)

/*
工具类
*/

//Transfer 方法关联到结构体中
type Transfer struct {
	Conn net.Conn
	Buf  [8096]byte
}

//WritePkg 这里进行消息体的写入发送
func (this *Transfer) WritePkg(data []byte) (err error) {
	//先发送一个长度给对方
	var pkgLen uint32
	pkgLen = uint32(len(data))
	binary.BigEndian.PutUint32(this.Buf[:4], pkgLen)

	//发送长度
	n, err := this.Conn.Write(this.Buf[:4])
	if n != 4 || err != nil {
		fmt.Println("writePkg() 发送数据长度错误: ", err)
		return
	}

	//发送数据本身
	n, err = this.Conn.Write(data)
	if uint32(n) != pkgLen || err != nil {
		fmt.Println("writePkg() 发送数据错误: ", err)
		return
	}

	return
}

//ReadPkg 这里解析消息体中包含的数据信息
func (this *Transfer) ReadPkg() (msg message.Message, err error) {
	_, err = this.Conn.Read(this.Buf[0:4])
	if err != nil {
		//err = errors.New("读取消息头错误")
		return
	}

	//将 buf[:4] 转成 uint32 类型数据
	var pkgLen uint32
	pkgLen = binary.BigEndian.Uint32(this.Buf[0:4])
	//根据 pkgLen 的长度读取消息体内容
	n, err := this.Conn.Read(this.Buf[:pkgLen])
	if uint32(n) != pkgLen || err != nil {
		err = errors.New("读取消息体内容错误")
		return
	}

	//将读取到的 buf[:pkgLen] 反序列化成原本的结构体类型
	err = json.Unmarshal(this.Buf[:pkgLen], &msg)
	if err != nil {
		fmt.Println("反序列化 buf[:pkgLen] 失败: ", err)
		return
	}

	return
}
