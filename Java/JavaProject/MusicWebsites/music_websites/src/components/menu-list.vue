/* 导航列表 */
<template>
    <el-menu 
        mode="horizontal" 
        background-color="transparent" 
        text-color="#fff" 
        active-text-color="#fff"
        class="menu-list">
        <el-menu-item v-for="menu in menuData" :key="menu.id" :index="menu.id">
            <span>{{menu.value}}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: 'MenuList',
    props: ['menuData']
}
</script>

<style>
.menu-list .el-menu-item {
  border-radius: 20px;
}
.menu-list .el-menu-item:hover {
  background-color: rgba(255, 255, 255, 0.4) !important;
}
.menu-list div {
    display: flex;
    float: left;
}
</style>