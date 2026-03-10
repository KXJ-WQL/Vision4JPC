// 使用 Vite 的 import.meta.glob 动态导入 pages 目录下的所有 .vue 文件
const pages = import.meta.glob('pages/**/*.vue');

/*
* 根据路径加载组件
* 注：
*   1. 所有组件路径都必须在pages包下的
*   2.数据库中路径的命名规则是相对pages目录的路径且不要文件vue后缀名
* 例：数据库设置的组件地址system/sys，它对应着pages/system/sys.vue
* 如果动态路由地址出问题，请修改这个地方的代码
* */
const loadPage = (path) => {
  // 调整路径，在前面加上 '../../pages/'，并在末尾加上 '.vue'（这样是为了匹配pages的key）
  const adjustedPath = `../pages/${path}.vue`;
  // 检查 pages 对象中是否有这个路径对应的组件，如果有则返回组件的动态导入函数，否则返回 null
  return pages[adjustedPath] ? pages[adjustedPath]() : null;
};

//转换路由数据，将后端提供的路由数据转换为前端需要的格式(主要是component的组件加载问题)
const transformRoutes = (routeData) => {
  return routeData.map(route => {
    const transformedRoute = {
      path: route.path, // 路由的路径
      name: route.name, // 路由的名称
      component: route.component // 根据组件路径加载组件
        ?  () => loadPage(route.component) //loadPage返回一个Promise，使用箭头函数将 Promise 封装，延迟 Promise 的执行
        : null,
      meta: route.meta || {}, // 路由的元数据，例如标题、图标等
      children: route.children // 如果有子路由，递归转换子路由
        ? transformRoutes(route.children)
        : []
    };

    return transformedRoute; // 返回转换后的路由对象
  });
}

/*
* 处理路由变化的逻辑：在标签页变化时判断新标签对应的路由是否已存在，若不存在则动态添加路由
* 这个逻辑的产生背景：我通过router中的getUpdateModuleRoutes动态数据添加合并路由时，这个路由信息只存在于layout的childer中，在router.getRoutes()是找不到路由信息的，导致push()时报错(找不到路由name)
* 解决：为了解决push找不到的问题，我动态将layout的childer中的路由，动态添加到router.getRoutes()路由列表中
* */
const handleRouteChange = (router, newActiveTab) => {
  // 获取当前所有已注册的路由，查找其中是否存在与新标签页名称（newActiveTab）匹配的目标路由
  // 若不存在此路由，则尝试在名为 'layout' 的路由的子路由中递归查找
  const targetRoute = router.getRoutes().find(route => route.name === newActiveTab)
    // 使用可选链操作符（?.）确保在 layout 路由存在的情况下再进行子路由查找
    || (findRouteInLayoutChildren(router.getRoutes().find(route => route.name === 'layout')?.children, newActiveTab)
      // 如果在 layout 子路由中找到目标路由，则动态添加该路由到 layout 路由中
      && addChildRoutes(router, findRouteInLayoutChildren(router.getRoutes().find(route => route.name === 'layout')?.children, newActiveTab)));

  // 如果目标路由（targetRoute）存在，无论是原有的还是新添加的，都执行导航操作
  targetRoute && router.push({ name: newActiveTab });
}

// 递归查找 layoutRoute 子路由下的目标路由：用于在 layout 路由的嵌套路由中查找指定名称的路由
const findRouteInLayoutChildren = (children, targetName) =>
  // 判断 children 是否为数组，如果是则使用 reduce 方法迭代所有子路由
  // reduce 的目的在于遍历 children 数组中的每一个路由，并累积找到的目标路由
  Array.isArray(children) ? children.reduce((found, child) =>
    // 如果已经找到与目标名称匹配的路由（found 非 null），则直接返回该路由
    // 否则，继续检查当前子路由是否匹配目标名称，若匹配则返回该子路由
    // 如果当前子路由不匹配目标名称且其本身还有子路由，则递归查找其子路由
    found || (child.name === targetName ? child : findRouteInLayoutChildren(child.children, targetName)), null) : null;

// 动态添加子路由到 layout 路由：将找到的目标路由对象作为 layout 的子路由进行动态添加
const addChildRoutes = (router, routeToAdd) =>
  // 使用 router.addRoute 方法将新路由对象添加到名为 'layout' 的父路由中
  // 添加的路由包括 path、name、component 及路由的元信息 meta
  router.addRoute('layout', { path: routeToAdd.path, name: routeToAdd.name, component: routeToAdd.component, meta: routeToAdd.meta });


export {
  transformRoutes,
  handleRouteChange
}
