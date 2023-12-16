<template>
  <h1>当前求和的值: {{ sum }}</h1>
  <button @click="sum++">点击加 1</button>
  <hr />
  <h1>当前鼠标的坐标: x {{ position.x }}, y {{ position.y }}</h1>
</template>

<script>
import { ref } from "vue";
import usePoint from "../hooks/usePoint";

export default {
  name: "Demo",
  setup() {
    let sum = ref(1);
    let position = usePoint();

    return { sum, position };
  },
};
</script>
