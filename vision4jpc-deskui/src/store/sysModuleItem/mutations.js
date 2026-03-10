// 全部覆盖式更新功能模块项
const UPDATE_ALL_MODULEITEM = (state, moduleItems) => {
  state.moduleItems = { ...moduleItems };  // 直接替换整个对象
};

export { UPDATE_ALL_MODULEITEM };
