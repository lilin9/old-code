import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";
import store from "./store";
//引入全局字体
import './assets/font/font.css'
//引入通用样式
import './assets/css/global.css'
//引入背景的粒子动画组件
import VueParticles from "vue-particles";
//引入 element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import Axios from 'axios'

//使用 vueParticles
Vue.use(VueParticles)
//使用element-ui
Vue.use(ElementUI);
//获取百度音乐api
Vue.prototype.HOST = "/baidu_music_api"
Vue.prototype.$axios = Axios

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
