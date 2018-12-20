<template>
  <div>
    <slot name="header"></slot>
    <el-row>
      <el-col :span="8" :offset="12">
        <el-input v-if="enableFilter.enabled"
          v-model="searchValue"
          size="mini"
          placeholder="输入关键字搜索" @input="_search()"/>
      </el-col>
      <el-col :span="4" v-if="enableAddButton.enabled">
        <el-button v-has-permission="enableAddButton.permission" :type="enableAddButton.type" size="mini" @click="LoadingAddView()">{{enableAddButton.label}}</el-button>
      </el-col>
    </el-row>

    <ListView :datalist="dataList" :columns="columnList" :buttons="enabledButtons" @extrabutton-click="ExtraButtonClick"></ListView>

    <!--编辑区域-->
    <FormDialog :title="editDialog.title" :data="editDialog.data"
                :visible.sync="editDialog.dialogFormVisible"  :columns="editableColumns"  @confirm="UpdateData"></FormDialog>
    <!--新建区域-->
    <FormDialog :title="createDialog.title" :data="createDialog.data"
                :visible.sync="createDialog.dialogFormVisible"  :columns="creatableColumns"  @confirm="SaveData"></FormDialog>
  </div>
</template>

<script>

  import _filter from "lodash/filter"
  import _cloneDeep from "lodash/cloneDeep"
  import _some from "lodash/some"
  import _find from "lodash/find"
  import  definition from './Definition'

  /*
  * init:{
  *   url:"",
  *   filter:{
            enabled: true,
            handle: (rowdata, searchvalue) => {
              return new RegExp(searchvalue).test(rowdata.username)
            },
          },
  *   columns:[{
  *     type:"string",
  *     prop:"id",
  *     label:"ID",
  *     width:"180",
  *     isListable:false,
  *     isExpand:false,
  *     isEditable:false,
  *     isCreatable:false,
  *     isKey:false
  *   }],
  *   buttons: [{
  *         enabled:true,
            name: "修改密码",
            type: "button",
            emit: "LoadingAlterPasswordView"
          }]
  * }
  *
  * */

  let mustColumns=[{
    type: "string",
    prop: "creator",
    label: "创建人:",
    // width: "180",
    isListable: false,
    isExpand: true,
    isEditable: false,
    isCreatable: false,
    isKey: false
  }, {
    type: "datetime",
    prop: "createdt",
    label: "创建时间:",
    // width: "180",
    isListable: false,
    isExpand: true,
    isEditable: false,
    isCreatable: false,
    isKey: false
  }, {
    type: "string",
    prop: "modifier",
    label: "最后一次修改人:",
    // width: "180",
    isListable: false,
    isExpand: true,
    isEditable: false,
    isCreatable: false,
    isKey: false
  }, {
    type: "datetime",
    prop: "modifydt",
    label: "最后一次修改时间:",
    // width: "180",
    isListable: false,
    isExpand: true,
    isEditable: false,
    isCreatable: false,
    isKey: false
  }]

  export default {
    name: "crud-facecade",
    props:{
      url:String,
      filter:Object,
      columns:Array,
      buttons:Array,
      addButton:Object,
      deleteButton:Object,
      updateButton:Object
    },
    components: {
      'ListView':()=>import ('./ListView.vue'),
      'FormDialog':()=>import ('./FormDialog.vue'),
    },
    data() {

      return {
        // columnList: this.columns,
        dataList: [], //所有数据
        searchValue: "", //搜索框输入值
        editDialog: {
          dialogFormVisible: false,//修改编辑区域是否显示
          data: {}, //正在编辑的数据
          title: "",
          url: ""
        },
        createDialog: {
          dialogFormVisible: false,//修改编辑区域是否显示
          data: {}, //正在编辑的数据
          title: "",
          url: ""
        }
      }
    },
    beforeMount() {
      this.QueryListData();
    },
    computed: {
      columnList(){
        let cls=[]
        cls=_cloneDeep(this.columns)
        mustColumns.forEach((row,index,list)=>{
          let  mc=_find(cls,{prop:row.prop})
          if(mc){
            Object.assign(mc,row)
          }else{
            cls.push(_cloneDeep(row))
          }
        })

        return cls
      },
      listColumn() {
        return _filter(this.columnList, {isListable: true});
      },
      enableFilter(){
        let  that=this
        return  Object.assign({
          enabled: true,
          handle: (rowdata, searchvalue) => {
            let reg=new RegExp(searchvalue)
            return _some(that.listColumn,function(o){
              if(o.type=="bool"){
                switch (searchvalue) {
                  case "是":
                  case "否":return rowdata[o.prop];
                }
              }else  if(o.type=="enum"){
                let v=rowdata[o.prop];
                 v=_find(o.enums,{value:v}).label
                return reg.test(v)
              }
              return reg.test(rowdata[o.prop])
            })
          }
        },this.filter||{})
      },
      enableAddButton(){
        return Object.assign({},new definition.TouchButtonDefinition({label:"新建",url:this.url,type:"primary"}),this.addButton)
      },
      enableDeleteButton(){
        return Object.assign({},new definition.TouchButtonDefinition({label:"删除",url:this.url,type:"danger"}),this.deleteButton)
      },
      enableUpdateButton(){
        return Object.assign({},new definition.TouchButtonDefinition({label:"修改",url:this.url,type:"primary"}),this.updateButton)
      },
      enabledButtons() {
        let bts=this.buttons||[]
        let that = this;
        if (this.enableUpdateButton.enabled) {
          bts.push(Object.assign(this.enableUpdateButton, {
            emit:"LoadingUpdateView"
          }));
        }
        if (this.enableDeleteButton.enabled) {
          bts.push(Object.assign(this.enableDeleteButton, {
            emit: "DeleteData"
          }));
        }
        return bts.map(b=>Object.assign(new definition.TouchButtonDefinition({}),b));
      },
      creatableColumns() {
        return this.SelectColumnProperty (_filter(this.columnList, {isCreatable: true}))
      },
      editableColumns() {
        return this.SelectColumnProperty (_filter(this.columnList, {isEditable: true}))
      },
      keyColumns() {
        return _filter(this.columnList, {isKey: true});
      }
    },
    methods: {
      QueryListData() {
        let that = this;
        this.axios.get(this.url, {
            headers: {},
            emulateJSON: true
          }
        ).then((response) => {
          // console.log(response);
          that.dataList = response.data;
        }, (res) => {
          console.log(res);
          that.dataList = [];
        });
      },
      SelectColumnProperty(columnList){
       return columnList.map(function (column, index, array) {
          let r={type: column.type, prop: column.prop,label:column.label}
          if(column.enums&&column.enums.length>0){
            r.options=column.enums
          }
          if(column.validate&&column.validate.length>0){
            r.validate=column.validate
          }
          return r
        });
      },
      _search() {
        if (this.dataList_bak == undefined) {
          this.dataList_bak = _cloneDeep(this.dataList)
        }
        if (this.searchValue === "") {
          this.dataList = _cloneDeep(this.dataList_bak)
          this.dataList_bak == undefined
        }
        else {
          let that = this;
          this.dataList = _filter(this.dataList_bak, function (u) {
            return that.enableFilter.handle(u, that.searchValue)
          })

        }
      },
      ExtraButtonClick(target){
        let e={};
        ({index:e.index,row:e.row}=target);
        if(this[target.emit]){
          this[target.emit](e)
        }else{
          this.$emit(target.emit,e)
        }
      },
      LoadingAddView() {
        let that = this;
        that.createDialog.data = {};
        that.createDialog.dialogFormVisible = true;
        that.createDialog.title = that.enableAddButton.label;
        that.createDialog.url = that.enableAddButton.url;
      },
      LoadingUpdateView({row,index}) {
        let that = this;
        that.editDialog.data = _cloneDeep(row);
        that.editDialog.dialogFormVisible = true;
        that.editDialog.title = that.enableUpdateButton.name;
        that.editDialog.url = that.enableUpdateButton.url;
      },
      SaveData() {
        let that = this
        that.myaxios.post(that.createDialog.url,that.createDialog.data).then((response) => {
          that.QueryListData()
          that.createDialog.dialogFormVisible = false;
        }).catch(e=>console.log(e));;
      },
      UpdateData() {
        let that = this
        that.myaxios.put(that.editDialog.url, that.editDialog.data).then((response) => {
          that.QueryListData()
          that.editDialog.dialogFormVisible = false;
        }).catch(e=>console.log(e));;
      },
      DeleteData({row,index}) {
        let that = this;
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {

          let d_url = that.enableDeleteButton.url;
          let keys = that.keyColumns
          if (keys.length == 0) {
            throw 'Fcecade.vue columnList  must have keys.'
          }

          for (let k = 0; k < keys.length; k++) {
            if (k == 0) {
              d_url += "?";
            } else {
              d_url += "&";
            }
            d_url += keys[k].prop + "=" + row[keys[k].prop];
          }
          that.myaxios.delete(d_url).then((response) => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            that.QueryListData()
          }).catch(e=>console.log(e));

        }).catch((e) => {
          this.$message({
            type: 'info',
            message: e == 'cancel' ? '已取消删除' : e
          });
        });


      }

    }
  }
</script>

<style scoped lang="less">
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
</style>
