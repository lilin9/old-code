<template>
  <button @click="isShow = !isShow">显式/隐藏</button>
  <Demo v-if="isShow" />
</template>

<script>
import {ref} from 'vue';
import Demo from "./components/Demo.vue";

export default {
  name: "App",
  components: { Demo },
  setup() {
    let isShow = ref(true);

    return { isShow };
  },
};
</script>
