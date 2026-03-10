package com.vision4jpc.system.mapper;
import com.vision4jpc.common.entity.dao.SysNavigation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.mapper
 * @className: SysNavigationMapper
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/28 17:47
 * @version: v1.0
 */
public interface SysNavigationMapper {

    //根据模块状态查询所有导航栏数据
    public List<SysNavigation> selectNavigationVoList();

    //查询所有导航栏数据
    public List<SysNavigation> selectNavigationList();

    //插入导航栏数据
    public Integer addNavigation(SysNavigation sysNavigation);

    //更新导航栏数据
    public Integer updateNavigation(SysNavigation sysNavigation);

    //删除导航栏数据
    public Integer deleteSysNavigationById(@Param("navigationId") Integer navigationId);

}
