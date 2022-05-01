/*
 * @Author: fauchard
 * @Date: 2021-11-02 09:58:00
 * @LastEditTime: 2021-12-22 15:40:36
 * @LastEditors: Please set LastEditors
 * @Description: 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 * @FilePath: \vue\src\router\index.js
 */
import {createRouter, createWebHistory} from 'vue-router'
import Layout from '../layout/Layout.vue'

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        redirect: "/home",
        children: [
            {
                path: 'home',
                name: 'Home',
                component: () => import("@/views/Home"),
            },
            {
                path: 'user',
                name: 'User',
                component: () => import("@/views/User"),
            },
            {
                path: 'plank',
                name: 'Plank',
                component: () => import("@/views/Plank"),
            },
            
            {
                path: 'news',
                name: 'News',
                component: () => import("@/views/News"),
            },
            {
                path: 'person',
                name: 'Person',
                component: () => import("@/views/Person"),
            }
        ]
    },
    {
        path: '/about',
        name: 'about',
        component: () => import("@/views/User")
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import("@/views/Login")
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import("@/views/Register")
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

// 限制某些页面禁止未登录访问
let limitPagePath = ['/about']

router.beforeEach((to, from, next) => {
    if (limitPagePath.includes(to.path)) {
        // 判断sessionStorage是否保存了用户信息
        let userStr = sessionStorage.getItem("user") || "{}"
        let user = JSON.parse(userStr)
        if (!user.id) {
            // 跳转到登录页面
            next({path: "/login"})
        } else {
            next()
        }
    } else {
        next()
    }

})

export default router
