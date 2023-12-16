<template>
    <div class="category">
        <h3>{{ title }}分类</h3>
        <slot name="slot1"></slot>
        <slot name="slot2"></slot>
    </div>
</template>

<script>
    export default {
    name: "Category",
    props: ["title"],
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