<template>
  <div>
    <h2 @click="showName">学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
  </div>
</template>

<script>
//引入 mixin 混合
// import { mixin } from "../mixin";

export default {
  name: "Student",
  data() {
    return {
      name: "smith",
      sex: "boy",
    };
  },
  // mixins: [mixin],
};
</script>