<template>
  <h1>Hello Vue3</h1>
  <h2 v-show="person.name">姓名：{{ person.name }}</h2>
  <h2>年龄：{{ person.age }}</h2>
  <h2 v-show="person.sex">性别{{ person.sex }}</h2>
  <h3>工作：{{ person.job.title }}</h3>
  <h3>薪水：{{ person.job.salary }}</h3>
  <br>
  <button @click="updateInfo">点击修改</button>
  <button @click="addSex">添加sex属性</button>
  <button @click="delName">删除name属性</button>
</template>

<script>
import { reactive } from "vue";

export default {
  name: "App",
  setup() {
    //数据
    let person = reactive({
      name: "tom",
      age: 12,
      job: {
        title: "前端工程师",
        salary: "30k",
      },
    });

    //方法
    function updateInfo() {
      person.name = "jerry";
      person.age = 9;
      person.job.title = "Java工程师";
      person.job.salary = "50k";

      console.log(person);
    }

    function addSex() {
      person.sex = "男";
    }

    function delName() {
      delete person.name;
    }

    //返回一个对象（常用）
    return { person, updateInfo, addSex, delName };
  },
};
</script>
