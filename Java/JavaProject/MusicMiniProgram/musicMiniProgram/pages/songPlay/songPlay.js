import moment from 'moment';
import PubSub from 'pubsub-js'
import request from '../../utils/request'
//获取全局实例
const appInstance = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isPlay: false,  //音乐是否播放
    song: {}, //音乐数据
    musicId: '',  //音乐id
    songLink: '', //音乐链接
    currentTimer: '00:00', //歌曲实时播放时长
    durationTimer: '00:00',  //歌曲总时长
    currentWidth: 0,  //实时进度条长度
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let musicId = options.id;
    this.setData({
      musicId
    });
    //获取音乐数据
    this.getMusicData(this.data.musicId);

    //判断当前页面的音乐是否在播放
    if(appInstance.globalData.isMusicPlay && appInstance.globalData.musicId === musicId) {
      //修改音乐播放状态
      this.setData({
        isPlay: true
      });
    }
    
    //创建控制音乐播放的实例
    this.backgroundAudioManager = wx.getBackgroundAudioManager();

    //监听音乐的播放
    this.backgroundAudioManager.onPlay(() => {
      this.changePlayState(true);
      //修改全局音乐播放的状态
      appInstance.globalData.musicId = musicId;
    });

    //监听音乐的暂停
    this.backgroundAudioManager.onPause(() => {
      this.changePlayState(false);
    });

    //监听音乐的停止
    this.backgroundAudioManager.onStop(() => {
      this.changePlayState(false);
    });

    //监听音乐实时播放进度
    this.backgroundAudioManager.onTimeUpdate(() => {
      //格式化实时播放时间
      let currentTimer = moment(this.backgroundAudioManager.currentTime * 1000).format('mm:ss');
      //根据播放时长实时计算歌曲播放进度条长度
      let currentWidth = (this.backgroundAudioManager.currentTime / this.backgroundAudioManager.duration) * 420;
      //更新数据状态
      this.setData({
        currentTimer,
        currentWidth
      });
    });

    //监听背景音乐自然结束
    this.backgroundAudioManager.onEnded(() => {
      //自动切换1下一首音乐，并且自动播放
      PubSub.publish('switchType', 'next');
      //将实时进度条长度还原成 0
      this.setData({
        currentWidth: 0,
        currentTimer: '00:00'
      });
    });
  },

  /**
   * 控制音乐 播放 / 暂停 的回调函数
   */
  musicPlay() {
    let isPlay = !this.data.isPlay;
    
    let {musicId, songLink} = this.data;
    //控制音乐播放与暂停
    this.musicControler(isPlay, musicId, songLink);
  },
  /**
   * 控制音乐 播放 / 暂停 的功能函数
   */
  async musicControler(isPlay, musicId, songLink) {
    if(isPlay) {  //音乐播放
      //如果没有音乐链接
      if(!songLink) {
        //获取歌曲播放链接
        let songLinkData = await request('/song/url', {id: musicId});
        songLink = songLinkData.data[0].url;
        //将链接更新到 songLink 中
        this.setData({
          songLink
        });
      }
      //设置音乐播放器的链接
      this.backgroundAudioManager.src = songLink;
      //设置音乐播放器的标题
      this.backgroundAudioManager.title = this.data.song.name;
    } else {      //音乐暂停
      this.backgroundAudioManager.pause();
    }
  },

  /**
   * 获取音乐数据
   */
  async getMusicData(musicId) {
    //获取歌曲详情信息
    let songData = await request('/song/detail', {ids: musicId});

    //获取歌曲播放时长，并将其格式化
    let durationTimer = moment(songData.songs[0].dt).format('mm:ss');

    this.setData({
      song: songData.songs[0],
      durationTimer
    });

    //更新窗口标题为歌名
    wx.setNavigationBarTitle({
      title: this.data.song.name,
    })
  },

  /**
   * 修改播放状态的功能函数
   */
  changePlayState(isPlay) {
    this.setData({
      isPlay
    });
    //修改全局音乐播放状态
    appInstance.globalData.isMusicPlay = isPlay;
  },

  /**
   * 切换歌曲回调
   */
  handleSwitch(event) {
    //关闭当前播放的音乐
    this.backgroundAudioManager.stop();
    //获取点击的歌曲 id
    PubSub.subscribe('musicId', (msg, musicId) => {
      this.getMusicData(musicId);
      //自动播放当前音乐
      this.musicControler(true, musicId);
      //取消订阅，避免重复订阅
      PubSub.unsubscribe('musicId');
    });
    
    //获取切歌的类型
    let type = event.currentTarget.id;
    //发布消息给 recommendSong 页面
    PubSub.publish('switchType', type);
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