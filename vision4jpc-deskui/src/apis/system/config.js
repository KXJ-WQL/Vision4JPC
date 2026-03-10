import {get, put} from '../base-http/http';

//按照配置菜单的ID获取所有配置信息
const getSysConfigVoListByMenuId = (param, callback) => {
  get('/sysConfigVoList', callback, param);
};

//获取所有开启模块的所有配置项
const getAllSysConfigisEnabledVoList = (callback) => {
  get('/sysConfigAllisEnabledVoList', callback);
};

//更新配置项
const updateSysConfigVoList = (moduleValue, callback) => {
  put('/sysConfigVoList', moduleValue, callback);
};

export {
  getSysConfigVoListByMenuId,
  getAllSysConfigisEnabledVoList,
  updateSysConfigVoList
}

