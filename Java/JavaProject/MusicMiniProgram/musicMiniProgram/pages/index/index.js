import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bannerList: [], //轮播图数据
    recommendList: [],  //推荐歌单数据
    topList: [], //排行榜数据
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function(options) {
    //获取轮播图数据
      let bannerData = await request('/banner', {type: 1});
      this.setData({
        bannerList: bannerData.banners
      })
      //获取推荐歌单数据
      let recommendData = await request('/personalized', {limit: 10});
      this.setData({
        recommendList: recommendData.result
      })
      //获取排行榜数据
      let index = 0;
      let resultArr = [];
      while(index < 5) {
        let allTopData = await request('/toplist/detail');
        let topId = allTopData.list[index++].id;
        let top =  await request('/playlist/detail', {id: topId});
        let topData = {
          name: top.playlist.name,
          tracks: top.playlist.tracks.slice(0, 3)
        };
        resultArr.push(topData);

        //更新 topList 的状态数据，放在此处更新不需要等待五次请求全部结束后在更新，用户体验较好，但是渲染次数会增多
        // this.setData({
        //   topList: topData
        // })
      }
      //更新 topList 的状态数据，放在此处更新会导致发送请求的过程中页面长时间白屏，用户体验差
      this.setData({
        topList: resultArr
      })
  },

  /**
   * 跳转到歌曲推荐页面的点击事件
   */
  toRecommendSong() {
    wx.navigateTo({
      url: '../recommendSong/recommendSong',
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