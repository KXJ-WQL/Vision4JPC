// 获取指定配置项数据
const getConfigItemByKey = (state) => (key) => {
  return state.configItem[key];
};

// 获取所有配置项数据
const getConfigItems = (state) => () => {
  return state.configItem;
};

// 批量导出
export {
  getConfigItemByKey,
  getConfigItems
}
