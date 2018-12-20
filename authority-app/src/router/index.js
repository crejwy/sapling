import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Unauth from '@/components/Unauth'
import S404 from '@/components/404'

//
// import  INDEX from '@/components/Index'
// import  USER from '@/components/User/Index'
// import  ROLE from '@/components/Role/Index'
// import  ROUTER from '@/components/Router/Index'
// import  PERMISSION from '@/components/Permission/Index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/404',
      name: 'S404',
      meta: {
        requireAuth: false // 添加该字段，表示进入这个路由是需要登录的
      },
      component: S404
    },
    {
      path: '/unauth',
      name: 'Unauth',
      meta: {
        requireAuth: false // 添加该字段，表示进入这个路由是需要登录的
      },
      component: Unauth
    },
    {
      path: '/login',
      name: 'Login',
      meta: {
        requireAuth: false // 添加该字段，表示进入这个路由是需要登录的
      },
      component: Login
    },
    // {
    //   path: '/',
    //   name: 'Index',
    //   component: INDEX,
    //   meta: {
    //     requireAuth: true // 添加该字段，表示进入这个路由是需要登录的
    //   },
    //   children: [
    //     {
    //       path: 'user',
    //       name: 'user',
    //       meta: {
    //         requireAuth: true
    //       },
    //       component: USER
    //     },
    //     {
    //       path: 'role',
    //       name: 'role',
    //       meta: {
    //         requireAuth: true
    //       },
    //       component: ROLE
    //     },
    //     {
    //       path: 'permission',
    //       name: 'permission',
    //       meta: {
    //         requireAuth: true
    //       },
    //       component: PERMISSION
    //     },
    //     {
    //       path: 'router',
    //       name: 'router',
    //       meta: {
    //         requireAuth: true
    //       },
    //       component: ROUTER
    //     }
    //   ]
    // }
  ]
})
