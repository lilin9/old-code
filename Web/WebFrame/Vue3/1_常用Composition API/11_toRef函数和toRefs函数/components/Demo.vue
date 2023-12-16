<template>
  <h1>姓名: {{ name }}</h1>
  <h1>年龄: {{ age }}</h1>
  <h1>薪资: {{ job.salary }}k</h1>
  <button @click="name += '?'">改名</button>
  <button @click="age++">年龄加 1</button>
  <button @click="job.salary++">加薪</button>
</template>

<script>
import { reactive, toRef, toRefs } from "vue";

export default {
  name: "Demo",
  setup() {
    let person = reactive({
      name: "tom",
      age: 12,
      job: {
        salary: 30
      }
    });

    let name1 = person.name;
    console.log("%%% ", name1);

    let name2 = toRef(person, "name");
    console.log("### ", name2);

    // return {
    //   name: toRef(person, "name"),
    //   age: toRef(person, "age"),
    //   salary: toRef(person.job, "salary")
    // };
    return {
      ...toRefs(person)
    };
  },
};
</script>
