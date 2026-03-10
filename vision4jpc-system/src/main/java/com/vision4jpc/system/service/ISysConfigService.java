package com.vision4jpc.system.service;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service
 * @className: ISysConfigService
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/4 14:33
 * @version: v1.0
 */
public interface ISysConfigService {

    // 获取所有的配置信息列表
    Map<String, String> selectAllSysConfigList();

    //获取指定configMenuID配置菜单的所有配置项
    Map<String, String> selectSysConfigVoListByConfigMenuId(Long configMenuID);

    //获取所有菜单所有所有模块开启的系统配置项
    Map<String, String> selectAllMouldIsEnabledSysConfigList();

    //批量更新配置项
    void updateSysConfig(Map<String,String> keyValueMap);

}
