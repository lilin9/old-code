<template>
  <div class="app">
    <h1>App组件</h1>
    <Child />
  </div>
</template>

<script>
import Child from './components/Child.vue'

export default {
  name: "App",
  components: {Child},
};
</script>

<style>
.app {
  background-color: red;
  padding: 12px;
  color: white;
}
</style>