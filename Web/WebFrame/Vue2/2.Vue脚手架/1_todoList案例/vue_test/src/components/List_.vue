<template>
  <ul class="todo-main">
    <transition-group name="todo" appear>
      <Item_
        v-for="todo in todos"
        :key="todo.id"
        :todo="todo"
      />
    </transition-group>
  </ul>
</template>

<script>
import Item_ from "./Item_.vue";

export default {
  name: "List_",
  components: {
    Item_,
  },
  props: ["todos"],
};
</script>

<style scoped>
.todo-main {
  margin-left: 0px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding: 0px;
}

.todo-empty {
  height: 40px;
  line-height: 40px;
  border: 1px solid #ddd;
  border-radius: 2px;
  padding-left: 5px;
  margin-top: 10px;
}

/* 进入的起点 and 离开的终点 */
.todo-enter,.todo-leave-to {
  transform: translateX(100%);
}

/* 进入的终点 and 离开的起点 */
.todo-enter-to,.todo-leave {
  transform: translateX(0);
}

.todo-enter-to, .todo-leave {
  transition: 0.75s linear;
}
</style>