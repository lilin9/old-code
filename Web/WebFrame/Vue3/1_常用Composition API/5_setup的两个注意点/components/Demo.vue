<template>
  <h1>Hello Vue3</h1>
  <h2>姓名：{{ person.name }}</h2>
  <h2>年龄：{{ person.age }}</h2>
  <button @click="test">点击弹出弹窗</button>
</template>

<script>
import { reactive } from "vue";

export default {
  name: "Demo",
  props: ["school", "msg"],
  emits: ["hello"],
  setup(props, context) {
    console.log("------setup------");
    console.log("props: ", props);
    console.log("emit: ", context.emit);
    console.log("slots: ", context.slots);

    let person = reactive({
      name: "tom",
      age: 12
    });

    function test() {
        context.emit("hello", person.name, person.age);
    }

    return { person, test};
  }
};
</script>
