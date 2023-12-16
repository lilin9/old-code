<template>
  <li>
    <label>
      <input
        type="checkbox"
        :checked="todo.done"
        @change="handlerCheck(todo.id)"
      />

      <!-- 以下代码可以实现功能，但是不推荐，因为它修改了 props，违反了 vue 的设计原则 -->
      <!-- <input type="checkbox" v-model="todo.done" /> -->
      <span>{{ todo.title }}</span>
    </label>
    <button class="btn btn-danger"  @click="handlerDelete(todo.id)">删除</button>
  </li>
</template>

<script>
export default {
  name: "Item_",
  props: ["todo", "checkTodo", "deleteTodo"],
  methods: {
    //勾选or取消勾选
    handlerCheck(id) {
      this.checkTodo(id);
    },

    //删除
    handlerDelete(id) {
      if(confirm("是否确认删除？")) {
        this.deleteTodo(id);
      }
    },
  },
};
</script>

<style scoped>
li {
  list-style: none;
  height: 36px;
  line-height: 36px;
  padding: 0 5px;
  border-bottom: 1px solid #ddd;
}

li label {
  float: left;
  cursor: pointer;
}

li label li input {
  vertical-align: middle;
  margin-right: 6px;
  position: relative;
  top: -1px;
}

li button {
  float: right;
  display: none;
  margin-top: 3px;
}

li:before {
  content: initial;
}

li:last-child {
  border-bottom: none;
}

li:hover {
  background-color: #ddd;
}

li:hover button {
  display: block;
}
</style>