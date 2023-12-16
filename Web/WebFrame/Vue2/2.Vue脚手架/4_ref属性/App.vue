<template>
  <div>
    <h1 v-text="msg" ref="title"></h1>
    <School ref="sch"/>
    <button @click="show" ref="btn">点击</button>
  </div>
</template>

<script>
//引入 School.vue文件
import School from "./components/School.vue";

export default {
  name: "App",
  components: {
    School,
  },
  data() {
    return {
      msg: "Welcome To Learning Vue!",
    }
  },
  methods: {
    show() {
      console.log(this.$refs.title);
      console.log(this.$refs.sch);
      console.log(this.$refs.btn);
    }
  },
};
</script>

<style>
</style>