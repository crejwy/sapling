<template>
  <el-dialog  :title="title" :visible.sync="visibleSync" :close-on-click-modal="false">
    <el-form :ref="formName" :model="data" :rules="rules" label-width="100px">
      <el-form-item :label="column.label" v-for="(column,index) in columns" :key="index" :prop="column.prop">
        <ItemInput v-model="data[column.prop]" :item="column"></ItemInput>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="visibleSync = false">取 消</el-button>
      <el-button type="primary" @click="Confirm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import ItemInput from '@/components/Common/ItemInput'
  import _filter from 'lodash/filter'
  import _date from 'lodash/date'

  export default {
    name: "form-dialog",
    components: {ItemInput},
    props: {
      data: Object,
      columns: Array,
      visible: Boolean,
      title: String,
      emit:{
        type:String,
        default:'confirm'
      }
    },
    data() {
      return {
        // dt:Object.assign({},this.data),
        formName:"",
        emitName:this.emit
      }
    },
    created(){
      this.formName="fn"+_date.now()
    },
    computed: {
      visibleSync:{
        get:function(){
          return this.visible
        },
        set:function(newval) {
          this.$emit("update:visible",newval)
        }
      },
      rules() {
        let r = {}
        _filter(this.columns, function (c) {
          return c.validate && c.validate.length > 0
        }).forEach(function (c) {
          r[c.prop] = c.validate
        })
        return r
      }
    },
    methods: {
      Confirm() {
        let that=this
        let fn=that.formName
        this.$refs[fn].validate((valid) => {
          if (valid) {
            that.$emit(that.emitName)
          } else {
            return false;
          }
        });
      },
      validateField(prop){
        let that=this
        let fn=that.formName
        this.$refs[fn].validateField(prop)
      }
    }
  }
</script>

<style scoped>

</style>
