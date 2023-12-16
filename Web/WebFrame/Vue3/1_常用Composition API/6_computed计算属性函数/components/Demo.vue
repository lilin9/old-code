<template>
  <h1>Hello Vue3</h1>
  姓: <input type="text" v-model="person.firstName" /> 
  <br /><br />
  名: <input type="text" v-model="person.lastName" />
  <br /><br />
  修改全名: <input type="text" v-model="person.fullName" />
   <br /><br /><br /><br />
  <span>全名: {{ person.fullName }}</span>
</template>

<script>
import { computed, reactive } from "vue";

export default {
  name: "Demo",
  setup() {
    //源数据
    let person = reactive({
      firstName: "李",
      lastName: "四",
    });

    //计算属性 - 简写形式，没有考虑计算属性被修改的情况
    // person.fullName = computed(() => {
    //   return person.firstName + person.lastName;
    // });

    //计算属性 - 完整写法，考虑读和写
    person.fullName = computed({
      get() {
        return person.firstName + "-" + person.lastName;
      },
      set(newName) {
        const nameArr = newName.split("-");
        person.firstName = nameArr[0];
        person.lastName = nameArr[1];
      },
    });

    return { person };
  },
};
</script>
