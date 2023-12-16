<template>
  <div class="student">
    <h2>学生名字：{{ name }}</h2>
    <h2>学生性别：{{ sex }}</h2>
    <button @click="sendStudentName">点击</button> <br><br>
    <button @click="unbind">解绑 love 事件</button> <br><br>
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
    sendStudentName() {
      //触发 Student 组件实例身上的 love 事件
      this.$emit("love", this.name, 100, 200, "a");
      // this.$emit("demo");
    },
    unbind() {
      // this.$off('love'); //解绑一个自定义事件
      // this.$off(['love', 'demo']); //解绑多个自定义事件
      this.$off(); //解绑所有自定义事件
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