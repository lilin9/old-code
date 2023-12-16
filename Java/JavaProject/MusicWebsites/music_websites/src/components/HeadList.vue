<template>
  <div class="head-list">
    <ul>
      <li>
        <a href="#"><i class="el-icon-arrow-left"></i></a>
      </li>
      <li>
        <a href="#"><i class="el-icon-arrow-right"></i></a>
      </li>
      <li>
        <a href="#"><i class="el-icon-refresh"></i></a>
      </li>
    </ul>
    <ul>
      <li><router-link to="/music">音乐</router-link></li>
      <li><router-link to="/mv">MV</router-link></li>
      <li><router-link to="/radio">电台</router-link></li>
      <li><router-link to="/cartoon">二次元</router-link></li>
      <li>
        <el-input clearable placeholder="搜索……" v-model="search">
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
      </li>
    </ul>
    <ul>
      <li>
        <a href="#">
          <i class="el-icon-message-solid"></i>
        </a>
      </li>
      <li><a href="#"><i class="el-icon-upload"></i></a></li>
      <li>
        <a href="#">
          <el-avatar :size="27" fit="fill" :src="avatarUrl" @error="errorHandler">
            <i class="el-icon-user-solid"></i>
          </el-avatar>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeadList",
  data() {
    return {
      search: "",
      avatarUrl: "/img/我喜欢.png"
    };
  },
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
.head-list {
  width: 1100px;
  height: 40px;
  float: left;
  align-items: center;
  display: flex;
  margin-left: -20px;
}
.head-list ul {
  display: inline-flex;
  float: left;
  width: auto;
  vertical-align: middle;
  margin-top: 20px;
  flex-direction: row;
  justify-content: center;
}
.head-list ul li {
  list-style-type: none;
  padding-right: 10px;
  display: flex;
  line-height: 29px;
}
.head-list a {
  text-decoration: none;
  color: #fff;
}
ul:nth-child(1) {
  width: 150px;
}
ul:nth-child(1) li {
  margin-left: 10px;
}
ul:nth-child(2) {
  width: 720px;
}
ul:nth-child(2) li {
  margin-right: 40px;
}
ul:nth-child(3) {
  vertical-align: middle;
  width: auto;
  margin-left: 65px;
}
ul:nth-child(3) li {
  margin-left: 15px;
}
</style>