package com.vision4jpc.system.service;

import com.vision4jpc.system.entity.dao.SysConfigMenu;
import com.vision4jpc.system.entity.vo.SysConfigMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service
 * @className: ISysConfigMenuService
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/1 14:39
 * @version: v1.0
 */
public interface ISysConfigMenuService {

    //查询配置菜单显示Vo列表
    public List<SysConfigMenuVo> selectConfigMenuVoList();

    //查询所有配置菜单显示
    public List<SysConfigMenu> selectConfigMenuList();

    //插入配置菜单
    public Integer addConfigMenu(SysConfigMenu sysConfigMenu);

    //更新配置菜单
    public Integer updateConfigMenu(SysConfigMenu sysConfigMenu);

    //删除配置菜单
    public void deleteConfigMenuById(List<Integer> configMenuIds);

}
