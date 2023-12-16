import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phone: '',  //手机号
    password: ''  //用户密码
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },
  /**
   * 登录按钮的点击事件
   */
  async login() {
    //手机表单数据
    let {phone, password} = this.data;
    //验证手机号是否为空
    if (!phone) {
      wx.showToast ({
        title: '手机号不能为空',
        icon: 'none'
      });
      return;
    }
    //验证手机号合法性
    let checkPhone = /^1(3|4|5|6|7|8|9)\d{9}$/;
    if (!checkPhone.test(phone)) {
      wx.showToast ({
        title: '手机号格式错误',
        icon: 'none'
      });
      return;
    }

    //验证密码是否为空
    if (!password) {
      wx.showToast ({
        title: '密码不能为空',
        icon: 'none'
      });
      return;     
    }

    //后端登录验证
    let result = await request('/login/cellphone', {phone, password, isLogin:true})
    if (result.code === 200) {
      //提示信息
      wx.showToast({
        title: '登录成功',
      });
      //将用户信息存至本地
      wx.setStorageSync('userInfo', JSON.stringify(result.profile))

      //登录成功，跳转回个人中心页面
      wx.reLaunch({
        url: '../personal/personal',
      })
    } else if (result.code === 400) {
      wx.showToast({
        title: '手机号错误',
        icon: 'none'
      });
    } else if (result.code === 502) {
      wx.showToast({
        title: '密码错误',
        icon: 'none'
      });
    } else {
      wx.showToast({
        title: '登录失败，请重新登录',
        icon: 'none'
      });
    }
  },

  /**
   * 用户输入手机号和密码事件
   */
  handlerInput(event) {
    //获取用户输入值
    const type = event.currentTarget.id;
    //修改对象值
    this.setData ({
      [type] : event.detail.value
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})