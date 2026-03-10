//添加新的标签页
const addTab = ({ commit, state, dispatch}, tab) => {
  //检查标签页列表中是否已存在该标签页，如果不存在执行操作
  if (!state.tabs.find(t => t.name === tab.name)) {

    //调用mutations的ADD_TAB方法进行添加
    commit('ADD_TAB', tab);

    //并激活当前添加的标签页
    dispatch('setActiveTab', tab.name)
  }
}

//移除标签页
const removeTab = ({ commit, state, dispatch }, tabName) => {

  //调用mutations的REMOVE_TAB移除标签页
  commit('REMOVE_TAB', tabName);

  // 返回标签页列表中的最后一个元素
  const lastTab = state.tabs[state.tabs.length - 1];

  // 元素是否不为null
  if (lastTab) {
    dispatch('setActiveTab', lastTab.name);//激活标签页列表中最后标签页
  } else {
    dispatch('setActiveTab', 'HomePage'); // 没有标签页时返回主页
  }
}

//激活标签页
const setActiveTab = ({ commit }, tabName) => {
  //当tabName不为null: 调用mutations的SET_ACTIVE_TAB修改激活标签页名称
  if(tabName){
    commit('SET_ACTIVE_TAB', tabName);
  }else {
    //激活主页
    commit('SET_ACTIVE_TAB', "HomePage");
  }
}

//清空所有标签页
const clearAllTab = ({ commit }) => {
  //清空tab标签页列表
  commit('CLEAR_ALL_TAB');

}

export {
  addTab,
  removeTab,
  setActiveTab,
  clearAllTab
}
