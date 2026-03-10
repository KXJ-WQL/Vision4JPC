package com.vision4jpc.system.service.impl;
import com.vision4jpc.common.entity.dao.SysNavigation;
import com.vision4jpc.common.utils.NullUtils;
import com.vision4jpc.common.utils.tree.TreeUtil;
import com.vision4jpc.system.converter.SysNavigationConvert;
import com.vision4jpc.system.entity.vo.NavigationRouterVo;
import com.vision4jpc.system.mapper.SysNavigationMapper;
import com.vision4jpc.system.service.ISysNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service.impl
 * @className: SysNavigationServiceImpl
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/28 17:59
 * @version: v1.0
 */
@Service
public class SysNavigationServiceImpl implements ISysNavigationService {

    @Autowired
    SysNavigationMapper sysNavigationMapper;

    @Autowired
    SysNavigationConvert sysNavigationConvert;

    @Override
    public List<NavigationRouterVo> selectNavigationTreeVoList() {
        //获取系统导航栏路由列表信息
        List<SysNavigation> sysNavigations = sysNavigationMapper.selectNavigationVoList();
        //将导航栏路由列表转化为树形结构
        List<SysNavigation> sysTreeNavigations = TreeUtil.buildNavigationTree(sysNavigations);
        //将导航栏路由树形列表转化为前端Router匹配的Vo结构并返回
        return sysNavigationConvert.sysNavigationsToNavigationRouterVos(sysTreeNavigations);
    }

    @Override
    public List<SysNavigation> selectNavigationTreeList() {
        //获取系统导航栏路由列表信息
        List<SysNavigation> sysNavigations = sysNavigationMapper.selectNavigationList();
        //将导航栏路由列表转化为树形结构
        return TreeUtil.buildNavigationTree(sysNavigations);
    }

    @Override
    public Integer addNavigation(SysNavigation sysNavigation) {
        // 手动处理path
        sysNavigation.setPath(getPath(sysNavigation));
        return sysNavigationMapper.addNavigation(sysNavigation);
    }

    @Override
    public Integer updateNavigation(SysNavigation sysNavigation) {
        return sysNavigationMapper.updateNavigation(sysNavigation);
    }

    @Override
    public void deleteNavigationById(List<Integer> navigationIdList) {
        navigationIdList.forEach(x -> sysNavigationMapper.deleteSysNavigationById(x));
    }

    private String getPath(SysNavigation sysNavigation){
        // 组件路径不为null，说明是二级菜单
        if (NullUtils.isNotNull(sysNavigation.getComponent())) return "/" + sysNavigation.getComponent().replaceAll("/index$", "");
        // 顶级菜单，直接使用路由名称为path
        return "/" + sysNavigation.getName().toLowerCase();
    }
}
