<template>
  <div class="app">
    <category title="美食">
      <img slot="slot1" src="https://s3.ax1x.com/2021/01/16/srJlq0.jpg" alt="">
      <div class="footer" slot="slot2">
        <a href="https://cn.bing.com/">更多美食</a>
      </div>
    </category>

    <category title="游戏">
      <ul slot="slot1">
        <li v-for="(item,index) in games" :key="index">{{item}}</li>
      </ul>
      <div class="footer" slot="slot2">
        <a href="https://cn.bing.com/">网络游戏</a>
        <a href="https://cn.bing.com/">单机游戏</a>
      </div>
    </category>

    <category title="电影">
      <video controls slot="slot1" src="http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"></video>
      <template slot="slot2">
        <div class="footer">
          <a href="https://cn.bing.com/">经典</a>
          <a href="https://cn.bing.com/">热门</a>
          <a href="https://cn.bing.com/">推荐</a>
        </div>
        <h4>欢迎来看电影</h4>  
      </template>
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
  .app, .footer {
    display: flex;
    justify-content: space-around;
  }
  
  h3 {
    text-align: center;
    background-color: orange;
  }

  h4 {
    text-align: center;
  }
</style>