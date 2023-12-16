<template>
  <h1>num的值: {{num.n}}</h1>
  <button @click="num.n++">num递增</button>
  <hr />
  <h1>姓名: {{ name }}</h1>
  <h1>年龄: {{ age }}</h1>
  <h1>薪资: {{ job.salary }}k</h1>
  <button @click="name += '?'">改名</button>
  <button @click="age++">年龄加 1</button>
  <button @click="job.salary++">加薪</button>
</template>

<script>
import { reactive, ref, shallowReactive, shallowRef, toRefs } from "vue";

export default {
  name: "Demo",
  setup() {
    let num = shallowRef({
      n: 1
    });
    // let num = ref(0);

    let person = shallowReactive({
      name: "tom",
      age: 12,
      job: {
        salary: 30,
      },
    });
    // let person = reactive({
    //   name: "tom",
    //   age: 12,
    //   job: {
    //     salary: 30,
    //   },
    // });

    return {
      ...toRefs(person),
      num
    };
  },
};
</script>
