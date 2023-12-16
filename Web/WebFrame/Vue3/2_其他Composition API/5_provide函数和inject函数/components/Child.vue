<template>
  <div class="child">
    <h1>Child组件-父组件</h1>
    <Son />
  </div>
</template>

<script>
import Son from "./Son.vue";

export default {
  name: "Child",
  components: { Son },
};
</script>

<style>
.child {
  background-color: green;
  padding: 12px;
  color: white;
}
</style>