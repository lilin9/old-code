/* 此文件专门用于创建整个项目的路由器 */

//导入vueRouter
import VueRouter from "vue-router";
//引入组件
import About from '../components/About'
import Home from '../components/Home'

//创建路由器
export default new VueRouter({
    routes: [
        {
            path: '/about',
            component: About
        },
        {
            path: '/home',
            component: Home
        }
    ]
})