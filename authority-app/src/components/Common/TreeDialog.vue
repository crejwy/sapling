<template>
  <el-dialog :title="title" :visible.sync="visibleSync" :close-on-click-modal="false">
    <el-tree :data="data"
             show-checkbox
             default-expand-all
             node-key="id"
             :ref="treeName"
             highlight-current
             :check-strictly="strictly"
             :default-checked-keys="defaultCheckedKeys">
              <span class="custom-tree-node" slot-scope="{ node, data }">
                <slot name="row" :node="node" :data="data"></slot>
              </span>
    </el-tree>

    <div slot="footer" class="dialog-footer">
      <el-button @click="visibleSync = false">取 消</el-button>
      <el-button type="primary" @click="Confirm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import _date from 'lodash/date'
  export default {
    name: "tree-dialog",
    props: {
      data: Array,
      visible: Boolean,
      title: String,
      defaultCheckedKeys:Array,
      emit: {
        type: String,
        default: 'confirm'
      },
      strictly:{
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        treeName: "",
        emitName: this.emit
      }
    },
    created() {
      this.treeName = "tn" + _date.now()
    },
    computed: {
      visibleSync: {
        get: function () {
          return this.visible
        },
        set: function (newval) {
          this.$emit("update:visible", newval)
        }
      },
      // defaultCheckedKeys() {
      //   let defaultCheckedKeys=[]
      //   this.data.forEach((r)=>{
      //     defaultCheckedKeys.push(...r.belongToRoleChildren)
      //   })
      //   return defaultCheckedKeys
      // }
    },
    methods: {
      Confirm() {
        let that = this
        let fn = that.treeName
        that.$emit(that.emitName,this.$refs[fn].getCheckedNodes())
      }

    }
  }
</script>

<style scoped>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
