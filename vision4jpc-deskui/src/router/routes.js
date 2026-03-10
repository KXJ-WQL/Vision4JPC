// 引入静态路由信息(注：staticRoutes对象是响应式的)
import staticRoutes from './static/routes';

// 引入获取动态路由信息的函数
import {fetchRoutes , convertNavigationRouterList} from './fetch/routes';

// 系统初始化时合并路由并返回
async function getInitRoutes() {
  // 获取动态路由
  const dynamicRoutes = await fetchRoutes();

  // 将动态路由添加到静态路由的根路由的 children 中
  staticRoutes[0].children.push(...dynamicRoutes);

  // 返回合并后的路由配置
  return staticRoutes;
}

// 功能模块修改时合并路由信息
function getUpdateModuleRoutes(dynamicRoutes) {

  // 获取 staticRoutes[0].children 的引用
  const staticChildren = staticRoutes[0].children;

  //将路由信息列表转化为Vue-Router接收的形式(主要是componse组件动态导入)
  const convertNavigationRouterLists= convertNavigationRouterList(dynamicRoutes)

  // 将静态Layout路由中的 children 数组从索引 1 开始的元素替换为动态路由数组中的元素
  staticRoutes[0].children.splice(1, staticChildren.length - 1, ...convertNavigationRouterLists); // 保留原layout子路由的第一个首页路由,动态路由数据中的所有元素

}

export { getInitRoutes, getUpdateModuleRoutes };
