class TouchButtonDefinition{
  constructor({label="button",url="",enabled=true, type="button",emit="",permission="default"}){
    this.label=label;//按钮的名称
    this.enabled= enabled;//是否启用按钮
    this.url= url;
    this.type= type;//按钮类型
    this.emit=emit;//触发的事件名称
    this.permission=permission;//按钮的权限
  }
}

class InputDefinition {
  constructor({prop,label,type,options,validate}){
    this.prop=prop;//名称
    this.type= type;//类型
    this.label= label;
    this.options= options;//可选值  对enum类型有效
    this.validate= validate;
  }
}

export  default {TouchButtonDefinition,InputDefinition}
