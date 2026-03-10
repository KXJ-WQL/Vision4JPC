package com.vision4jpc.system.service.impl;

import com.vision4jpc.common.utils.eventbus.EventBusUtil;
import com.vision4jpc.system.converter.SysConfigConvert;
import com.vision4jpc.system.eventbus.event.ConfigEvent;
import com.vision4jpc.system.mapper.SysConfigMapper;
import com.vision4jpc.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service.impl
 * @className: SysConfigServiceImpl
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/4 14:33
 * @version: v1.0
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    @Autowired
    SysConfigMapper sysConfigMapper;

    @Autowired
    SysConfigConvert sysConfigConvert;


    @Override
    public Map<String, String> selectAllSysConfigList() {
        //将查询到的所有List<SysConfig>中key-value转化为map结构并返回
        return sysConfigConvert.sysConfigsToMaps(sysConfigMapper.selectAllSysConfigList());
    }

    @Override
    public Map<String, String> selectSysConfigVoListByConfigMenuId(Long configMenuID) {
        //将查询的List<SysConfig>中key-value转化为map结构并返回
        return sysConfigConvert.sysConfigsToMaps(sysConfigMapper.selectSysConfigListByConfigMenuId(configMenuID));
    }

    @Override
    public Map<String, String> selectAllMouldIsEnabledSysConfigList() {
        //将已开启的模块的List<SysConfig>结果中的key-value转化为map结构
        return sysConfigConvert.sysConfigsToMaps(sysConfigMapper.selectSysConfigListByMouldIsEnabled());
    }

    @Override
    public void updateSysConfig(Map<String, String> keyValueMap) {
        //遍历keyValueMap进行批量更新
        keyValueMap.forEach((key, value) -> sysConfigMapper.updateSysConfig(key, value));
        //更新缓存中的配置信息
        EventBusUtil.publishEvent(new ConfigEvent(selectAllSysConfigList()));
    }

}
