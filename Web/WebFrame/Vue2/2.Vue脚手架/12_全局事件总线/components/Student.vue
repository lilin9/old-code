<template>
  <div class="student">
    <h2>学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
    <button @click="forward">传送数据给School</button>
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
  methods: {
    forward() {
      this.$bus.$emit('hello', this.name);
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