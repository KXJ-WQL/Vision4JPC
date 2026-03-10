package com.vision4jpc.system.service;

import com.vision4jpc.system.entity.dao.SysInformationType;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service
 * @className: ISysInformationTypeService
 * @author: WQL-KXJ
 * @description: TODO 系统信息类业务处理类(预留给开发者模式编写使用)
 * @date: 2024/12/11 16:54
 * @version: v1.0
 */
public interface ISysInformationTypeService {

    // 查询所有系统异常系统类型
    public List<SysInformationType> getSysInformationTypeList();

    // 根据 status_code 查询系统异常信息类型
    public SysInformationType getSysInformationTypeByStatusCode(Integer statusCode);

    // 添加新的系统异常信息类型到数据库
    public int addSysInformationType(SysInformationType sysInformationType);

    // 更新现有的系统异常信息类型信息
    public int updateSysInformationType(SysInformationType sysInformationType);

    // 删除指定的系统异常信息类型
    public int deleteSysInformationType(Integer typeId);

}
