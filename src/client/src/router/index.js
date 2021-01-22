import Vue from 'vue'
import VueRouter from 'vue-router'
import api from '../api'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    redirect: '/bid'
    // component: () => import('../views/Home.vue')
  },
  {
    path: '/bid',
    component: () => import('../views/Bid.vue'),
    children: [
      {
        name: 'bid-list',
        path: '',
        component: () => import('../views/bid/List.vue')
      },
      {
        name: 'bid-create',
        path: 'create',
        component: () => import('../views/bid/Create.vue')
      },
      {
        name: 'bid-update',
        path: 'update/:id',
        component: () => import('../views/bid/Update.vue')
      },
      {
        name: 'bid-detail',
        path: 'detail/:id',
        component: () => import('../views/bid/Detail.vue')
      }
    ]
  },
  {
    path: '/auth',
    component: () => import('../views/Auth.vue'),
    children: [
      {
        path: '',
        name: 'Login',
        component: () => import('../views/auth/Login.vue')
      },
      {
        path: 'register',
        name: 'Register',
        component: () => import('../views/auth/Register.vue')
      },
    ]
  },
  {
    // will match everything
    path: '*',
    component: () => import('../views/exceptions/NotFound.vue')
  }
]

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // redirect to login page if not logged in and trying to access a restricted page
  const publicPages = ['/auth'];
  const authRequired = !publicPages.includes(to.path);

  if (authRequired && !api.isLogged()) {
    return next('/auth');
  }

  next();
})

export default router
