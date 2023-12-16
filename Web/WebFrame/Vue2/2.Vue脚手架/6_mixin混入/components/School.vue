<template>
  <div>
    <h2 @click="showName">学校名称：{{ name }}</h2>
    <h2>学校地址：{{ address }}</h2>
  </div>
</template>

<script>
//引入 mixin 混合
// import { mixin } from "../mixin";

export default {
  name: "School",
  data() {
    return {
      name: "北大",
      address: "北京",
    };
  },
  // mixins: [mixin],
};
</script>