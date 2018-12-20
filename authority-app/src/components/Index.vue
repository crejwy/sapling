<template>
  <el-container   >
    <el-header>
      <div class="logo primary">
        <div><i class="el-icon-menu"></i>{{hospitalname}}</div>
        <el-tag>{{username}}</el-tag>
      </div>
      <el-button type="danger" @click="Logout()">退出</el-button>
    </el-header>

    <el-container>
      <el-aside width="200px" >
        <el-menu default-active="1" class="el-menu-vertical-demo" >
          <el-menu-item v-has-permission="item.permission" :index="item.name"  v-for="(item,index) in  menulist" :key="index" @click="chooseMenu(item.name)">
            <i class="el-icon-time"></i>
            <span slot="title">{{item.desc}}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>


  </el-container>
</template>

<script>

export default {
  name: 'Index',
  data () {
    return {
      hospitalname: 'Permision  Management',
      // username: 'brave.wang',
      menulist:[{
        name:"user",
        desc:"用户",
        permission:"user:*"
      },{
        name:"role",
        desc:"角色",
        permission:"role:*"
      },{
        name:"permission",
        desc:"权限",
        permission:"permission:*"
      }]
    }
  },
  computed:{
    username(){
      return this.$store.getters.username
    }

  },
  methods: {
    chooseMenu(routename){
      this.$router.push({name:routename});
    },
    Logout(){
      sessionStorage.removeItem('token')
      location.reload();

    }
  }
}
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    display: flex;
  }
  .el-header button{
    float: right;
    right: 10px;
    position: absolute;
    line-height: 40px;
  }
  .el-menu{
    border: none;
  }
  .el-aside {
    color: #333;
  }
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    /*width: 200px;*/
    /*min-height: auto;*/
  }
  .container{
    height: auto !important;
    border: none !important;
  }
</style>
