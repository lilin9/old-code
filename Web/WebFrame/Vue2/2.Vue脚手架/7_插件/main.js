import Vue from "vue";
import App from "./App.vue"
//导入插件
import plugins from "./plugins"

//关闭Vue的生产提示
Vue.config.productionTip = false;
//使用插件
Vue.use(plugins)

new Vue({
    el: "#app",
    render: h => h(App),
})