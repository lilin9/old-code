/* 音乐播放页面 */
<template>
  <div class="music-play">
      <div class="song-img">
          <el-image class="img" style="width:60%;height:60%" :src="songImgUrl" fit="cover"></el-image>
          <span class="temp"></span>
      </div>
      <div class="lyrics">歌词</div>
      <div class="song-audio">
          <audio :src="audioData" controls="controls" autoplay="autoplay" class="audio"></audio>
      </div>
  </div>
</template>

<script>
export default {
    name: 'MusicPlay',
    data() {
        return {
            songImgUrl: "/img/爱在西元前.jpg",
            lyricsData: "",
            audioData: "/audio/周杰伦 - 爱在西元前.kgm"
        }
    }
}
</script>

<style>
.music-play {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}
.music-play .song-img {
    float: left;
    width: 50%;
    height: 80%;
    text-align: center;
}
.music-play .song-img .temp {
    display: inline-block;
    width: 0;
    height: 100%;
    vertical-align: middle;
}
.music-play .song-img .img {
    display: inline-block;
    vertical-align: middle;
}
.music-play .lyrics {
    float: right;
    width: 50%;
    height: 80%;
}
.music-play .song-audio {
    width: 100%;
    height: 100%;
}
.music-play .song-audio .audio {
    width: 100%;
    background-color: transparent;
    text-align: center;
    margin-top: 15px;
}
</style>