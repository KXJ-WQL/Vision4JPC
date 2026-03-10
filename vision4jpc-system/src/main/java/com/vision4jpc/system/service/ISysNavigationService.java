package com.vision4jpc.system.service;

import com.vision4jpc.common.entity.dao.SysNavigation;
import com.vision4jpc.system.entity.vo.NavigationRouterVo;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service
 * @className: ISysNavigationService
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/28 17:59
 * @version: v1.0
 */
public interface ISysNavigationService {

    //获取路由导航树形Vo列表
    public List<NavigationRouterVo> selectNavigationTreeVoList();

    //获取路由导航列表
    public List<SysNavigation> selectNavigationTreeList();

    //添加导航菜单
    public Integer addNavigation(SysNavigation sysNavigation);

    //修改导航菜单
    public Integer updateNavigation(SysNavigation sysNavigation);

    //删除导航菜单
    public void deleteNavigationById(List<Integer>  navigationID);

}
