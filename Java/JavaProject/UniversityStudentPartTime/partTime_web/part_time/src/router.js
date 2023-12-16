import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/search',
      name: 'search',
      component: () => import('./views/Search.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/notice',
      name: 'notice',
      component: () => import('./views/Notice.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/profile',
      name: 'profile',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/Profile.vue')
    },
    {
      path: '/self/:id',
      name: 'self',
      component: () => import('./views/Self.vue'),
      meta: {
        requiresAuth: true
      }
    },
    // 这里使用的是声明式导航
    {
      path: '/setting/:id',
      name: 'setting',
      component: () => import('./views/Setting.vue'),
      meta: {
        requiresAuth: true
      }
    },
    // 这个页面使用的时编程式导航
    {
      path: '/jobDetail',
      name: 'jobDetail',
      component: () => import('./views/jobDetail.vue'),
      meta: {
        requiresAuth: true,
        keepAlive: true
      }
    },
    {
      path: '/userDetail',
      name: 'userDetail',
      component: () => import('./views/UserDetail.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/publishing',
      name: 'publishing',
      component: () => import('./views/Publishing.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/searchDetail',
      name: 'searchDetail',
      component: () => import('./views/SearchDetail.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/followingDetail',
      name: 'followingDetail',
      component: () => import('./views/followingDetail.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/publishingDetail',
      name: 'publishingDetail',
      component: () => import('./views/PublishingDetail.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/collectingDetail',
      name: 'collectingDetail',
      component: () => import('./views/CollectingDetail.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/contact',
      name: 'contact',
      component: () => import('./views/Contact.vue'),
      meta: {
        requiresAuth: true
      }
    }
  ]
})
