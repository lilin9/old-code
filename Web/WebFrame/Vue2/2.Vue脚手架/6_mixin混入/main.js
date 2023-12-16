import Vue from "vue";
import App from "./App.vue"
import {mixin} from "./mixin"
//关闭Vue的生产提示
Vue.config.productionTip = false;
//混合的全局配置
Vue.mixin(mixin)

new Vue({
    el: "#app",
    render: h => h(App),
})