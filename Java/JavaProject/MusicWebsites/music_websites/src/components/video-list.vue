/* 视频列表 */
<template>
  <div class="video-list">
    <ul v-for="mv in videoData" :key="mv.id">
      <li>
        <div class="video-view">
          <a href="#" class="video-cover" @mouseenter="mvEnter(mv)" @mouseleave="mvLeave(mv)">
            <span class="video-play" v-show="mv.isShow"><i class="el-icon-video-play"></i></span>
            <span class="video-img"><el-image style="width: 100%; height: 100%" :src="mv.videoImgUrl" fit="cover"></el-image></span>
          </a> 
          <a class="video-name" href="#" :title="mv.videoName">{{mv.videoName}}</a><br />
          <span class="video-singer" :title="mv.videoSinger">{{mv.videoSinger}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
    name: 'VideoList',
    methods: {
        mvEnter(mv) {
        mv.isShow = true
        },
        mvLeave(mv) {
        mv.isShow = false
        }
    },
    props: ['videoData']
}
</script>

<style>
.video-list {
  width: 100%;
  height: 100%;
  padding: 0;
  margin-left: -47px;
}
.video-list ul {
  width: auto;
  display: inline-flexbox;
  margin-bottom: auto;
}
.video-list ul li {
  width: 180px;
  height: 150px;
  float: left;
  text-align: center;
  flex: auto;
}
.video-list ul,
.video-list ul li {
  list-style-type: none;
  text-align: left;
  margin-left: 15px;
}
.video-list .video-view {
  width: 180px;
  height: 101px;
}
.video-list .video-view a {
  text-decoration: none;
  color: white;
}
.video-list .video-view .video-name:hover {
  color: #667eea;
}
.video-list .video-singer {
  color: #d1cfcf;
}
.video-list .video-cover {
  display: block;
  position: relative;
}
.video-list .video-img {
  display: block;
  width: 180px;
  height: 101px;
}
.video-list .video-play {
  font-size: 50px;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 50px;
  height: 50px;
  margin-top: -25px;
  margin-left: -25px;
  z-index: 99;
}
.video-list .video-play:hover {
  color: #d1cfcf;
}
</style>