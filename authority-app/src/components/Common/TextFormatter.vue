<template>
  <div class="grid-content bg-purple-dark" v-if="valType=='string'">{{ value }}</div>
  <div class="grid-content bg-purple-dark" v-else-if="valType=='date'">{{ value | dataFormat("YYYY-MM-DD") }}</div>
  <div class="grid-content bg-purple-dark" v-else-if="valType=='datetime'">{{ value | dataFormat( 'YYYY-MM-DD HH:mm:ss') }}</div>
  <div class="grid-content bg-purple-dark" v-else-if="valType=='enum'">{{ value | enumFormat(enumsData) }}</div>
  <div class="grid-content bg-purple-dark" v-else-if="valType=='bool'">{{ value?"是":"否" }}</div>
  <div class="grid-content bg-purple-dark" v-else="1==1">{{ value }}</div>
</template>

<script>
    export default {
        name: "TextView",
        props:["value","enums","vt"],
        data(){
          return{
            // val:this.value,
            enumsData:Object.assign({},this.enums),
            valType:this.vt
          }
        }
    }
</script>

<style scoped>

</style>
