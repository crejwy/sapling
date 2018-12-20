<template>
  <div class="login_page">
    Name:
    <el-input v-model="user.username"></el-input>
    Password:
    <el-input v-model="user.password"></el-input>
    <div>
      <el-button @click="submit(user)">提交</el-button>
    </div>
    <el-alert v-show="loginfailure==true"
              title="登录失败"
              type="error"
              center
              :description="description"
              show-icon>
    </el-alert>
  </div>
</template>

<script>
  export default {
    name: 'Login',
    data() {
      return {
        user: {
          username: '',
          password: ''
        },
        description: '',
        loginfailure: false
      }
    },
    methods: {
      submit(user) {
        let that = this
        this.myaxios.post('/login', user).then((response) => {
          return sessionStorage.setItem('token', response.token)

        }).catch((res) => {
          this.description = res
          this.loginfailure = true
          setTimeout(function () {
            that.description = ""
            that.loginfailure = false
          }, 2000)
        }).then(()=>{
          this.$store.dispatch("GenerateRole", this.myaxios).then(() => {
            return this.$router.addRoutes(this.$store.getters.routers) // 动态添加可访问路由表
          }).then(() => {
            location.href = '/#/'
            location.reload()
          })
        })
      }
    }
  }
</script>

<style scoped>
  .login_page {
    width: 300px;
    height: 500px;
  }
</style>
