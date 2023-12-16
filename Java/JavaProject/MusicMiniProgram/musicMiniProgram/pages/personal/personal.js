import request from '../../utils/request'

let startY = 0; //手指起始坐标
let moveY = 0; //手指移动后的坐标
let moveDistance = 0; //手指移动的距离
Page({

  /**
   * 页面的初始数据
   */
  data: {
    coverTransform: 'translateY(0)',  //元素的滑动距离
    coverTransition: '',              //元素回复时的过渡动画设置
    userInfo: {},                     //用户个人信息
    userRecentPlayList: [],               //用户播放记录
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //获取用户信息，从本地
    let userInfo = wx.getStorageSync('userInfo');
    if (userInfo) {
      this.setData({
        userInfo: JSON.parse(userInfo)
      });
      //获取用户播放记录
      this.getUserRecentPlayList(this.data.userInfo.userId);
    }
  },

  /**
   * 获取用户播放记录数据
   */
  async getUserRecentPlayList(userId) {
    let userRecentPlayListData = await request("/user/record", {uid: userId, type: 0});
    let index = 0;
    let userRecentPlayList = userRecentPlayListData.allData.splice(0, 20).map(item => {
      item.id = index++;
      return item;
    })
    this.setData({
      userRecentPlayList,
    })
  },

  /**
   * 跳转到登录页点击事件
   */
  toLogin() {
    wx.navigateTo({
      url: '/pages/login/login',
    })
  },

  /* 元素下拉动画设置-start */
  touchStart(event) {
    //取消滑动过渡效果
    this.setData({
      coverTransition: ''
    })
    //获取手指起始坐标
    startY = event.touches[0].clientY;
  },

  touchMove(event) {
    //获取手指滑动后的坐标
    moveY = event.touches[0].clientY;
    moveDistance = moveY - startY;

    //如果向上滑动，禁止动画触发
    if (moveDistance <= 0 ) return;
    //当向下滑动超过80，则终止滑动动画
    if (moveDistance >= 80) moveDistance = 80;

    this.setData({
      //设置滑动效果，元素跟随手指向下滑动
      coverTransform: `translateY(${moveDistance}rpx)`
    })
  },
  
  touchEnd() {
    this.setData({
      //回复元素滑动为初始值
      coverTransform: 'translateY(0)',
      //设置元素回复时的过渡动画
      coverTransition: 'transform 0.5s linear'
    })
  },
  /* 元素下拉动画设置-end */

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