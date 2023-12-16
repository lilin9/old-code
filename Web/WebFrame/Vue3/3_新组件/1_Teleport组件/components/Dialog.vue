<template>
  <button @click="isShow = true">弹窗</button>
  <teleport to="body">
    <div class="mask" v-if="isShow">
      <div class="dialog">
        <h1>我是弹窗</h1>
        <h1>我不是弹窗</h1>
        <h1>我是弹窗</h1>
        <button @click="isShow = false">关闭弹窗</button>
      </div>
    </div>
  </teleport>
</template>
<script>
import { ref } from "vue";
export default {
  name: "Dialog",
  setup() {
    let isShow = ref(false);
    return { isShow };
  },
};
</script>

<style>
.mask {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
}
.dialog {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 300px;
  height: auto;
  background-color: orange;
  padding: 10px;
}
</style>