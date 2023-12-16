// 专门用于发送 ajax 请求
import config from './config'

export default (url, data={}, method="GET") => {
  return new Promise((resolve, reject) => {
    wx.request({
      url: config.host + url,
      data,
      method,
      header: {
        cookie: 
        wx.getStorageSync('cookies') ? wx.getStorageSync('cookies').find(item => item.indexOf('MUSIC_U') !== -1) : ''
      },
      success: (res) => {
        //如果用户登录了，将用户cookie存入本地
        if(data.isLogin) {
          wx.setStorage({
            key: "cookies",
            data: res.cookies
          })
        }

        resolve(res.data); //修改 promise 的状态为成功状态
      },
      fail: (err) => {
        reject(err); //修改 promise 的状态为失败状态
      }
    })
  })
}