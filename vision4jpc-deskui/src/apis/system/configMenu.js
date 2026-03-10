import { get, post, put, remove } from '../base-http/http';

//获取系统配置菜单VO列表
const getSysConfigMenuVoList = (callback) => {
  get('/sysConfigMenuVoList', callback);
};

//获取系统配置菜单列表
const getSysConfigMenuList = (callback) => {
  get('/sysConfigMenu', callback);
};

//添加系统配置菜单
const addSysConfigMenu = (configMenuData,callback) => {
  post('/sysConfigMenu', configMenuData, callback);
};

//更新系统配置菜单
const updateSysConfigMenu = (configMenuData,callback) => {
  put('/sysConfigMenu', configMenuData, callback);
};

//删除系统配置菜单
const deleteSysConfigMenu = (configMenuData,callback) => {
  remove('/sysConfigMenu', configMenuData, callback);
};


export {
  getSysConfigMenuVoList,
  getSysConfigMenuList,
  addSysConfigMenu,
  updateSysConfigMenu,
  deleteSysConfigMenu
}
