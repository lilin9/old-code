<template>
  <div>
    <h1>{{ msg }}</h1>
    <School/>
    <hr />
    <Student/>
  </div>
</template>

<script>
//引入 School.vue文件
import Student from "./components/Student.vue";
import School from "./components/School.vue";

export default {
  name: "App",
  data() {
    return {
      msg: "Welcome To Learning Vue",
      studentName: '',
    };
  },
  components: {
    Student,
    School,
  },
};
</script>

<style>
</style>