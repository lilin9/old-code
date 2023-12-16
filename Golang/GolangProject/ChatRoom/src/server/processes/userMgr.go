package processes

import "fmt"

//UserMgr 实例在服务器有且仅有一个，故将其定义为全局变量
var (
	userMgr *UserMgr
)

type UserMgr struct {
	onlineUser map[int]*UserProcess
}

//初始化 UserMgr
func init() {
	userMgr = &UserMgr{
		onlineUser: make(map[int]*UserProcess, 1024),
	}
}

/*
 定义对 UserMgr 的增删改查方法
*/

func (this *UserMgr) AddUpdateOnlineUser(userProcess *UserProcess) {
	this.onlineUser[userProcess.userId] = userProcess
}

func (this *UserMgr) DeleteOnlineUser(userId int) {
	delete(this.onlineUser, userId)
}

func (this *UserMgr) GetOnlineUser(userId int) (userProcess *UserProcess, err error) {
	userProcess, ok := this.onlineUser[userId]
	if !ok {
		err = fmt.Errorf("ID为 %d 的用户不存在", userId)
		return
	}
	return
}

func (this *UserMgr) GetAllOnlineUser() map[int]*UserProcess {
	return this.onlineUser
}
