/* 主页 */
<template>
  <div class="index">
    <el-container>
      <el-header>
        <HeadList />
      </el-header>
      <el-container>
        <el-aside width="150px">
          <LeftList />
        </el-aside>
        <el-main>
          <Main />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import LeftList from "../components/LeftList";
import HeadList from "../components/HeadList";
import Main from "../components/Main";
export default {
  name: "Index",
  components: { LeftList, HeadList, Main },
};
</script>

<style>
.index {
  color: white;
  height: 520px;
  width: 1100px;
  border-radius: 6px;
  position: absolute;
  left: 50%;
  top: 48%;
  transform: translate(-50%, -50%);
  background: rgba(0, 0, 0, 0.2);
  -webkit-backdrop-filter: blur(8px);
  backdrop-filter: blur(8px);
  box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.2);
}
.el-container {
  margin: 0;
  padding: 0;
  height: 460px;
  width: 100%;
}
.el-header {
  height: 20px;
}
.el-main {
  margin: 0;
  padding: 0;
  height: 100%;
  width: 950px;
}
</style>