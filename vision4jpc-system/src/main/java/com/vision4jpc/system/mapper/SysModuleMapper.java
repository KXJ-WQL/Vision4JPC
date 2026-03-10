package com.vision4jpc.system.mapper;


import com.vision4jpc.common.entity.dao.SysModule;
import com.vision4jpc.common.entity.dao.SysNavigation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.mapper
 * @className: SysModuleMapper
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/8/25 14:14
 * @version: v1.0
 */
public interface SysModuleMapper {

    //查询所有功能模块
    public List<SysModule> selectModuleList();

    //更新功能模块
    public Integer updateModule(SysModule sysModule);

    //插入功能模块
    public Integer addModule(SysModule SysModule);

    //删除功能模块
    public Integer deleteSysModuleById(@Param("moduleId") Integer moduleId);
}
