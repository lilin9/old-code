<template>
  <div class="app">
    <h1>App组件-祖组件 {{name}} --- {{price}}</h1>
    <Child />
  </div>
</template>

<script>
import { provide, reactive, toRefs } from 'vue';
import Child from './components/Child.vue'

export default {
  name: "App",
  components: {Child},
  setup() {
    let car = reactive({name: '劳斯莱斯', price: '1200w'});
    provide('car', car);
    return {...toRefs(car)};
  }
};
</script>

<style>
.app {
  background-color: red;
  padding: 12px;
  color: white;
}
</style>