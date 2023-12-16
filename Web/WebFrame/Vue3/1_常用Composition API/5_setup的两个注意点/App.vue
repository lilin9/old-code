<template>
  <Demo @hello="showHelloMsg" school="b站大学" msg="下午好">
    <template v-slot:mySpan>
      <span>天选之人</span>
    </template>
  </Demo>
</template>

<script>
import Demo from "./components/Demo.vue";

export default {
  name: "App",
  components: { Demo },
  setup() {
    function showHelloMsg(name, age) {
      alert(`你好，你的名字是${name}，你的年龄是${age}`);
    }

    return {showHelloMsg};
  },
};
</script>
