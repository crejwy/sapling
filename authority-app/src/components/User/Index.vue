<template>
  <div>
    <crud-facecade ref="crud2" :url="init.url" :columns="init.columnList" :updateButton="init.enableUpdateButton"
                   :buttons="init.buttons" @LoadingAlterPasswordView="LoadingAlterPasswordView"
                   @AssignRoles="AssignRoles">
      <el-row slot="header">
        <el-col :span="4">{{headerName}}</el-col>
      </el-row>
    </crud-facecade>
    <!--修改密码区域-->
    <form-dialog ref="ruleForm2" :title="alterPasswordDialog.title" :data="alterPasswordDialog.data"
                 :visible.sync="alterPasswordDialog.dialogFormVisible" :columns="alterPasswordDialog.columns"
                 @confirm="SavePassword"></form-dialog>
    <!--分配juese-->
    <form-dialog ref="ruleForm3" :title="assignRolesDialog.title" :data="assignRolesDialog.data"
                 :visible.sync="assignRolesDialog.dialogFormVisible" :columns="assignRolesDialog.columns"
                 @confirm="SaveRoles"></form-dialog>
  </div>
</template>

<script>
  import _filter from "lodash/filter"
  import _join from "lodash/join"
  import _find from "lodash/find"
  export default {
    name: "Index",
    data() {
      let validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.alterPasswordDialog.data.newpassword2 !== '') {
            this.$refs.ruleForm2.validateField('newpassword2');
          }
          callback();
        }
      };
      let validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.alterPasswordDialog.data.newpassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        headerName: 'user management',
        init: {
          url: '/user/',
          columnList: [{
            type: "string",
            prop: "uid",
            label: "编号",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: false,
            isCreatable: false,
            isKey: true
          }, {
            type: "string",
            prop: "username",
            label: "名称",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: false,
            isCreatable: true,
            isKey: false,
            validate: [{required: true, message: '必输项', trigger: 'change'}]
          }, {
            type: "string",
            prop: "password",
            label: "密码",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: true,
            isCreatable: true,
            isKey: false,
            validate: [{required: true, message: '必输项', trigger: 'change'}]
          }, {
            type: "enum",
            prop: "state",
            label: "状态",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: false,
            isCreatable: false,
            isKey: false,
            enums: [{value: 0, label: "未激活"}, {value: 1, label: "正常"}, {value: 2, label: "锁定"}]
          }],
          enableUpdateButton: {enabled: false},
          buttons: [{
            label: "分配角色",
            type: "button",
            emit: "AssignRoles",
            permission:"user_role:save"
          }, {
            label: "修改密码",
            type: "button",
            emit: "LoadingAlterPasswordView",
            permission:"user_role:save"
          }]
        },

        alterPasswordDialog: {
          title: "修改密码",
          dialogFormVisible: false,
          data: {username: "", newpassword: "", newpassword2: "", oldpassword: ""},
          columns: [{
            type: "string",
            prop: "oldpassword",
            label: "旧密码:",
            // options: [],
            validate: [{required: true, message: '必输项', trigger: 'change'}]
          }, {
            type: "string",
            prop: "newpassword",
            label: "新密码:",
            // options: [],
            validate: [{validator: validatePass, trigger: 'blur'}, {required: true, message: '必输项', trigger: 'change'}]
          }, {
            type: "string",
            prop: "newpassword2",
            label: "再输一次:",
            // options: [],
            validate: [{validator: validatePass2, trigger: 'blur'}, {required: true, message: '必输项', trigger: 'change'}]
          }]
        },
        roles: [],
        assignRolesDialog: {
          title: "分配角色",
          dialogFormVisible: false,
          data: {username: "", roles: []},
          columns: [{
            type: "checkbox",
            prop: "roles",
            label: "角色:",
            options: this.CatRoleCheckbox,
            validate: [{required: true, message: '必输项', trigger: 'change'}]
          }]
        }
      }
    },
    created() {
      this.LoadingRoles()
    },
    computed: {
      CatRoleCheckbox(){
        return this.roles.map(function (val, index, array) {
          // return {value:val.role,label:val.description}
          return val.role
        })
      }
    },
    methods: {
      LoadingRoles() {
        let that = this
        this.axios.get("/role/", {
            headers: {},
            emulateJSON: true
          }
        ).then((response) => {
          if (response.status == 200) {
            that.roles = _filter(response.data,{available:true})
          } else {
            that.$message({
              type: 'error',
              message: response.msg
            });
          }

        }, (res) => {
          console.log(res);
        });
      },
      LoadingAlterPasswordView({row, index}) {
        this.alterPasswordDialog.data.username = row.username;
        this.alterPasswordDialog.dialogFormVisible = true;
      },
      AssignRoles({row, index}) {
        this.assignRolesDialog.columns[0].options=this.CatRoleCheckbox
        this.assignRolesDialog.data.user=row;
        this.assignRolesDialog.data.roles=row.roles;
        this.assignRolesDialog.data.username = row.username;
        this.assignRolesDialog.dialogFormVisible = true;
      },
      SavePassword() {
        let that = this
        // console.log(this.alterPasswordDialog.data)
        this.axios.post("/user/alterpassword", this.alterPasswordDialog.data, {
            headers: {},
            emulateJSON: true
          }
        ).then((response) => {
          if (response.status == 200) {
            that.alterPasswordDialog.dialogFormVisible = false;
            that.$refs["crud2"].QueryListData();
          } else {
            that.$message({
              type: 'error',
              message: response.msg
            });
          }

        }, (res) => {
          console.log(res);
        });
      },
      SaveRoles() {
        let that = this
        let user=this.assignRolesDialog.data.user;


        let roleList=[]
        let selectRoles=this.assignRolesDialog.data.roles.split(",");
        selectRoles.forEach(function(role){
          if(role){roleList.push(_find(that.roles,{role:role}))}
        });
        user.roleList=roleList;

        this.axios.post("/user/assignroles",user , {
            headers: {
              'Content-Type': 'application/json;charset=UTF-8'
            },
            emulateJSON: true
          }
        ).then((response) => {
          // console.log(response);
          if(response.status==200){
            that.assignRolesDialog.dialogFormVisible = false;
            that.$refs["crud2"].QueryListData();
            // that.QueryListData()

          }else {
            this.$message({
              type: 'error',
              message: response.msg
            });
          }

        }, (res) => {
          console.log(res);
        });
      }
    }
  }
</script>

<style scoped>

</style>
