/* 电台 */
<template>
  <!-- 顶部导航列表 start -->
  <div class="radio">
    <MenuList :menuData='radioMenuData' />
    <!-- 顶部导航列表 end -->
    <!-- 内容展示 start -->
    <VideoList :videoData='radioData'/>
    <!-- 内容展示 end -->
  </div>
</template>

<script>
import VideoList from '../components/video-list'
import MenuList from '../components/menu-list'
export default {
    name: "Radio",
    components: {VideoList, MenuList},
    data() {
      return {
        radioData: [
          {id: "1", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "2", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "3", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "4", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "5", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "6", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "7", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "8", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "9", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
          {id: "10", videoImgUrl: "/img/仙剑.jpg", videoName: "光棍", videoSinger: "胡歌", isShow: false,},
        ],
        radioMenuData: [
          {id: '1', value: '推荐'},
          {id: '2', value: 'DJ'},
          {id: '3', value: '语言'},
          {id: '4', value: '主题'},
          {id: '5', value: '场景'},
          {id: '6', value: '心情'},
          {id: '7', value: '风格'},
          {id: '8', value: '人群'},
        ]
      }
    }
}
</script>

<style scoped>
.radio {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.menu-list .el-menu-item {
  border-radius: 20px;
}
.menu-list .el-menu-item:hover {
  background-color: rgba(255, 255, 255, 0.4) !important;
}
</style>