package com.vision4jpc.system.mapper;

import com.vision4jpc.common.entity.dao.SysModule;
import com.vision4jpc.system.entity.dao.SysConfigMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.mapper
 * @className: SysConfigMenuMapper
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/1 14:10
 * @version: v1.0
 */
public interface SysConfigMenuMapper {

    //根据模块状态查询配置菜单显示列表
    public List<SysConfigMenu> selectConfigMenuVoList();

    //查询所有配置菜单显示
    public List<SysConfigMenu> selectConfigMenuList();

    //插入配置菜单
    public Integer addConfigMenu(SysConfigMenu sysConfigMenu);

    //更新配置菜单
    public Integer updateConfigMenu(SysConfigMenu sysConfigMenu);

    //删除配置菜单
    public Integer deleteConfigMenuById(@Param("configMenuId") Integer configMenuId);

}
