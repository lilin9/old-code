<template>
  <div>
    <h2>学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
    <button @click="test">点击</button>
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
    test() {
      this.hello();
    }
  }
};
</script>