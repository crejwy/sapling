<template>
  <div>
    <crud-facecade :url="init.url" :columns="init.columnList" :buttons="init.buttons"
                   @AssignPermission="AssignPermission" @AssignRouter="AssignRouter">
      <el-row slot="header">
        <el-col :span="4">{{headerName}}</el-col>
      </el-row>
    </crud-facecade>


    <tree-dialog :title="assignRouterDialog.title" :strictly="true" :visible.sync="assignRouterDialog.dialogFormVisible"
                 :data="assignRouterDialog.data" :default-checked-keys="assignRouterDialog.defaultCheckedKeys" @confirm="SaveRouter">
      <template slot="row" slot-scope="{ node, data }">
        <span>{{ data.name }}</span>
        <span>
                  {{data.path}}
                </span>
      </template>
    </tree-dialog>

    <tree-dialog :title="assignPermission.title" :strictly="true" :visible.sync="assignPermission.dialogFormVisible"
                 :data="assignPermission.data" :default-checked-keys="assignPermission.defaultCheckedKeys" @confirm="SavePermission">
      <template slot="row" slot-scope="{ node, data }">
        <span>{{ data.name }}</span>
        <span>
                  {{data.path}}
                </span>
      </template>
    </tree-dialog>
  </div>


</template>

<script>
  import TreeDialog from "../Common/TreeDialog";

  export default {
    name: 'RoleIndex',
    components: {TreeDialog},
    data() {
      return {
        headerName: 'role management',
        init: {
          url: '/role/',
          columnList: [{
            type: "string",
            prop: "id",
            label: "编号",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: false,
            isCreatable: false,
            isKey: true
          }, {
            type: "string",
            prop: "role",
            label: "角色",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: false,
            isCreatable: true,
            isKey: false,
            validate: [{required: true, message: '必输项', trigger: 'change'}]
          }, {
            type: "string",
            prop: "description",
            label: "描述",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: true,
            isCreatable: true,
            isKey: false
          }, {
            type: "bool",
            prop: "available",
            label: "是否可用",
            width: "180",
            isListable: true,
            isExpand: false,
            isEditable: true,
            isCreatable: false,
            isKey: false
          }],
          buttons: [{
            label: "分配权限",
            type: "button",
            emit: "AssignPermission",
            permission:"role_permission:view"
          }]

        },

        assignRouterDialog: {
          title: "分配路由",
          dialogFormVisible: false,
          data: [],
          roleId:"",
          defaultCheckedKeys: []
        },

        assignPermission: {
          title: "分配权限",
          dialogFormVisible: false,
          data: [],
          roleId:"",
          defaultCheckedKeys: []
        }
      }
    },
    computed: {},
    methods: {
      AssignPermission({row, index}) {
        let that = this
        that.assignPermission.roleId=row.id
        this.axios.get("/permission/queryAllPermissionAndMarkByRole", {
            emulateJSON: true,
            params: {
              roleId: row.id
            }
          }
        ).then((response) => {
          if (response.status == 200) {
            that.assignPermission.dialogFormVisible = true
            that.assignPermission.data = response.data
            that.assignPermission.defaultCheckedKeys=[]
            response.data.forEach((r) => {
              that.assignPermission.defaultCheckedKeys.push(...r.childrenChecked)
            })
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
      AssignRouter({row, index}) {
        let that = this
        that.assignRouterDialog.roleId=row.id
        this.axios.get("/router/queryAllRouterAndMarkByRole", {
            emulateJSON: true,
            params: {
              roleId: row.id
            }
          }
        ).then((response) => {
          if (response.status == 200) {
            that.assignRouterDialog.dialogFormVisible = true
            that.assignRouterDialog.data = response.data
            that.assignRouterDialog.defaultCheckedKeys=[]
            response.data.forEach((r) => {
              that.assignRouterDialog.defaultCheckedKeys.push(...r.childrenChecked)
            })
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
      SaveRouter(e) {
        let  that=this
        let checkedKeys=e.map( (v)=> {
          return v.id
        })
        // console.log(checkedKeys)
        this.axios.post("/role/saveRoleRouter", {
          roleId:this.assignRouterDialog.roleId,
            routers:checkedKeys
          },{
            emulateJSON: true
          }
        ).then((response) => {
          if (response.status == 200) {
            that.assignRouterDialog.dialogFormVisible = false
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
      SavePermission(e) {
        let  that=this
        let checkedKeys=e.map( (v)=> {
          return v.id
        })
        this.axios.post("/role/saveRolePermission", {
            roleId:this.assignPermission.roleId,
            permissions:checkedKeys
          },{
            emulateJSON: true
          }
        ).then((response) => {
          if (response.status == 200) {
            that.assignPermission.dialogFormVisible = false
          } else {
            that.$message({
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
