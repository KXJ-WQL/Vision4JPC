// 部分更新配置信息项
const updateConfigItem = ({ commit }, configItems) => {
  commit("UPDATE_CONFIGITEM", configItems);
};

// 全部覆盖式更新配置信息项
const updateAllConfigItem = ({ commit }, configItems) => {
  commit("UPDATE_ALL_CONFIGITEM", configItems);
};

// 批量导出
export {
  updateConfigItem,
  updateAllConfigItem
}
