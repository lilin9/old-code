<template>
  <h1>当前求和的值: {{ sum }}</h1>
  <button @click="sum++">点击加 1</button>
  <hr />
  <h1>{{ msg }}</h1>
  <button @click="msg += '下午好! '">点击追加</button>
  <hr />
  <h1>姓名: {{ person.name }}</h1>
  <h1>年龄: {{ person.age }}</h1>
  <h1>薪资: {{ person.job.salary }}k</h1>
  <button @click="person.name += '?'">改名</button>
  <button @click="person.age++">年龄加 1</button>
  <button @click="person.job.salary++">加薪</button>
</template>

<script>
import { reactive, ref, watch, watchEffect } from "vue";

export default {
  name: "Demo",
  setup() {
    let sum = ref(1);
    let msg = ref("下午好! ");
    let person = reactive({
      name: "tom",
      age: 12,
      job: {
        salary: 30,
      },
    });

    //监视 ref 定义的一个响应式数据
    // watch(sum, (newValue, oldValue) => {
    //   console.log(`sum的值发生改变: ${newValue} -> ${oldValue}`);
    // }, {immediate: true});

    //watchEffect 所指定的回调中用到的数据只要发生变化，则直接重新指向回调
    watchEffect(() => {
      const sum1 = sum.value;
      const salary = person.job.salary;
      console.log("watchEffect 的回调函数被调用");
    });

    return { sum, msg, person };
  },
};
</script>
