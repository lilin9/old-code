<template>
  <h1>sum的值: {{sum}}</h1>
  <button @click="sum++">sum递增</button>
  <hr />
  <h1>姓名: {{ name }}</h1>
  <h1>年龄: {{ age }}</h1>
  <h1>薪资: {{ job.salary }}k</h1>
  <button @click="name += '?'">改名</button>
  <button @click="age++">年龄加 1</button>
  <button @click="job.salary++">加薪</button>
</template>

<script>
import { reactive, readonly, ref, shallowReadonly, toRefs } from "vue";

export default {
  name: "Demo",
  setup() {
    let sum = ref(0);

    let person = reactive({
      name: "tom",
      age: 12,
      job: {
        salary: 30,
      },
    });

    // person = readonly(person);
    person = shallowReadonly(person);

    return {
      ...toRefs(person),
      sum
    };
  },
};
</script>
