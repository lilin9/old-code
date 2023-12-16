import Vue from "vue";
import App from "./App.vue"

//关闭Vue的生产提示
Vue.config.productionTip = false;

// const Demo = Vue.extend({});

// Vue.prototype.x = new Demo();

new Vue({
    el: "#app",
    render: h => h(App),
})