<template>
  <crud-facecade  :url="init.url"  :columns="init.columnList"  :buttons="init.buttons" >
    <el-row slot="header">
      <el-col :span="4">{{headerName}}</el-col>
    </el-row>
  </crud-facecade>
</template>

<script>
export default {
  name: 'PermissionIndex',
  data() {
    return {
      headerName: 'permission management',
      init: {
        url: '/permission/',
        columnList: [{
          type: "string",
          prop: "id",
          label: "编号",
          // width: "180",
          isListable: true,
          isExpand: false,
          isEditable: false,
          isCreatable: false,
          isKey: true
        }, {
          type: "string",
          prop: "name",
          label: "名称",
          // width: "180",
          isListable: true,
          isExpand: false,
          isEditable: true,
          isCreatable: true,
          isKey: false,
          validate: [{required: true, message: '必输项', trigger: 'change'}]
        }, {
          type: "enum",
          prop: "resourceType",
          label: "类型",
          // width: "180",
          isListable: true,
          isExpand: false,
          isEditable: true,
          isCreatable: true,
          isKey: false,
          enums: [{value: "menu", label: "menu"}, {value: "button", label: "button"}, {value: "request", label: "request"}]
        }, {
          type: "string",
          prop: "url",
          label: "路由",
          // width: "180",
          isListable: true,
          isExpand: false,
          isEditable: true,
          isCreatable: true,
          isKey: false,
          // validate: [{required: true, message: '必输项', trigger: 'change'}]
        }, {
          type: "string",
          prop: "permission",
          label: "权限代号",
          // width: "180",
          isListable: true,
          isExpand: false,
          isEditable: true,
          isCreatable: true,
          isKey: false,
          validate: [{required: true, message: '必输项', trigger: 'change'}]
        }, {
          type: "string",
          prop: "parentId",
          label: "父编号",
          // width: "180",
          isListable: false,
          isExpand: true,
          isEditable: true,
          isCreatable: true,
          isKey: false
        }, {
          type: "bool",
          prop: "available",
          label: "是否可用",
          // width: "180",
          isListable: true,
          isExpand: false,
          isEditable: true,
          isCreatable: false,
          isKey: false
        }]

      }
    }
  }
}
</script>

<style scoped>

</style>
