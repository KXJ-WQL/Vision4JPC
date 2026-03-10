package com.vision4jpc.system.service;
import com.vision4jpc.common.entity.dao.SysModule;

import java.util.List;
import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service
 * @className: ISysModuleServcie
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/27 11:35
 * @version: v1.0
 */
public interface ISysModuleServcie {

    //获取功能模块树形列表
    public List<SysModule> selectModuleTreeList();

    //获取功能模块普通列表
    public List<SysModule> selectModuleList();

    //获取所有模块列表，只要需要Key-isEnabled信息
    public Map<String,String> selectModuleMap();

    //获取所有模块列表，只要需要Key-isEnabled信息
    public List<Map<String,String>> selectModuleMapList();

    //更新功能模块状态
    public Integer updateModule(SysModule sysModule);

    //添加功能模块
    public Integer addModule(SysModule sysModule);

    //删除功能模块
    public void deleteModule(List<Integer> moduleIdList);
}
