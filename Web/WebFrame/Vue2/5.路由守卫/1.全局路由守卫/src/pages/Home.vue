<template>
  <div>
    <h2>我是Home的内容</h2>
    <ul class="nav nav-tabs">
      <li>
        <router-link to="/home/news" class="list-group-item" active-class="active">News</router-link>
      </li>
      <li>
        <router-link to="/home/message" class="list-group-item" active-class="active">Message</router-link>
      </li>
    </ul>
    <keep-alive include="News">
      <router-view/>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>