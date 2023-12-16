<template>
  <div>
    <div class="row">
      <div class="col-xs-offset-2 col-xs-8">
        <div class="page-header"><h2>Vue Router Demo</h2></div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-2 col-xs-offset-2">
        <div class="list-group">
          <!-- 原始方式 -->
          <!-- <a href="./about.html" class="list-group-item active">Home</a> -->
          <!-- <a href="./home.html" class="list-group-item">About</a> -->
        
          <!-- Vue中借助router-link标签实现路由的切换 -->
        <router-link to="/home" class="list-group-item" active-class="active">Home</router-link>
        <router-link to="/about" class="list-group-item" active-class="active">About</router-link>
        </div>
      </div>
      <div class="col-xs-6">
        <div class="panel">
          <div class="panel-body">
            <!-- 指定组件呈现的位置 -->
            <router-view/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
</script>
<style>
</style>