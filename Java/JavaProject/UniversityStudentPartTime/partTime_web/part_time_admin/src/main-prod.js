import Vue from 'vue'
import App from './App.vue'
import router from './router'
// import ElementUI from 'element-ui'
import '@/assets/fonts/iconfont.css'
// import 'element-ui/lib/theme-chalk/index.css'
import '@/css/golbal.css'
import axios from 'axios'
import TreeTable from 'vue-table-with-tree-grid'
import moment from 'moment'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import NProgress from 'nprogress'
// import 'nprogress/nprogress.css'
// axios.defaults.baseURL = 'http://127.0.0.1:8888/api/private/v1/'
axios.defaults.baseURL = 'http://localhost:8089/admin'
axios.interceptors.request.use(config => {
  // console.log(config)\
  NProgress.start()
  config.headers.Authorization = window.sessionStorage.getItem('admin_token')
  return config
})
axios.interceptors.response.use(config => {
  NProgress.done()
  return config
})
// 在response拦截器中，隐藏进度条
axios.interceptors.response.use(config => {
  // 当进入response拦截器，表示请求已经结束，我们就结束进度条
  NProgress.done()
  return config
})
Vue.prototype.$http = axios

Vue.config.productionTip = false
// Vue.use(ElementUI)
Vue.component('tree-table', TreeTable)
Vue.use(VueQuillEditor)

Vue.filter('global_filter', function (value, type = 'YYYY-MM-DD hh:mm:ss') {
  return moment(value).format(type)
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
