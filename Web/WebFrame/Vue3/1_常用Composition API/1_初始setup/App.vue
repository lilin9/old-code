<template>
  <h1>Hello Vue3</h1>
  <h2>姓名：{{name}}</h2>
  <h2>年龄：{{age}}</h2>
  <button @click="info">点我</button>
</template>

<script>
import {h} from 'vue'

export default {
  name: "App",
  setup() {
    //数据
    let name = "tom";
    let age = 12;

    //方法
    function info() {
      alert(`你好，我是${name}, ${age}岁了`);
    }

    //返回一个对象（常用）
    // return {name, age, info};

    //返回一个渲染函数
    return () => h("h1", "Hello");
  }
};
</script>
