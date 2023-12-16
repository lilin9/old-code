import PubSub from 'pubsub-js'
import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    day: '',            //天
    month: '',          //月
    recommendList: [],  //推荐列表数据
    index: 0,           //点击音乐的下标
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //判断用户是否登录
    let userInfo = wx.getStorageSync('userInfo');
    if(!userInfo) {
      //如果没登录，就跳到登陆页面
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        success: () => {
          wx.reLaunch({
            url: '/pages/login/login',
          })
        }
      })
    }

    //更新日期数据
    this.setData({
      day: new Date().getDate(),
      month: new Date().getMonth() + 1
    });

    //获取推荐的歌曲列表数据
    this.getRecommendList();

    //订阅来自 songPlay 页面发布的消息
    PubSub.subscribe('switchType', (msg, type) => {
      let {index, recommendList} = this.data;
      if(type === 'pre') {
        //如果点击上一首歌曲，就获取上一首歌曲的下标
        (index === 0) && (index = recommendList.length)
        index -= 1;
      } else {
        //如果点击下一首歌曲，就获取下一首歌曲的下标
        (index === recommendList.length - 1) && (index = -1)
        index += 1;
      }
      //更新下标值
      this.setData({
        index
      });
      //得到点击歌曲的id
      let musicId = recommendList[index].id;
      //将得到的歌曲id发布给 songPlay 页面
      PubSub.publish('musicId', musicId);
    });
  },

  /**
   * 获取推荐歌曲列表
   */
  async getRecommendList() {
    let recommendListData = await request('/recommend/songs');
    this.setData({
      recommendList: recommendListData.data.dailySongs
    });
  },

  /**
   * 点击歌曲跳转到播放页面
   */
  toSongDetail(event) {
    let {song, index} = event.currentTarget.dataset;
    this.setData({
      index
    });

    wx.navigateTo({
      //原生小程序中路由传参，会对参数的长度有限制，如果参数长度过长会自动截取掉之后的内容
      // url: '../songPlay/songPlay?song=' + JSON.stringify(song),
      url: '../songPlay/songPlay?id=' + song.id,
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