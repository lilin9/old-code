<template>
  <div>
    <School/>
    <hr>
    <Student/>
  </div>
</template>

<script>
//引入 School.vue文件
import Student from "./components/Student.vue";
import School from "./components/School.vue";

export default {
  name: "App",
  components: {
    Student,
    School,
  },
};
</script>

<style>
</style>