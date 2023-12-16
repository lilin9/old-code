<template>
    <div>
        <van-tabbar route>
            <van-tabbar-item replace to="/home" icon="home-o">首页</van-tabbar-item>
            <van-tabbar-item replace to="/search" icon="search">发现</van-tabbar-item>
            <van-tabbar-item replace to="/notice" icon="more-o">消息</van-tabbar-item>
            <van-tabbar-item replace to="/profile" icon="friends-o">我的</van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
export default {
  name: 'navigation'
}
</script>
