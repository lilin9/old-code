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
      <span v-show="!todo.isEdit">{{ todo.title }}</span>
      <input
        type="text"
        :value="todo.title"
        v-show="todo.isEdit"
        @blur="handlerBlur(todo, $event)"
        ref="inputTitle"
      />
    </label>
    <button class="btn btn-danger" @click="handlerDelete(todo.id)">
      删除
    </button>
    <button
      v-show="!todo.isEdit"
      class="btn btn-edit"
      @click="handlerEdit(todo)"
    >
      编辑
    </button>
  </li>
</template>

<script>
export default {
  name: "Item_",
  props: ["todo"],
  methods: {
    //勾选or取消勾选
    handlerCheck(id) {
      // this.checkTodo(id);
      this.$bus.$emit("checkTodo", id);
    },
    //删除
    handlerDelete(id) {
      if (confirm("是否确认删除？")) {
        // this.deleteTodo(id);
        this.$bus.$emit("deleteTodo", id);
      }
    },
    //编辑
    handlerEdit(todo) {
      if (todo.hasOwnProperty("isEdit")) {
        todo.isEdit = true;
      } else {
        this.$set(todo, "isEdit", true);
      }
      this.$nextTick(function () {
        this.$refs.inputTitle.focus();
      });
    },
    //失去焦点事件(真正执行修改逻辑)
    handlerBlur(todo, e) {
      todo.isEdit = false;
      if (!e.target.value) {
        alert("输入不能为空");
        return;
      }
      this.$bus.$emit("updateTodo", todo.id, e.target.value);
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