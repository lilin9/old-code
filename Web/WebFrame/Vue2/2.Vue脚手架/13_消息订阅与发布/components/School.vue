<template>
  <div class="school">
    <h2>学校名称：{{ name }}</h2>
    <h2>学校地址：{{ address }}</h2>
  </div>
</template>

<script>
import pubsub from "pubsub-js";

export default {
  name: "School",
  data() {
    return {
      name: "北大",
      address: "北京",
    };
  },
  mounted() {
    //订阅消息
    this.pubId = pubsub.subscribe("hello", (messageName, data) => {
      console.log("hello回调函数被调用了 --> ", messageName, data);
    });
  },
  beforeDestroy() {
    //取消订阅
    pubsub.unsubscribe(this.pubId);
  },
};
</script>

<style scoped>
.school {
  background-color: skyblue;
  padding: 5px;
  margin-top: 30px;
}
</style>