// 根据功能模块key获取是否开启的值(1.开启 2.关闭)
const getModuleByKey = (state) => (key) => {
  return state.moduleItems[key];
};

export { getModuleByKey };
