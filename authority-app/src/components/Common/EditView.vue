<template>
  <el-form :model="data" :rules="rules" label-width="100px">
    <el-form-item :label="column.label" v-for="(column,index) in columns" :key="index" :prop="column.name">
      <ItemInput v-model="data[column.name]" :item="column"></ItemInput>
    </el-form-item>
  </el-form>
</template>

<script>
  import ItemInput from '@/components/Common/ItemInput'
  // import _cloneDeep from 'lodash/cloneDeep'
  import _filter from 'lodash/filter'

  export default {
    name: "EditView",
    props: ["data", "columns"],
    components: {ItemInput},
    data() {
      return {
        // form:_cloneDeep(this.data)
      }
    },
    computed: {
      rules() {
        let r = {}
        _filter(this.columns, function (c) {
          return c.validate && c.validate.length > 0
        }).forEach(function (c) {
          r[c.name] = c.validate
        })
        return r
      }
    }


  }
</script>

<style scoped>

</style>
