import Vue from 'vue'
import VueRouter from 'vue-router'
// import Login from '@/components/login/Login.vue'
// import Home from '@/components/home/HomeVue.vue'
// import Welcome from '@/components/welcome/Welcome.vue'

// import Users from '@/components/users/Users.vue'
// import Rights from '@/components/power/Rights.vue'
// import Roles from '@/components/power/Roles.vue'
// import Categories from '@/components/positions/Categories.vue'
// import Params from '@/components/positions/Params.vue'
// import PositionsList from '@/components/positions/City.vue'
// import AddPositions from '@/components/positions/AddPositions.vue'
// import Care from '@/components/Care/Care.vue'
// import Reports from '@/components/reports/Reports.vue'
const Login = () => import(/* webpackChunkName:"login_home_welcome" */ '@/components/login/Login.vue')
// import Login from './components/Login.vue'
const Home = () => import(/* webpackChunkName:"login_home_welcome" */ '@/components/home/HomeVue.vue')
// import Home from './components/Home.vue'
const Welcome = () => import(/* webpackChunkName:"login_home_welcome" */ '@/components/welcome/Welcome.vue')
// import Welcome from './components/Welcome.vue'
const Users = () => import(/* webpackChunkName:"user" */ '@/components/users/Users.vue')
// import Users from './components/user/Users.vue'
const Rights = () => import(/* webpackChunkName:"power" */ '@/components/power/Rights.vue')
// import Rights from './components/power/Rights.vue'
const Roles = () => import(/* webpackChunkName:"power" */ '@/components/power/Roles.vue')
// import Roles from './components/power/Roles.vue'
const Categories = () => import(/* webpackChunkName:"positions" */ '@/components/positions/Categories.vue')
// import Cate from './components/positions/Cate.vue'
const Params = () => import(/* webpackChunkName:"positions" */ '@/components/positions/Params.vue')
// import Params from './components/positions/Params.vue'
const PositionsList = () => import(/* webpackChunkName:"positions" */ '@/components/positions/List.vue')
// import GoodList from './components/positions/City.vue'
const AddPositions = () => import(/* webpackChunkName:"positions" */ '@/components/positions/AddPositions.vue')
// import GoodAdd from './components/positions/Add.vue'
const Care = () => import(/* webpackChunkName:"order" */ '@/components/users/Care.vue')
// import Order from './components/order/Order.vue'
const Reports = () => import(/* webpackChunkName:"report" */ '@/components/reports/Reports.vue')
// import Report from './components/report/Report.vue'
const Collect = () => import(/* webpackChunkName:"report" */ '@/components/users/Collect.vue')
// import Collect from './components/users/Collect.vue'
// import Report from './components/report/Report.vue'
const City = () => import(/* webpackChunkName:"report" */ '@/components/city/City.vue')
// import City from './components/city/City.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/users', component: Users },
      { path: '/rights', component: Rights },
      { path: '/roles', component: Roles },
      { path: '/categories', component: Categories },
      { path: '/params', component: Params },
      { path: '/positions', component: PositionsList },
      { path: '/positions/add', component: AddPositions },
      { path: '/Care', component: Care },
      { path: '/reports', component: Reports },
      { path: '/collect', component: Collect },
      { path: '/city', component: City }

    ]
  }

]

const router = new VueRouter({
  routes
})
// 路由导航控制访问权限
router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()
  const tokenStr = window.sessionStorage.getItem('admin_token')
  if (!tokenStr) return next('/login')
  next()
})

export default router
