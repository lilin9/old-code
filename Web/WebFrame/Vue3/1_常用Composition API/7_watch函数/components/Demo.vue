<template>
  <h1>当前求和的值: {{ sum }}</h1>
  <button @click="sum++">点击加 1</button>
  <hr>
  <h1>{{msg}}</h1>
  <button @click="msg += '下午好! '">点击追加</button>
  <hr>
  <h1>姓名: {{person.name}}</h1>
  <h1>年龄: {{person.age}}</h1>
  <h1>薪资: {{person.job.salary}}k</h1>
  <button @click="person.name += '?'">改名</button>
  <button @click="person.age++">年龄加 1</button>
  <button @click="person.job.salary++">加薪</button>
</template>

<script>
import { reactive, ref, watch } from "vue";

export default {
  name: "Demo",
  setup() {
    let sum = ref(1);
    let msg = ref("下午好! ");
    let person = reactive({
      name: "tom",
      age: 12,
      job: {
        salary: 30
      }
    });

    //情况一：监视 ref 定义的一个响应式数据
    // watch(sum, (newValue, oldValue) => {
    //   console.log(`sum的值发生改变: ${newValue} -> ${oldValue}`);
    // }, {immediate: true});

    //情况二：监视 ref 定义的多个响应式数据
    // watch([sum, msg], (newValue, oldValue) => {
    //   console.log(`sum的值发生改变: ${newValue[0]} -> ${oldValue[0]}`);
    //   console.log(`msg的值发生改变: ${newValue[1]} -> ${oldValue[1]}`);
    // }, {immediate: true});

    /**
     * 情况三：监视 reactive 定义的一个响应式数据的全部个属性
     * 注意：
     *    1.此处无法正确获取 oldValue
     *    2.强制开启了深度监视（deep配置无效）
     */
    // watch(person, (newValue, oldValue) => {
    //   console.log(`person的值发生改变: ${newValue} -> ${oldValue}`);
    // }, {deep: false});

    //情况五：监视 reactive 定义的一个响应式数据的某个属性
    // watch(() => person.age, (newValue, oldValue) => {
    //   console.log(`person.age的值发生改变: ${newValue} -> ${oldValue}`);
    // });

    //情况五：监视 reactive 定义的一个响应式数据的多个属性
    // watch([() => person.age, () => person.name], (newValue, oldValue) => {
    //   console.log(`person.age或person.name的值发生改变: ${newValue} -> ${oldValue}`);
    // });

    //特殊情况
    watch(person.job, (newValue, oldValue) => {
      console.log("person.job的值发生改变: ${newValue} -> ${oldValue}")
    }, {deep: true}); //由于此处监视的是 reactive 所定义的对象中的某个属性，所以 deep 配置会生效

    return { sum, msg, person };
  },
};
</script>
