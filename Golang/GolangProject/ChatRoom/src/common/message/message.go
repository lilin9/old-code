package message

//定义消息类型常量
const (
	LoginMessageType        = "LoginMessage"
	LoginResultType         = "LoginResult"
	RegisterMessageType     = "RegisterMessage"
	RegisterResMesType      = "RegisterResultMessage"
	NotifyUserStatusMsgType = "NotifyUserStatusMessage"
	SmsMessageType          = "SmsMessage"
)

//定义用户在线状态常量
const (
	UserOnline     = iota //用户在线
	UserOffline           //用户下线
	UserBusyStatus        //用户繁忙
)

type Message struct {
	Type string `json:"type"` //消息的类型
	Data string `json:"data"` //消息的数据
}

type LoginMessage struct {
	UserId   int    `json:"userId"`   //用户id1
	UserPwd  string `json:"userPwd"`  //用户密码
	Username string `json:"username"` //用户名
}

type LoginResult struct {
	Code    int    `json:"code"`    //状态码: 500 未注册; 200 登录成功
	UsersId []int  `json:"usersId"` //增加字段，用来保存用户 id 的切片
	Error   string `json:"error"`   //错误信息
}

type RegisterMessage struct {
	User User `json:"user"`
}

type RegisterResultMessage struct {
	Code  int    `json:"code"`  //状态码: 400 该用户已被占用; 200 注册成功
	Error string `json:"error"` //错误信息
}

//NotifyUserStatusMessage 为了配合服务器推送用户状态变化的消息
type NotifyUserStatusMessage struct {
	UserId int `json:"userId"` //用户id
	Status int `json:"status"` //用户状态
}

//SmsMessage 用于发送消息
type SmsMessage struct {
	User           //发送消息的用户信息
	Content string `json:"content"` //要发送的消息内容
}
