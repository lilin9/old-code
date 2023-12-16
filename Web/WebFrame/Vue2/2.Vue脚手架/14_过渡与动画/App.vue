<template>
  <div>
    <h1>{{ msg }}</h1>
    <Test1/>
    <hr>
    <Test2/>
    <hr>
    <Test3/>
  </div>
</template>

<script>
//引入 School.vue文件
import Test1 from "./components/Test1.vue";
import Test2 from "./components/Test2.vue";
import Test3 from "./components/Test3.vue";

export default {
  name: "App",
  data() {
    return {
      msg: "Welcome To Learning Vue",
    };
  },
  components: {
    Test1,
    Test2,
    Test3,
  },
};
</script>

<style>
</style>