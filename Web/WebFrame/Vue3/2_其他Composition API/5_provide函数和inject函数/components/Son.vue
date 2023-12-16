<template>
  <div class="son">
    <h1>son组件-孙组件 {{car.name}}---{{car.price}}</h1>
  </div>
</template>

<script>
import { inject } from 'vue';
export default {
    name: "Son",
    setup() {
        let car = inject("car");
        return {car};
    }
};
</script>

<style>
.son {
    background-color: blue;
    padding: 12px;
  color: white;
}
</style>