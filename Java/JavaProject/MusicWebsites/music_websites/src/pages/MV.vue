/* MV */
<template>
  <div class="mv">
    <!-- 顶部导航列表 start -->
    <MenuList :menuData='mvMenuData' />
    <!-- 顶部导航列表 end -->
    <!-- mv start -->
    <VideoList :videoData='mvData' />
    <!-- mv end -->
  </div>
</template>

<script>
import VideoList from "../components/video-list";
import MenuList from '../components/menu-list';
export default {
  name: "MV",
  components: { VideoList, MenuList },
  data() {
    return {
      mvData: [
        {
          id: "1",
          videoImgUrl: "/img/周杰伦.jpg",
          videoName: "最伟大的作品",
          videoSinger: "周杰伦",
          isShow: false,
        },
        {
          id: "2",
          videoImgUrl: "/img/周杰伦.jpg",
          videoName: "最伟大的作品",
          videoSinger: "周杰伦",
          isShow: false,
        },
        {
          id: "3",
          videoImgUrl: "/img/周杰伦.jpg",
          videoName: "最伟大的作品",
          videoSinger: "周杰伦",
          isShow: false,
        },
        {
          id: "4",
          videoImgUrl: "/img/周杰伦.jpg",
          videoName: "最伟大的作品",
          videoSinger: "周杰伦",
          isShow: false,
        },
        {
          id: "5",
          videoImgUrl: "/img/周杰伦.jpg",
          videoName: "最伟大的作品",
          videoSinger: "周杰伦",
          isShow: false,
        },
        {
          id: "6",
          videoImgUrl: "/img/周杰伦.jpg",
          videoName: "最伟大的作品",
          videoSinger: "周杰伦",
          isShow: false,
        },
      ],
      mvMenuData: [
        {id: '1', value: '综合'},
        {id: '2', value: '单曲'},
        {id: '3', value: '视频'},
        {id: '4', value: '歌单'},
        {id: '5', value: '听书'},
        {id: '6', value: '专辑'},
        {id: '7', value: '歌词'},
      ],
    };
  },
};
</script>

<style>
.mv {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
</style>