<template>
  <div>
    <Student name="amy" sex="girl" :age="15"/>
  </div>
</template>

<script>
//引入 School.vue文件
import Student from "./components/Student.vue";

export default {
  name: "App",
  components: {
    Student,
  },
};
</script>

<style>
</style>