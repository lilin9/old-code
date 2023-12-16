<template>
  <div class="app">

    <category title="游戏">
      <template scope="{games}">
        <ul>
          <li v-for="(item,index) in games" :key="index">{{item}}</li>
        </ul>
      </template>
    </category>

    <category title="游戏">
      <template scope="{games}">
        <ol>
          <li style="color: white" v-for="(item,index) in games" :key="index">{{item}}</li>
        </ol>
      </template>
    </category>

    <category title="游戏">
      <template slot-scope="{games}">
        <h4 style="color:green" v-for="(item,index) in games" :key="index">{{item}}</h4>
      </template>
    </category>

  </div>
</template>

<script>
import category from './components/Category.vue'
export default {
  name: "App",
  components: {category},
};
</script>

<style lang="css">
  .app, .footer {
    display: flex;
    justify-content: space-around;
  }
  
  h3 {
    text-align: center;
    background-color: orange;
  }

  h4 {
    text-align: center;
  }
</style>