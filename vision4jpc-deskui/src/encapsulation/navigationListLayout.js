//过滤路由数据，移除普通页面，只保留菜单路由。
const filterTypeCommonPages = (routes) => {
  return routes
    // 过滤掉 type 等于 "2" 的普通页面路由
    .filter(route => route.meta.type !== "2")
    .map(route => {
      // 如果当前路由项有子路由，则递归过滤子路由
      if (route.children && route.children.length > 0) {
        route.children = filterTypeCommonPages(route.children); // 递归调用，处理子路由
      }
      return route; // 返回处理后的路由项
    });
};

// 递归查找指定名称的子路由
const findChildrenByName = (routes, name) => {
  //变量主路由列表
  for (const route of routes) {
    //是否为当前点击主路由名称
    if (route.name === name) {
      //获取children属性，判断：1.不为空且元素大于0返回子路由列表 2.为空且元素小于等于0返回null
      return route.children && route.children.length > 0
        ? route.children
        : null;
    }

    //children属性是否存在
    if (route.children) {
      //递归路由
      const foundChildren = findChildrenByName(route.children, name);
      //递归跳出条件
      if (foundChildren) {
        //返回子路由信息列表
        return foundChildren;
      }
    }
  }
  return null;
};

// 更新路由列表的样式
const updateDrawerStyles = (drawerStyles, hasSubRoutes) => {
  if (hasSubRoutes) {
    drawerStyles.leftDrawWidth = 250;
    drawerStyles.leftDrawBreakPoint = 210;
    drawerStyles.containerClass = "full-height row";
    drawerStyles.mainRouteListClass = "col-sm-4 shadow-24";
    drawerStyles.subRouteListClass = " col-sm-8";
    drawerStyles.subRouteVisible = true;
  } else {
    drawerStyles.leftDrawWidth = 50;
    drawerStyles.leftDrawBreakPoint = 70;
    drawerStyles.containerClass = "";
    drawerStyles.mainRouteListClass = "";
    drawerStyles.subRouteListClass = "";
    drawerStyles.subRouteVisible = false;
  }
};

//添加或激活tab标签页
const activeAndAddTab = (store, routeName, routeList) => {
  //当前点击菜单是否存在标签页 - 1.不存在则添加新的标签页 2.存在则激活标签页
  store.getters["tagPageModule/hasTab"](routeName)
    ? store.dispatch("tagPageModule/setActiveTab",routeName)
    : store.dispatch("tagPageModule/addTab",routeList)
}

//激活首页
const activeHomeTag = (store) => {
  //当前点击菜单是否存在标签页 - 1.不存在则添加新的标签页 2.存在则激活标签页
  store.dispatch("tagPageModule/setActiveTab")
}

//清空标签栏
const clearAllTab = (store) => {
  //当前点击菜单是否存在标签页 - 1.不存在则添加新的标签页 2.存在则激活标签页
  store.dispatch("tagPageModule/clearAllTab")
}

//暴露导出
export {
  filterTypeCommonPages,
  findChildrenByName,
  updateDrawerStyles,
  activeAndAddTab,
  activeHomeTag,
  clearAllTab
};
