/* 歌曲列表 */
<template>
  <div class="song-table">
    <div class="song-table-header">
      <table>
        <tr>
          <th>歌曲名</th>
          <th>歌手</th>
          <th>专辑</th>
          <th>时长</th>
        </tr>
      </table>
    </div>
    <div class="song-table-body">
      <table v-for="song in songData" :key="song.id">
        <router-link to="/musicPlay" tag="tr">
          <td>
            {{ song.songName }}
            <span class="song-icon">
              <i class="el-icon-video-play"></i>&nbsp;
              <i class="el-icon-download"></i>
            </span>
          </td>
          <td>
            {{ song.name }}
          </td>
          <td>
            {{ song.album }}
          </td>
          <td>
            {{ song.timer }}
          </td>
        </router-link>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "SongList",
  props: ["songData"],
};
</script>

<style scoped>
.song-table {
  color: white;
  background-color: transparent;
  width: 100%;
  margin-top: -15px;
  border: none;
}
.song-table .song-table-header tr th {
  color: rgb(186, 186, 186);
  font-weight: normal;
}
.song-table .song-table-body tr:hover {
  background-color: rgba(0, 0, 0, 0.1);
}
.song-table table tr th,
.song-table table tr td {
  text-align: left;
  padding-bottom: 10px;
  padding-top: 10px;
  border: none;
}
.song-table table tr th:nth-child(1),
.song-table table tr td:nth-child(1) {
  width: 500px;
  padding-right: 10px;
}
.song-table table tr th:nth-child(n + 2),
.song-table table tr td:nth-child(n + 2) {
  width: 200px;
  padding-right: 10px;
}
.song-table-header table,
.song-table-body table {
  widows: 100%;
}
.song-table-body table tr td a {
  text-decoration: none;
  color: #fff;
}
.song-table .song-table-body .song-icon {
  float: right;
}
.song-table .song-table-body .song-icon .el-icon-video-play {
  margin-left: 5px;
}
.song-table .song-table-body .el-icon-video-play:hover,
.song-table .song-table-body .el-icon-download:hover {
  color: rgba(255, 255, 255, 0.5);
}
</style>