import Vue from 'vue'
import 'es6-promise/auto'
import Vuex from 'vuex'

Vue.use(Vuex)

import componentDefinition from './components/componentDefinition'
import _filter from 'lodash/filter'
function switchRouter(routers,allpermission) {
  return routers.map((r) => {
    let name=r.permission.split(":")[0];
    return {
      path: r.url,
      name: name,
      meta: {
        requireAuth: true,
        permissions: _filter(allpermission,{ parentId : r.id }).map(p=>p.permission)
      },
      component: componentDefinition[name.toUpperCase()],
      children: r.children.length>0 ?switchRouter(r.children,allpermission):[]
    }
  })
}

/*界面元素权限控制*/
Vue.directive('hasPermission', {
  bind: function (el, binding, vnode) {
    let permissionList =[];
    vnode.context.$route.matched.forEach(r=>{
      permissionList.push(...r.meta.permissions)
    })
    if("default"!==binding.value && (permissionList==undefined || permissionList.length==0 || !permissionList.includes(binding.value))){
      el.parentNode.removeChild(el)
    }
  }
})



export default new Vuex.Store({
  state: {
    username:"",
    roleIds: [],
    routers: [],
    // permissions:[]
  },
  getters: {
    username:(state)=>{
      return state.username
    },
    roleIds: (state, getters) => {
      let roleIds = state.roleIds
      return roleIds
    },
    routers: (state, getter) => {
      return state.routers
    },
    // permissions:(state)=>{
    //   return state.permissions
    // }
  },
  actions: {
    GenerateRole({commit}, axios) {
      return axios.get("/user/queryUserByToken").then((response) => {
          return commit("setRole", response)
      });
    },

    GenerateRouters: ({commit, getters,dispatch}, axios) => {
      return axios.post("/router/queryAllRouterByRoles",{roleIds: getters.roleIds})
        .then((response) => {
          let  r=switchRouter(response)
          return commit("setRouters", r)
      });
    }
  },
  mutations: {
    setRouters: (state, payload) => {
      state.routers = payload
    },
    setRole: (state, acuser) => {
      state.username=acuser.username;
      // state.permissions=acuser.permissions;
      state.routers=switchRouter(acuser.routers,acuser.permissions);
      state.roleIds = acuser.roleList.map((r)=>r.id);
    }
  }
})
