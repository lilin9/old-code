<template>
  <h1>当前求和的值: {{ sum }}</h1>
  <button @click="sum++">点击加 1</button>
</template>

<script>
import {
  ref,
  onbeforeinput,
  onMounted,
  onBeforeUpdate,
  onUpdated,
  onBeforeUnmount,
  onUnmounted,
} from "vue";

export default {
  name: "Demo",
  setup() {
    let sum = ref(1);

    console.log("------setup------");

    onbeforeinput(() => {
      console.log("------onbeforeinput------");
    });

    onMounted(() => {
      console.log("------onMounted------");
    });

    onBeforeUpdate(() => {
      console.log("------onBeforeUpdate------");
    });

    onUpdated(() => {
      console.log("------onUpdated------");
    });

    onBeforeUnmount(() => {
      console.log("------onBeforeUnmount------");
    });

    onUnmounted(() => {
      console.log("------onUnmounted------");
    });
    return { sum };
  },
  //使用配置项的形式使用生命周期钩子
  // beforeCreate() {
  //   console.log("------beforeCreate------");
  // },
  // created() {
  //   console.log("------created------");
  // },
  // beforeMount() {
  //   console.log("------beforeMount------");
  // },
  // mounted() {
  //   console.log("------mounted------");
  // },
  // beforeUpdate() {
  //   console.log("------beforeUpdate------");
  // },
  // updated() {
  //   console.log("------updated------");
  // },
  // beforeUnmount() {
  //   console.log("------beforeUnmount------");
  // },
  // unmounted() {
  //   console.log("------unmounted------");
  // }
};
</script>
