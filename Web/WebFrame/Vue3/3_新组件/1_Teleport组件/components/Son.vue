<template>
  <div class="son">
    <h1>son组件</h1>
    <Dialog />
  </div>
</template>

<script>
import Dialog from './Dialog.vue'

export default {
    name: "Son",
    components: {Dialog}
};
</script>

<style>
.son {
    background-color: blue;
    padding: 12px;
  color: white;
}
</style>