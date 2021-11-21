// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import axios from 'axios'
import store from './store'
import VueLazyload from 'vue-lazyload'
import infiniteScroll from  'vue-infinite-scroll'
import {currency} from './util/currency'

import './assets/css/base.css'
import './assets/css/checkout.css'
import './assets/css/product.css'

Vue.prototype.axios = axios;
Vue.use(Vuex);
Vue.use(infiniteScroll);
Vue.use(VueLazyload, {
  loading: 'static/loading-svg/loading-bars.svg',
  try: 3 // default 1
})

// 设置接口的前缀
axios.defaults.baseURL = '/api';
axios.defaults.timeout = 8000;

Vue.config.productionTip = false;

Vue.filter("currency",currency);

// 拦截返回值
axios.interceptors.response.use((response)=>{
  let res = response.data;
  if(res.status == 0){
    return response;
  }else{
    if(response.config.url != '/users/checkLogin'){
      if (res.status == 10008) {
        alert(res.msg)
        return Promise.reject(res.msg);
      } else {
        alert(res.msg)
        return Promise.reject(res.msg);
      }
    }else{
      return response;
    }
  }
},(error)=>{
  let res = error.response;
  alert(res.data.message)
    return Promise.reject(error);
})

// router的钩子拦截
// router.beforeEach((to,from,next)=>{
//   if(to.meta.login_require){
//     next('/goods');
//   }else{
//     document.title = to.meta.title;
//     next();
//   }
// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App),
}).$mount('#app')
