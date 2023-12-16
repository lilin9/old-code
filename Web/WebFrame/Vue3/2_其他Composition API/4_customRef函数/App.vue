<template>
  <input type="text" v-model="keyWord" />
  <h1>{{ keyWord }}</h1>
</template>

<script>
import { customRef, ref } from "vue";

export default {
  name: "App",
  setup() {
    // let keyWord = ref("hello"); //vue提供的 ref
    let keyWord = myRef("hello", 500); //自定义 ref

    function myRef(value, delay) {
      let timer;
      return customRef((track, trigger) => {
        return {
          get() {
            //通知 vue 追踪 value 的变化
            track();
            return value;
          },
          set(newVal) {
            clearTimeout(timer);
            timer = setTimeout(() => {
              value = newVal;
              //通知 vue 去重新解析模板
              trigger();
            }, delay);
          }
        }
      });
    }

    return { keyWord };
  },
};
</script>
