<template>
  <div class="student">
    <h2>学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
    <button @click="forward">将学生名传给School组件</button>
  </div>
</template>

<script>
import pubsub from "pubsub-js";

export default {
  name: "Student",
  data() {
    return {
      name: "smith",
      sex: "boy",
    };
  },
  methods: {
    forward() {
      //接受订阅
      pubsub.publish('hello', 12345);
    }
  },
};
</script>

<style scoped>
    .student {
      background-color: greenyellow;
      padding: 5px;
    }
</style>