//全部覆盖式更新功能模块项
const updateAllModuleItem = (store, moduleItems) => {
  store.dispatch("sysModuleItem/updateAllModuleItem", moduleItems)
}

//根据获取功能模块项Key判断模块是否开启
const getIsEnabled = (store,key) => {
  // 1.开启 2.关闭
  return "1" == store.getters["sysModuleItem/getModuleByKey"](key)
}

export {
  updateAllModuleItem,
  getIsEnabled
}
