import { get, put, post, remove } from '../base-http/http';

//获取树形路由导航栏菜单(用于动态路由)
const getNavigationTree = (callback) => {
  get("/sysNavigationTreeVoList", callback);
};

//获取树形导航栏菜单列表(用于菜单管理)
const getNavigationList = (callback) => {
  get("/sysNavigationTreeList", callback);
};

//添加导航栏菜单
const addNavigation = (navigationData,callback) => {
  post("/sysNavigation",navigationData, callback);
};

//修改导航栏菜单
const updateNavigation = (navigationData, callback) => {
  put("/sysNavigation",navigationData, callback);

};

//删除导航栏菜单
const deleteNavigation = (navigationData, callback) => {
  remove("/sysNavigation",navigationData, callback);
};

export {
  getNavigationTree,
  getNavigationList,
  addNavigation,
  updateNavigation,
  deleteNavigation
}
