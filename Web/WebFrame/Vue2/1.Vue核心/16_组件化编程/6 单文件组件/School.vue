<!-- 组件的结构 -->
<template>
    <div class="demo">
        <h1>学校名称：{{name}}</h1>
        <h1>学校地址：{{address}}</h1>
        <button @click="showName">学校名</button>
    </div>
</template>

<!-- 组件交互相关的代码（数据、方法等） -->
<script>
    export default Vue.extend({
        name: "School",
        data() {
            return {
                name: "北大",
                address: "北京"
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
        background-color: orange;
    }
</style>