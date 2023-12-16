import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入reset.css
import '@/assets/css/reset.css'
// 引入rem.js
import '@/assets/js/rem.js'
// 导入axios
import axios from 'axios'
// 引入retreat 组件
import Retreat from './components/Retreat'
// 引入navigation 组件
import Navigation from './components/Navigation'
// 引入icon
import './assets/icon/iconfont.css'

import {
  Tabbar, TabbarItem, Field, Button, Toast,
  Image as VanImage, Divider, Icon, Uploader,
  RadioGroup, Radio, Tab, Tabs, Cell, CellGroup,
  Grid, GridItem, Empty
} from 'vant'

// socket.io
import SocketIO from 'vue-socket.io'
import ClientSocketIO from 'socket.io-client'

Vue.use(new SocketIO({
  debug: true,
  connection: ClientSocketIO.connect('http://localhost:8000/', {
    autoConnect: false, // 关闭自动连接
    transports: ['websocket']
  })
}))

Vue.use(Tabbar).use(TabbarItem).use(Field).use(Button).use(Toast).use(VanImage)
  .use(Divider).use(Icon).use(Uploader).use(RadioGroup).use(Radio).use(Tab).use(Tabs)
  .use(Cell).use(CellGroup).use(Grid).use(GridItem).use(Empty)
// 后端请求地址
axios.defaults.baseURL = 'http://localhost:8088'
// axios 实例，此实例用于需要登陆验证的请求
let authAxios = axios.create({})
authAxios.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    // 如果 token 存在
    if (token) {
      // 把token塞入Authorization里
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  err => {
    return Promise.reject(err)
  }
)
authAxios.interceptors.response.use(
  res => {
    if (res.data.errno === 999) {
      router.replace('/')
      console.log('token 过期')
    }
    return res
  }, err => {
    return Promise.reject(err)
  }
)
Vue.prototype.$authHttp = authAxios
Vue.prototype.$http = axios

Vue.config.productionTip = false

// 全局注册组件
Vue.component('Retreat', Retreat)
Vue.use(Retreat)
Vue.component('Navigation', Navigation)
Vue.use(Navigation)

// 全局 token 过期时间 24 个小时
let TOKEN_TIMEOUT = 86400000

// 全局路由守卫
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    let token = window.localStorage.getItem('token')
    console.log('token ', typeof token)
    // 跳转到首页判断登录
    // 判断 token
    console.log(token !== null && token !== undefined && token !== '')
    if (token !== null && token !== undefined && token !== '') {
      let result = new Date().getTime() - JSON.parse(token).startTime > TOKEN_TIMEOUT
      console.log('result ', result)
      if (!result) {
        // 存在 token，放行
        next()
      } else {
        goToLogin()
      }
    } else {
      goToLogin()
    }
  } else {
    // 放行
    next()
  }
})

// 跳转到 登录 页面
function goToLogin () {
  // 清空 token
  window.localStorage.setItem('token', '')
  // token 不存在，跳转到登陆页
  router.push('/profile')
}

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
