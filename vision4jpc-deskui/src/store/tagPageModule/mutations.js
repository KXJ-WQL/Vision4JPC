//添加标签页
const ADD_TAB = (state, tab) => {
  state.tabs.push(tab);
};

//移除标签页
const REMOVE_TAB = (state, tabName) => {
  //filter通过router路由数据的name过滤调用符合添加的元素并返回
  state.tabs = state.tabs.filter((tab) => tab.name !== tabName);
};

//激活标签页
const SET_ACTIVE_TAB = (state, tabName) => {
  //激活标签名等于传入的name
  state.activeTab = tabName;
};

//清空所有标签页
const CLEAR_ALL_TAB = (state) => {
  //清空列表
  state.tabs = [];
};

//批量导出
export { ADD_TAB, REMOVE_TAB, SET_ACTIVE_TAB, CLEAR_ALL_TAB };
