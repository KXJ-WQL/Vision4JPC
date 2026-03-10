
//部分更新配置信息项(存在key则更新其值)
const updateConfigItem = (store, configItems) => {
  //当前点击菜单是否存在标签页 - 1.不存在则添加新的标签页 2.存在则激活标签页
  store.dispatch("sysConfigItem/updateConfigItem", configItems)
}

//全部覆盖式更新配置信息项
const updateAllConfigItem = (store, configItems) => {
  //当前点击菜单是否存在标签页 - 1.不存在则添加新的标签页 2.存在则激活标签页
  store.dispatch("sysConfigItem/updateAllConfigItem", configItems)
}

//根据配置项Key获取数据值
const getValueByKey = (store,key) => {
  //通过key获取value
  return store.getters["sysConfigItem/getConfigItemByKey"](key)
}

//获取所有配置项的数据值
const getAllValues = (store) => {
  //通过key获取value
  return store.getters["sysConfigItem/getConfigItems"]()
}

export {
  updateConfigItem,
  updateAllConfigItem,
  getValueByKey,
  getAllValues
}
