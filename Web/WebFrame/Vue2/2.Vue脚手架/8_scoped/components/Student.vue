<template>
  <div class="demo">
    <h2>学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
    <button>点击</button>
  </div>
</template>

<script>

export default {
  name: "Student",
  data() {
    return {
      name: "smith",
      sex: "boy",
    };
  },
};
</script>

<style scoped>
    .demo {
      background-color: greenyellow;
    }
</style>