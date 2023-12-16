<template>
    <div class="category">
        <h3>{{ title }}分类</h3>
        <slot :games="games"></slot>
    </div>
</template>

<script>
    export default {
        name: "Category",
        props: ["title"],
        data() {
            return {
            games: ['战地','巫师三','我的世界','塞尔达传说'],
            }
        },
    };
</script>

<style scoped>
    .category {
    background-color: skyblue;
    width: 200px;
    height: 300px;
    }
    img {
    width: 100%;
    }
    video {
    width: 100%;
    }
</style>