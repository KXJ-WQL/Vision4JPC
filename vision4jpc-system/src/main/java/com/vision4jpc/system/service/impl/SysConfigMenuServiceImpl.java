package com.vision4jpc.system.service.impl;

import com.vision4jpc.system.converter.SysConfigMenuConvert;
import com.vision4jpc.system.entity.dao.SysConfigMenu;
import com.vision4jpc.system.entity.vo.SysConfigMenuVo;
import com.vision4jpc.system.mapper.SysConfigMenuMapper;
import com.vision4jpc.system.service.ISysConfigMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service.impl
 * @className: SysConfigMenuServiceImpl
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/1 14:40
 * @version: v1.0
 */
@Service
public class SysConfigMenuServiceImpl implements ISysConfigMenuService {

    @Autowired
    SysConfigMenuMapper sysConfigMenuMapper;

    @Autowired
    SysConfigMenuConvert sysConfigMenuConvert;

    @Override
    public List<SysConfigMenuVo> selectConfigMenuVoList() {
        //查询配置菜单列表并转化Vo数据格式返回
        return sysConfigMenuConvert.sysConfigMenuToConfigMenuVos(sysConfigMenuMapper.selectConfigMenuVoList());
    }

    @Override
    public List<SysConfigMenu> selectConfigMenuList() {
        return sysConfigMenuMapper.selectConfigMenuList();
    }

    @Override
    public Integer addConfigMenu(SysConfigMenu sysConfigMenu) {
        return sysConfigMenuMapper.addConfigMenu(sysConfigMenu);
    }

    @Override
    public Integer updateConfigMenu(SysConfigMenu sysConfigMenu) {
        return sysConfigMenuMapper.updateConfigMenu(sysConfigMenu);
    }

    @Override
    public void deleteConfigMenuById(List<Integer> configMenuIds) {
        configMenuIds.forEach(configMenuId -> sysConfigMenuMapper.deleteConfigMenuById(configMenuId));
    }
}
