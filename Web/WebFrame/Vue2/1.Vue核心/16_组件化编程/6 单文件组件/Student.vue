<!-- 组件的结构 -->
<template>
    <div class="demo">
        <h1>学生姓名：{{name}}</h1>
        <h1>学生年龄：{{age}}</h1>
        <button @click="showName">学生名</button>
    </div>
</template>

<!-- 组件交互相关的代码（数据、方法等） -->
<script>
    export default Vue.extend({
        name: "Student",
        data() {
            return {
                name: "tom",
                age: 15
            }
        },
        methods: {
            showName() {
                alert(this.name);
            }
        },
    });
</script>

<!-- 组件的样式 -->
<style>
    .demo{
        background-color: greenyellow;
    }
</style>