package com.vision4jpc.system.service.impl;

import com.vision4jpc.common.entity.dao.SysModule;
import com.vision4jpc.common.utils.eventbus.EventBusUtil;
import com.vision4jpc.common.utils.tree.TreeUtil;
import com.vision4jpc.system.eventbus.event.ModuleEvent;
import com.vision4jpc.system.mapper.SysModuleMapper;
import com.vision4jpc.system.service.ISysModuleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service.impl
 * @className: SysModuleServiceImpl
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/27 11:37
 * @version: v1.0
 */
@Service
public class SysModuleServiceImpl implements ISysModuleServcie {

    @Autowired
    private SysModuleMapper sysModuleMapper;

    @Override
    public List<SysModule> selectModuleTreeList() {
        // 查询所有功能模块
        List<SysModule> sysModules = sysModuleMapper.selectModuleList();
        //构建树形列表并返回
        return TreeUtil.buildModulesTree(sysModules);
    }

    @Override
    public List<SysModule> selectModuleList() {
        // 查询所有功能模块并返回
        return sysModuleMapper.selectModuleList();
    }

    @Override
    public Map<String, String> selectModuleMap() {
        // 查询所有功能模块
        List<SysModule> sysModules = sysModuleMapper.selectModuleList();

        // 提取key-isEnabled，组装成Map<String, String>
        return sysModules.stream()
                .filter(module -> module.getKey() != null) // 可选：避免key为null
                .collect(Collectors.toMap(SysModule::getKey, SysModule::getIsEnabled));
    }

    @Override
    public List<Map<String, String>> selectModuleMapList() {
        // 查询所有功能模块
        List<SysModule> sysModules = sysModuleMapper.selectModuleList();

        // 提取key-isEnabled，组装成List<Map<String, String>>
        return sysModules.stream()
                .map(module -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(module.getKey(),module.getIsEnabled());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Integer updateModule(SysModule sysModule) {
        //更新数据库中的模块信息
        Integer updateLine = sysModuleMapper.updateModule(sysModule);
        //更新缓存中的模块信息
        EventBusUtil.publishEvent(new ModuleEvent(selectModuleMapList()));
        return updateLine;
    }

    @Override
    public Integer addModule(SysModule sysModule) {
        //新增数据模块信息
        Integer raw = sysModuleMapper.addModule(sysModule);
        //更新缓存中的模块信息
        EventBusUtil.publishEvent(new ModuleEvent(selectModuleMapList()));
        return raw;
    }

    @Override
    public void deleteModule(List<Integer> moduleIdList) {
        //删除数据模块信息
        moduleIdList.forEach(x -> sysModuleMapper.deleteSysModuleById(x));
        //更新缓存中的模块信息
        EventBusUtil.publishEvent(new ModuleEvent(selectModuleMapList()));
    }
}
