import Vue from 'vue'
import App from './App.vue'
//引入store
import Store from "./store/index"
//引入插件
import VueResource from "vue-resource"

//使用插件
Vue.use(VueResource);

//关闭Vue的生产提示
Vue.config.productionTip = false;

//创建vm
new Vue({
  el: "#app",
  render: h => h(App),
  Store,
  beforeCreate() {
    Vue.prototype.$bus = this
  }
})
