// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// import VueResource from 'vue-resource';

/*使用VueResource插件*/
// Vue.use(VueResource);
import axios from 'axios'
import Qs from 'qs'
//QS是axios库中带的，不需要我们再npm安装一个
import myaxios from './myaxios'
Vue.prototype.axios = axios;
Vue.prototype.qs = Qs;
// Vue.prototype.HOST='/api'
Vue.prototype.myaxios=myaxios

axios.defaults.baseURL = 'http://localhost:8089';
// axios.defaults.headers.common['Authorization'] = sessionStorage.getItem('token');
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';


// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  let token = sessionStorage.getItem('token');
  if (token) { // 判断是否存在token，如果存在的话，则每个http header都加上token
    config.headers.Authorization = `${token}`;
    // config[emulateJSON= true
  }
  if("post,put".includes(config.method) && config.headers['Content-Type']==undefined){
    //默认axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
    config.data = Qs.stringify(config.data, { indices: false });
  }
  config.headers["X-Requested-With"]="XMLHttpRequest";
  return config;
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
  // 对响应数据做点什么
  if(response.data.status === 401) {
    router.push({ path: 'Login' })
  } else if (response.data.status === 403) {
    ElementUI.Message.error("无权限访问！")
  }else if (response.data.status === 404) {
    router.push({ path: 'S404' })
  }
  return response.data
}, function (error) {
  // 对响应错误做点什么
  // console.log(error);
  return Promise.reject(error)
})

Vue.use(ElementUI, { size: 'small', zIndex: 3000 }) //引用element-ui 并设置尺寸
Vue.config.productionTip = false
// 验证token，存在才跳转
router.beforeEach((to, from, next) => {
  let token = sessionStorage.getItem('token')
  if (to.meta.requireAuth==undefined || to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (token) {
      if(store.getters.roleIds.length==0){
        store.dispatch('GenerateRole',myaxios).then((res) => {
          return router.addRoutes(store.getters.routers)
        }).catch((e)=>{
          ElementUI.Message.error(e.msg)
        }).then(()=>{
          router.replace(to)
        })
      }else{
        next()
      }
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  } else {
    next()
  }
})
import moment from 'moment'
Vue.filter('dataFormat',function(val,pattern = 'YYYY-MM-DD HH:mm:ss'){
  return moment(val).format(pattern)
})
import  _find from 'lodash/find'
import  _size from  'lodash/size'
Vue.filter('enumFormat',function(val,pattern){
  if(!pattern||_size(pattern)==0){return val;}
  else{
    let e=_find(pattern, ['value', val]);
    if(e){
      return  e.label
    }
    return val
  }
})

/*全局引用自定义的插件*/
import CrudFacecade from '@/components/Common/index'
Vue.use(CrudFacecade)


/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
