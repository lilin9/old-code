import Vue from 'vue'
//引入App.vue
import App from './App.vue'

//Element-UI组件全部引入
//引入ElementUI
// import ElementUI from 'element-ui';
//引入ElementUI的全部样式
// import 'element-ui/lib/theme-chalk/index.css';

//Element-UI按需引入
import { Button,Row,DatePicker } from 'element-ui'

//关闭Vue的生产提示
Vue.config.productionTip = false;
//应用ElementUI
// Vue.use(ElementUI)
Vue.component(Button.name, Button)
Vue.component(Row.name, Row)
Vue.component(DatePicker.name, DatePicker)

//创建vm
new Vue({
  el: "#app",
  render: h => h(App),
})
