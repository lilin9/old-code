<template>
  <div>
    <button @click="isShow = !isShow">显示/隐藏</button>
    <transition-group name="hello" appear>
      <h1 v-show="!isShow" class="go" key="1">你好</h1>
      <h1 v-show="isShow" class="go" key="2">Vue</h1>
    </transition-group>
  </div>
</template>

<script>
export default {
  name: "Test2",
  data() {
    return {
      isShow: true,
    };
  },
};
</script>

<style scoped>
h1 {
  background-color: orange;
}
/* 进入的起点 and 离开的终点 */
.hello-enter,.hello-leave-to {
  transform: translateX(-100%);
}
/* 进入的终点 and 离开的起点 */
.hello-enter-to,.hello-leave {
  transform: translateX(0);
}
.hello-enter-to, .hello-leave {
  transition: 1s linear;
}
</style>