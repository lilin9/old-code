<template>
  <h1>Hello Vue3</h1>
  <h2>姓名：{{ name }}</h2>
  <h2>年龄：{{ age }}</h2>
  <button @click="updateInfo">点击修改</button>
</template>

<script>
import { ref } from "vue";

export default {
  name: "App",
  setup() {
    //数据
    let name = ref("tom");
    let age = 12;

    //方法
    function updateInfo() {
      name.value = "jerry";
      age = 9;

      console.log("name: ", name);
      console.log("age", age);
    }

    //返回一个对象（常用）
    return { name, age, updateInfo };
  },
};
</script>
