import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    videoGroupList: [],   //导航标签数据
    videoList: [],        //导航标签下的视频列表
    navId: '',            //导航栏标识
    videoUrl: {},         //视频链接地址
    videoId: '',          //视频 id
    videoUpdateTime: [],  //记录 video 播放的时长
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //获取导航标签数据
    this.getVideoGroupList();
  },
  
  /**
   * 获取导航标签数据
   */
  async getVideoGroupList() {
    let videoGroupListData = await request('/video/group/list');
    this.setData({
      videoGroupList: videoGroupListData.data.slice(0, 14),
      navId: videoGroupListData.data[0].id
    })
    //获取导航标签下的视频列表
    this.getVideoList(this.data.navId);
  },

  /**
   * 切换导航栏标签的点击事件
   */
  navChange(event) {
    let navId = event.currentTarget.id;
    this.setData({
      navId: navId * 1,  //乘1是为了将 String 类型的 navid 转换为 number 类型
      videoList: []
    })
    //显示加载动画
    wx.showLoading({
      title: '正在加载',
    })

    //动态获取当前导航标签下的数据
    this.getVideoList(this.data.navId);
  },

  /**
   * 获取导航标签下的视频列表
   */
  async getVideoList(navId) {
    //获取视频列表
    let videoListData = await request('/video/group', {id: navId});

    //关闭加载动画
    wx.hideLoading(); 

    let index = 0;
    let videoDatas = videoListData.datas;
    //循环遍历视频列表数据，为其添加 id 和视频播放地址 vUrl
    for (let i = 0; i < videoDatas.length; i++) {
      //视频id
      let vid = videoDatas[i].data.vid;
      //发送请求获取视频播放地址
      let urlData = await request('/video/url', {id: vid});
      // //将 id 和 url 添加到视频数据中去
      videoDatas[i]['id'] = index++;
      videoDatas[i].data['vUrl'] = urlData.urls[0].url;
    }
    //更新 videoList 的状态信息
    this.setData({
      videoList: videoDatas
    })
  },

  /**
   * 视频播放的回调
   */
  handlerPlay(event) {
    //获取视频id
    let vid = event.currentTarget.id;
    //关闭上一个播放的视频
    // this.vid !== vid && this.videoContext && this.videoContext.stop();

    // this.vid = vid;
    //更新 videoId 的状态信息
    this.setData({
      videoId: vid
    });

    //创建控制 video 标签的实例对象
    this.videoContext = wx.createVideoContext(vid);

    //判断当前的视频是否在之前播放过，是否有播放记录
    let {videoUpdateTime} = this.data;
    let videoItem = videoUpdateTime.find(item => item.vid === vid);
    if(videoItem) {
      //如果有，则跳转至指定的播放位置
      this.videoContext.seek(videoItem.currentTime);
    }

    //视频加载出来后自动播放
    this.videoContext.play();
  },

  /**
   * 监听视频播放进度的回调函数
   */
  timeUpdate(event) {
    let videoTimeObj = {vid: event.currentTarget.id, currentTime: event.detail.currentTime};
    let {videoUpdateTime} = this.data;
    //判断记录播放时长的 videoUpdateTime 数组中是否有当前视频的播放记录
    let videoItem = videoUpdateTime.find(item => item.vid === videoTimeObj.vid);
    if(videoItem) {
      //如果有，在原有播放记录上修改播放时间为当前的播放时间
      videoItem.currentTime = videoTimeObj.currentTime;
    } else {
      //如果没有，需要在数组中添加当前视频的播放对象
      videoUpdateTime.push(videoTimeObj);
    }
    
    //更新 videoUpdateTime 的状态
    this.setData({
      videoUpdateTime
    });
  },

  /**
   * 在视频播放结束时触发
   */
  videoPlayEnded(event) {
    //移除播放结束的视频
    let {videoUpdateTime} = this.data;
    videoUpdateTime.splice(videoUpdateTime.findIndex(item => {event.currentTarget.id === item.vid}), 1);
    this.setData({
      videoUpdateTime
    });
  },

  /**
   * 自定义上拉加载
   */
  handlerTolower() {
    console.log("handlerTolower --- 上拉加载");
    console.log("因不可抗原因，此功能无法实现\n");
  },

  /**
   * 跳转到搜索页面
   */
  toSearchView() {
    wx.navigateTo({
      url: '../search/search',
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