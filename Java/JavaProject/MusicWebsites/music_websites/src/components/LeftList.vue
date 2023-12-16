<template>
  <div class="left-list">
    <ul>
      <p>推荐</p>
      <li><a href="#"><img src="/img/每日推荐.png" alt="" />每日推荐</a></li>
      <li><a href="#"><img src="/img/搜索.png" alt="" />歌曲搜索</a></li>
    </ul>
    <ul>
      <p>我的音乐</p>
      <li><a href="#"><img src="/img/收藏.png" alt="" />我的收藏</a></li>
      <li><a href="#"><img src="/img/电台.png" alt="" />我的电台</a></li>
      <li><a href="#"><img src="/img/下载.png" alt="" />本地与下载</a></li>
      <li><a href="#"><img src="/img/云盘.png" alt="" />音乐云盘</a></li>
      <li><a href="#"><img src="/img/时间.png" alt="" />最近播放</a></li>
    </ul>
    <ul>
      <p>美化</p>
      <li><a href="#"><img src="/img/主题.png" alt="" />主题</a></li>
    </ul>
    <ul>
      <p>自建歌单</p>
      <li><a href="#"><img src="/img/我喜欢.png" alt="" />我喜欢</a></li>
      <li><a href="#"><img src="/img/可莉.jpg" alt="" />默认歌单</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "LeftList",
};
</script>

<style scoped>
.left-list {
  margin-left: -10px;
  text-align: left;
  height: 100%;
  overflow: scroll;
}
.left-list ul p {
  color: #999999;
  padding: auto;
}
.left-list img {
  width: 20px;
  height: 20px;
  margin-right: 5px;
  vertical-align: middle;
}
.left-list ul {
  list-style-type: none;
}
.left-list li {
  margin-top: 6px;
  margin-bottom: 6px;
}
.left-list li a {
  text-decoration: none;
  color: #fff;
}
</style>