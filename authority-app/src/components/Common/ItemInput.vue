<template>
  <div v-if="item.type=='number'">
    <el-input-number :name="item.name" v-model="numberValue" placeholder="请输入数字" @change="change"></el-input-number>
  </div>
  <div v-else-if="item.type=='string'">
    <el-input :name="item.name" v-model="textValue" placeholder="请输入内容" @change="change"></el-input>
  </div>
  <div v-else-if="item.type=='select'||item.type=='enum'">
    <el-select :name="item.name" v-model="textValue" placeholder="请选择" @change="change">
      <el-option
        v-for="(op,index) in item.options"
        :key="index"
        :label="op.label"
        :value="op.value">
      </el-option>
    </el-select>
  </div>
  <div v-else-if="item.type=='checkbox'">
    <el-checkbox-group v-model="listValue" @change="change">
      <el-checkbox border :label="op"   v-for="(op,index) in item.options" :key="index"></el-checkbox>
    </el-checkbox-group>
  </div>
  <div v-else-if="item.type=='bool'">
    <el-switch    :name="item.name"  v-model="checked"      active-color="#13ce66"      inactive-color="#ff4949" @change="change"></el-switch>
  </div>
  <div v-else-if="item.type=='datetime'">
    <input type="hidden" v-model="dateValue"/>
    <el-date-picker
      :name="item.name"
      v-model="dateValue1"
      value-format="yyyy-MM-dd HH:mm:ss"
      format="yyyy-MM-dd HH:mm:ss"
      type="datetime"
      placeholder="选择日期时间"
      default-time="12:00:00" @change="change">
    </el-date-picker>
  </div>
  <div v-else-if="item.type=='date'">
    <input type="hidden" v-model="dateValue"/>
    <el-date-picker
      :name="item.name"
      v-model="dateValue1"
      value-format="yyyy-MM-dd"
      format="yyyy-MM-dd"
      type="date"
      placeholder="选择日期"
      @change="change">
    </el-date-picker>
  </div>
  <div v-else-if="item.type=='time'">
    <input type="hidden" v-model="dateValue"/>
    <el-date-picker
      :name="item.name"
      v-model="dateValue1"
      value-format="HH:mm"
      format="HH:mm"
      type="time"
      placeholder="选择时间"
      @change="change">
    </el-date-picker>
  </div>

</template>


<script>
  import _join from 'lodash/join'

  export default {
    name: "ItemInput",
    props: ['item', 'value'],
    data() {
      return {
        dateValue1: "",
        _textValue: "",
        _numberValue: 0,
        _listValue: [],
        _checked: false,
      }
    },

    computed: {
      textValue: {
        get: function () {
          return this.value
        },
        set: function (newval) {
          this._textValue = newval
        }
      },
      dateValue: {
        get: function () {
          return this.value
        },
        set: function (newval) {
          this.dateValue1 = newval
        }
      },
      numberValue: {
        get: function () {
          return this.item.type == 'number' ? this.value : 0
        },
        set: function (newval) {
          this._numberValue = newval
        }
      },
      listValue: {
        get: function () {
          return this.item.type == 'checkbox' ? this.value.toString().split(',') : []
        },
        set: function (newval) {
          this._listValue = newval
        }
      },
      checked: {
        get: function () {
          return this.item.type == 'bool' ? this.value == true  : false
        },
        set: function (newval) {
          this._checked = newval
        }
      }
    },
    methods: {
      change() {
        let v;
        switch (this.item.type) {
          case  'number':
            v = this._numberValue;
            break;
          case 'date':
          case 'time':
          case 'datetime':
            v = this.dateValue1;
            break;
          case  'string':
          case 'enum':
          case  'select':
            v = this._textValue;
            break;
          case  'checkbox':
            v = _join(this._listValue, ',');
            break;
          case  'bool':
            v = this._checked ;
            break;
        }
        this.$emit("input", v)
      }
    }
  }
</script>

<style scoped>

</style>
