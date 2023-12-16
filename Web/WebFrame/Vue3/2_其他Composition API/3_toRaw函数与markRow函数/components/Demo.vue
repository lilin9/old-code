<template>
  <h1>sum的值: {{sum}}</h1>
  <button @click="sum++">sum递增</button>
  <hr />
  <h1>姓名: {{ name }}</h1>
  <h1>年龄: {{ age }}</h1>
  <h1>薪资: {{ job.salary }}k</h1>
  <h1>我的车: {{person.car}}</h1>
  <button @click="name += '?'">改名</button>
  <button @click="age++">年龄加 1</button>
  <button @click="job.salary++">加薪</button>
  <hr/>
  <button @click="showRawObject">输出原始对象</button>
  <button @click="addCar">买台车吧</button>
  <button @click="person.car.name = '劳斯莱斯'">换台车吧</button>
</template>

<script>
import { markRaw, reactive, ref, toRaw, toRefs } from "vue";

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

    function showRawObject() {
      console.log(person);
      console.log(toRaw(person));
    }

    function addCar() {
      person.car = markRaw({name: "奔驰", price: 1200});
    }

    return {
      person,
      ...toRefs(person),
      sum,
      showRawObject,
      addCar
    };
  },
};
</script>
