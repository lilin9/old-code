/* 此文件专门用于创建整个项目的路由器 */

//导入vueRouter
import VueRouter from "vue-router";
//引入组件
import About from '../pages/About'
import Home from '../pages/Home'
import News from '../pages/News'
import Message from '../pages/Message'
import Detail from '../pages/Detail'

//创建路由器
const router = new VueRouter({
    routes: [
        {
            name: 'about',
            path: '/about',
            component: About,
            meta: { title: "about" }
        },
        {
            name: 'home',
            path: '/home',
            component: Home,
            meta: { title: "home" },
            children: [
                {
                    name: 'news',
                    path: 'news',
                    component: News,
                    meta: { isAuth: true, title: "news" },
                    beforeEnter: (to, from, next) => {
                        if (to.meta.isAuth) {    //判断是否需要权限
                            if (localStorage.getItem('school') === 'atguigu') {
                                next()
                            } else alert('无权限访问！')
                        } else {
                            next()
                        }
                    }
                },
                {
                    name: 'message',
                    path: 'message',
                    component: Message,
                    meta: { isAuth: true, title: "message" },
                    children: [
                        {
                            name: 'detail',
                            path: 'detail/:id/:title',
                            component: Detail,

                            //props的第一种写法，值为对象。
                            //该对象中的所有key-value都会以props的形式传给Detail组件
                            // props: {a:'1',b:'hello'}

                            //props的第二种写法，值为布尔值
                            //若布尔值为真，就会把该路由组件收到的所有params参数，以props的形式传给Detail组件
                            // props: true

                            //props的第三种写法，值为函数
                            //
                            props($route) {
                                return {
                                    id: $route.params.id, title: $route.params.title
                                }
                            }
                        }
                    ]
                }
            ]
        }
    ]
})

//全局后置守卫，初始化时执行、每次路由切换后执行
router.afterEach((to, from) => {
    document.title = to.meta.title || "goodMorning"
})

export default router