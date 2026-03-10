import { route } from 'quasar/wrappers'
import { createRouter, createMemoryHistory, createWebHistory, createWebHashHistory } from 'vue-router'
import { getInitRoutes } from './routes'

export default route(async function (/* { store, ssrContext } */) {

  //根据环境变量选择合适的历史记录模式
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : (process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory : createWebHashHistory)

  //获取路由配置：调用 getRoutes 方法，这个方法会返回一个包含所有路由配置的 Promise
  const routes = await getInitRoutes()

  //创建 Vue Router 实例
  const Router = createRouter({
    //滚动行为设置，每次导航后滚动到页面顶部
    scrollBehavior: () => ({ left: 0, top: 0 }),
    //路由配置，包含所有的路由规则
    routes,
    //历史记录模式，使用 createHistory 方法创建的历史记录对象
    history: createHistory(process.env.VUE_ROUTER_BASE)
  })


  // 返回Router实例
  return Router
})
