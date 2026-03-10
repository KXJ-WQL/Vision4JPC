package com.vision4jpc.system.mapper;

import com.vision4jpc.system.entity.dao.SysConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.mapper
 * @className: SysConfigMapper
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/3 20:48
 * @version: v1.0
 */
public interface SysConfigMapper {

    //获取所有的配置列表
    List<SysConfig> selectAllSysConfigList();

    //根据配置菜单的ID查询配置列表
    List<SysConfig> selectSysConfigListByConfigMenuId(Long configMenuId);

    //根据key更新value配置项的值
    Integer updateSysConfig(@Param("key") String key,@Param("value") String value);

    //根据菜单查询所有所有模块开启的系统配置项(关联查询)
    List<SysConfig> selectSysConfigListByMouldIsEnabled();
}
