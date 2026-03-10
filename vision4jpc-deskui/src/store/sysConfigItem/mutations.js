/*
* 部分更新配置信息项
* 遍历传入的 configItems 对象的键，检查 state.configItem 中是否存在相同的键。
* 如果存在相同的键，则更新 state.configItem 中对应键的值为 configItems 中的值
*/
const UPDATE_CONFIGITEM = (state, configItems) => {
  // 如果 state.configItem 中存在 key，则更新其值
  Object.keys(configItems).forEach(key => {
    if (state.configItem.hasOwnProperty(key)) {
      state.configItem[key] = configItems[key];
    }
  });
};

// 全部覆盖式更新配置信息项
const UPDATE_ALL_CONFIGITEM = (state, configItems) => {
  state.configItem = { ...configItems };  // 直接替换整个对象
};

// 批量导出
export {
  UPDATE_CONFIGITEM,
  UPDATE_ALL_CONFIGITEM,
}
