import { store } from 'quasar/wrappers'
import { createStore } from 'vuex'
import tagPageModule from './tagPageModule'
import sysConfigItem from './sysConfigItem'
import sysModuleItem from './sysModuleItem'

export default store(function (/* { ssrContext } */) {
  const Store = createStore({
    modules: {
      tagPageModule,
      sysConfigItem,
      sysModuleItem
    },
    // 为开发环境和带 --debug 参数的生产环境开启严格模式
    strict: process.env.DEBUGGING
  })

  return Store
})
