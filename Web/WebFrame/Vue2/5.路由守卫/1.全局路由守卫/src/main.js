import Vue from 'vue'
//引入App.vue
import App from './App.vue'
//引入VueRouter
import VueRouter from 'vue-router'
//引入路由器
import Router from './router/index'

//关闭Vue的生产提示
Vue.config.productionTip = false;
//使用VueRouter
Vue.use(VueRouter)

//创建vm
new Vue({
  el: "#app",
  render: h => h(App),
  router: Router,
})
