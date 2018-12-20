<template>
  <div class="tbl_datalist">
    <el-table :data="datalist" @row-click="rowClick" :expand-row-keys="expandRow"  :row-key="setRowKeys" border fit>
      <!--额外部分标题-->
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-row v-for="(g,index) in expandColumn " :key="index">
            <el-col :span="12" v-for="(item,index) in g" :key="index">
              <el-row>
                <el-col :span="8">
                  <div class="expand-label">{{item.label}}</div>
                </el-col>
                <el-col :span="16">
                  <TextFormatter :vt="item.type" v-model="props.row[item.prop]"
                                 :enums="props.row[item.enums]"></TextFormatter>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <!--正常展示标题-->
      <el-table-column v-for="(item ,index) in listColumn" :key="index" :prop="item.prop" :label="item.label"
                       :width="item.width" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <TextFormatter :vt="item.type" v-model="scope.row[item.prop]" :enums="item.enums"></TextFormatter>
        </template>
      </el-table-column>
      <!--操作区域-->
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <!--button.handle(scope.$index, scope.row)-->
          <el-button v-has-permission="button.permission" v-for="(button,index) in buttons" :key="index" @click="$emit('extrabutton-click',{emit:button.emit,index:scope.$index,row:scope.row})"
                     size="mini" :type="button.type">{{button.label}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  /*
  *   columns:[{
  *     type:"string",
  *     prop:"id",
  *     label:"ID",
  *     width:"180",
  *     isListable:false,
  *     isExpand:false,
  *     isEditable:false,
  *     isKey:false
  *   }]
  * */
  import TextFormatter from './TextFormatter'
  import _filter from "lodash/filter"
  import _groupBy from "lodash/groupBy"

  export default {
    name: "ListData",
    components: {TextFormatter},
    props: ["datalist", "columns", "buttons"],
    data() {
      return {
        expandRow: []
      }
    },
    computed: {
      listColumn() {
        return _filter(this.columns, {isListable: true});
      },
      expandColumn() {
        let expandColumns = _filter(this.columns, {isExpand: true});
        let newExpandColumns = expandColumns.map(function (value, index, array) {
          return Object.assign({}, value, {num: index / 2})
        })
        return _groupBy(newExpandColumns, function (c) {
          return Math.floor(c.num)
        });
      },
      keyColumns() {
        return _filter(this.columns, {isKey: true});
      }
    },
    methods: {
      catKeyVal(row){
        let k=""
        for (let o in this.keyColumns) {
          k+="_"+row[this.keyColumns[o].prop];
        }
        return k
      },
      rowClick(row, event, column) {
        let  r=[];
        r.push(this.catKeyVal(row))
        this.expandRow=r
      },
      setRowKeys(row){
        return this.catKeyVal(row)
      }
    }
  }
</script>

<style scoped>
  .table-expand {
    font-size: 0;
  }

  .expand-label {
    color: #909399;
  }
</style>
