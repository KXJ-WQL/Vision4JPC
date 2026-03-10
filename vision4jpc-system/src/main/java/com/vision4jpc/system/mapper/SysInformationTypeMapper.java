package com.vision4jpc.system.mapper;

import com.vision4jpc.system.entity.dao.SysInformationType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.mapper
 * @className: SysInformationTypeMapper
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/11/14 16:14
 * @version: v1.0
 */
public interface SysInformationTypeMapper {

    // 查询所有系统异常信息类型
    public List<SysInformationType> selectSysInformationTypeList();

    // 根据 status_code 查询系统异常信息类型
    public SysInformationType selectSysInformationTypeByStatusCode(@Param("statusCode") Integer statusCode);

    // 插入新的系统异常信息类型
    public int insertSysInformationType(SysInformationType sysInformationType);

    // 更新现有的系统异常信息类型
    public int updateSysInformationType(SysInformationType sysInformationType);

    // 删除指定的系统异常信息类型
    public int deleteSysInformationType(@Param("typeId") Integer typeId);
}
