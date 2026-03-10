//获取全部标签页列表数据
const getTabs = (state) => {
  return state.tabs;
}

//获取当前激活的标签页名称
const getActiveTab = (state) => {
  return state.activeTab;
}

//当前标签页是否存在
const hasTab = (state) => (name) => {

  //some检查列表所有元素是否有元素符合指定条件 - 固定标签栏的首页不在tabs中但也需要判断
  return state.tabs.some(tab => tab.name === name) || name == "HomePage"  ;
}

//获取路由缓存(标签页不关闭缓存页面)
const getCacheComponentName = (state) => {
  //提取每个store路由对象中 meta 属性里的 cacheComponentName 并将它们封装成一个列表
  return state.tabs.map(tab => tab.meta.cacheComponentName);
}

//批量导出
export {
  getTabs,
  getActiveTab,
  hasTab,
  getCacheComponentName
}
