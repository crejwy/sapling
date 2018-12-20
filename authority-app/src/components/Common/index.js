import CrudFacecade from './CrudFacecade.vue'
import FormDialog from './FormDialog.vue'
import ListView from './ListView.vue'
import TextFormatter from './TextFormatter.vue'
import ItemInput from './ItemInput.vue'
import EditView from './EditView.vue'
import TreeDialog from './TreeDialog.vue'


const components = [
  CrudFacecade,  FormDialog,ListView,TextFormatter,ItemInput,EditView,TreeDialog
]

const install = function(Vue, opts = {}) {
  components.map(component => {
    Vue.component(component.name, component);
  })
}


// 这里的判断很重要
if (typeof window !== 'undefined' && window.Vue) {
  // window.Vue.use(comment)
  install(window.Vue);
}
export default {install,CrudFacecade,  FormDialog,ListView,TextFormatter,ItemInput,EditView,TreeDialog}
