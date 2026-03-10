// 从指定路径引入获取导航树数据的 API 函数
import { getNavigationTree } from 'apis/system/navigation';
// 引入换路由工具方法，将后端提供的路由组件转化为标准的路由信息(主要是component动态组件问题)
import { transformRoutes } from 'utils/routerUtil';

//异步获取路由数据
async function fetchRoutes() {
  try {
    // 使用 Promise 包装 getNavigationTree 函数，等待数据获取完成
    const response = await new Promise((resolve, reject) => {
      getNavigationTree((data)=>{
        // 数据获取成功时，resolve 传递数据
        resolve(data);
      });
    });
    // 调用 transformRoutes 函数转换路由数据，并返回转换后的路由数组
    return convertNavigationRouterList(response.data);
  } catch (error) {
    // 捕获并打印获取路由数据时的错误
    console.error("Fetch Router Error", error);
    // 返回空数组，表示未能获取路由数据
    return [];
  }
}

//转化路由数据
function convertNavigationRouterList(dynamicRoutes) { 
  // 调用 transformRoutes 函数转换路由数据，并返回转换后的路由数组
  return transformRoutes(dynamicRoutes);
}

// 导出函数
export {
  fetchRoutes,
  convertNavigationRouterList
};
