/* 音乐 */
<template>
  <div class="music">
    <!-- 轮播图 start -->
    <div class="img-list">
      <el-carousel :interval="4000" height="200px" class="img-list-left">
        <el-carousel-item v-for="item in imgListLeft" :key="item.key">
          <img :src="item.value" alt="" />
        </el-carousel-item>
      </el-carousel>
      <el-carousel
        :interval="4000"
        type="card"
        height="200px"
        class="img-list-right"
      >
        <el-carousel-item v-for="item in imgListRight" :key="item.key">
          <img :src="item.value" alt="" />
        </el-carousel-item>
      </el-carousel>
    </div>
    <!-- 轮播图 end -->
    <!-- 歌曲列表 start -->
    <div>
      <p>热门歌曲</p>
      <el-card class="box-card">
      <SongList :songData='songDataOfHot'/>
      </el-card>
      </div>
      <div>
      <p>推荐歌曲</p>
      <el-card class="box-card">
      <SongList :songData='songDataOfRecommend'/>
      </el-card>
    </div>
    <!-- 歌曲列表 end -->
  </div>
</template>

<script>
import SongList from "../components/SongList";
export default {
  name: "Music",
  data() {
    return {
      imgListLeft: [
        { key: "1", value: "/img/1.jpg" },
        { key: "2", value: "/img/2.jpg" },
        { key: "3", value: "/img/3.jpg" },
      ],
      imgListRight: [
        { key: "1", value: "/img/胡桃.jpg" },
        { key: "2", value: "/img/可莉.jpg" },
        { key: "3", value: "/img/七七.jpg" },
      ],
      /* 热门歌曲数据 */
      songDataOfHot: [
        {
          id: "1",
          songName: "最伟大的作品",
          name: "周杰伦",
          album: "最伟大的作品",
          timer: "04:04",
        },
        {
          id: "2",
          songName: "稻香",
          name: "周杰伦",
          album: "魔杰座",
          timer: "03:43",
        },
        {
          id: "3",
          songName: "夜曲",
          name: "周杰伦",
          album: "十一月的萧邦",
          timer: "03:46",
        },
        {
          id: "4",
          songName: "兰亭序",
          name: "周杰伦",
          album: "魔杰座",
          timer: "04:24",
        },
        {
          id: "5",
          songName: "爱在西元前",
          name: "周杰伦",
          album: "范特西",
          timer: "03:54",
        },
      ],
      /* 推荐歌曲数据 */
      songDataOfRecommend: [
        {
          id: "1",
          songName: "最伟大的作品",
          name: "周杰伦",
          album: "最伟大的作品",
          timer: "04:04",
        },
        {
          id: "2",
          songName: "稻香",
          name: "周杰伦",
          album: "魔杰座",
          timer: "03:43",
        },
        {
          id: "3",
          songName: "夜曲",
          name: "周杰伦",
          album: "十一月的萧邦",
          timer: "03:46",
        },
        {
          id: "4",
          songName: "兰亭序",
          name: "周杰伦",
          album: "魔杰座",
          timer: "04:24",
        },
        {
          id: "5",
          songName: "爱在西元前",
          name: "周杰伦",
          album: "范特西",
          timer: "03:54",
        },
      ]
    };
  },
  components: { SongList },
};
</script>

<style scoped>
.music {
  width: 100%;
  height: auto;
  max-height: 450px;
  margin: 0;
  padding: 0;
  overflow: auto;
  position: relative;
}
.music .img-list {
  display: flex;
  margin: 0;
  padding: 0;
}
.music .img-list-left {
  height: 200px;
  width: 575px;
  overflow: hidden;
}
.music .img-list-right {
  height: 200px;
  width: 315px;
  margin-left: 20px;
}
.music .el-carousel img {
  width: 100%;
  height: 100%;
  border-radius: 10px;
  object-fit: cover;
}
.music p {
  text-align: left;
  padding-top: 20px;
  padding-bottom: 20px;
}
.music .box-card {
  border: none;
  width: auto;
  height: auto;
  margin: -15px;
  background-color: rgba(255, 255, 255, 0.05);
}
</style>