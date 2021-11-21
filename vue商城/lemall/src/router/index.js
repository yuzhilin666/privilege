import Vue from 'vue'
import Router from 'vue-router'

import GoodsList from '@/views/GoodsList'
import Cart from '@/views/Cart'
import Address from '@/views/Address'
import OrderConfirm from '@/views/OrderConfirm'
import OrderSuccess from '@/views/OrderSuccess'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'GoodsList',
      meta:{
        title:'商城首页',
        login_require:false
      },
      component:GoodsList
    },
    {
      path: '/cart',
      name: 'Cart',
      meta: {
        title: '购物车',
        login_require: true
      },
      component:Cart
    },
    {
      path: '/goods',
      name: 'GoodsList',
      meta: {
        title: '商城首页',
        login_require: false
      },
      component: GoodsList
    },
    {
      path: '/address',
      name: 'Address',
      meta: {
        title: '地址页',
        login_require: true
      },
      component:Address
    },
    {
      path: '/orderConfirm',
      name: 'OrderConfirm',
      meta: {
        title: '订单确认',
        login_require: true
      },
      component:OrderConfirm
    },
    {
      path: '/orderSuccess',
      name: 'OrderSuccess',
      meta: {
        title: '订单成功',
        login_require: true
      },
      component:OrderSuccess
    }
  ]
})
