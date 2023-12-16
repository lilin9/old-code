<template>
  <div class="app">
    <category title="美食">
      <img src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="">
    </category>
    <category title="游戏">
      <ul>
        <li v-for="(item,index) in games" :key="index">{{item}}</li>
      </ul>
    </category>
    <category title="电影">
      <video controls src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
    </category>
  </div>
</template>

<script>
import category from './components/Category.vue'
export default {
  name: "App",
  components: {category},
  data() {
    return {
      foods: ['火锅','烧烤','小龙虾','牛排'],
      games: ['战地','巫师三','我的世界','塞尔达传说'],
      films: ['星际穿越','超体','湮灭','盗梦空间']
    }
  },
};
</script>

<style lang="css">
  .app {
    display: flex;
    justify-content: space-around;
  }
  h3 {
    text-align: center;
    background-color: orange;
  }
  img {
      width: 100%;
  }
  video {
    width: 100%;
  }
</style>