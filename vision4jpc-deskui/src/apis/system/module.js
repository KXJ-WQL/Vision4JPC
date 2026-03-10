import { get, put, post, remove } from '../base-http/http';

//获取功能模块树形结构列表
const getModuleTreeList = (callback) => {
  get('/sysModuleTreeList', callback);
};

//获取功能模块普通结构列表
const getModuleList = (callback) => {
  get('/sysModuleList', callback);
};

//获取功能模块Map结构信息(key - isEnable)
const getModuleMap = (callback) => {
  get('/sysModuleMap', callback);
};

//更新功能模块树形结构
const updateModuleTreeList = (moduleValue, callback) => {
  put('/sysModuleTreeList', moduleValue, callback);
};

//新增功能模块
const addModule = (moduleValue, callback) => {
  post('/sysModuleTreeList', moduleValue, callback);
};

//删除功能模块
const deleteModule = (moduleValue, callback) => {
  remove('/sysModuleTreeList', moduleValue, callback);
};

export {
  getModuleTreeList,
  getModuleList,
  getModuleMap,
  updateModuleTreeList,
  addModule,
  deleteModule
}
