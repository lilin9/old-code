import request from '../../utils/request'
let isSend = false  //函数节流使用
Page({

  /**
   * 页面的初始数据
   */
  data: {
    placeholderContent: '', //placeholder的内容
    hotList: [],  //热搜榜列表
    searchContent: '',  //用户输入的表单项内容
    searchList: [],   //搜索关键字查询到的数据
    historyList: [],  //搜索记录
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //获取初始化数据
    this.getInitData();
    //从本地获取搜索历史
    this.getHistoryList();
  },

  /**
   * 获取初始化数据
   */
  async getInitData() {
    //获取 placeholder 默认内容
    let placeholderContentData = await request('/search/default');
    //获取热搜榜内容列表
    let hotListData = await request('/search/hot/detail');
    this.setData({
      placeholderContent: placeholderContentData.data.showKeyword,
      hotList: hotListData.data
    });
  },

  /**
   * 表单项内容发生改变事件
   */
  inputChange(event) {
    //更新 searchContent 的状态内容
    this.setData({
      searchContent: event.detail.value.trim()
    });

    //使用函数节流限制搜索框请求发送次数
    //确保函数只会发送一次请求
    if(isSend) return;
    isSend = true;

    //通过关键字模糊搜索查询数据
    this.getSearchList();
    
    //保证在定时器结束之后，再发一次请求
    setTimeout(() => {
      isSend = false;
    }, 1000);
  },

  /**
   * 通过关键字模糊搜索查询数据
   */
  async getSearchList() {
    //如果没有内容，就不发送请求
    if(!this.data.searchContent) return;

    let {searchContent, historyList} = this.data;

    //通过关键字模糊搜索查询数据
    let searchListData = await request('/cloudsearch', {keywords: searchContent, limit: 10});
    //更新 searchList 的状态内容
    this.setData({
      searchList: searchListData.result.songs
    });

    //将搜索关键词添加到搜索列表中
    //在添加之前，确认列表中是否已经存在记录
    if(historyList.indexOf(searchContent) !== -1) {
      //如果有，将旧的记录删除
      historyList.splice(historyList.indexOf(searchContent), 1)
    }
    //将搜索记录倒序添加到列表中
    historyList.unshift(searchContent);
    this.setData({
      historyList
    });
    //将搜索记录保存到本地
    wx.setStorageSync('historyList', historyList);
  },

  /**
   * 从本地获取搜索历史
   */
  getHistoryList() {
    //从本地得到历史记录
    let historyList = wx.getStorageSync('historyList');
    //同步到列表中
    if(historyList) {
      this.setData({
        historyList
      });
    }
  },

  /**
   * 清空搜索框里的内容
   */
  clearSearchContent() {
    this.setData({
      searchContent: ''
    });
  },

  /**
   * 一键清空搜索记录
   */
  deleteSearchHistory() {
    wx.showModal({
      content: '确认删除历史记录？',
      success: (res) => {
        if(res.confirm) {
          //清空 data 中的 historyList
          this.setData({
            historyList: []
          });
          //清空本地保存的历史记录
          wx.removeStorageSync('historyList');
        }
      }
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