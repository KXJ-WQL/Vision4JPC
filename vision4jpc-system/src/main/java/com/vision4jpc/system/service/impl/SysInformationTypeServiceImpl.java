package com.vision4jpc.system.service.impl;

import com.vision4jpc.system.entity.dao.SysInformationType;
import com.vision4jpc.system.mapper.SysInformationTypeMapper;
import com.vision4jpc.system.service.ISysInformationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service.impl
 * @className: SysInformationTypeServiceImpl
 * @author: WQL-KXJ
 * @description: TODO 系统类型业务处理类(预留给开发者模式编写使用)
 * @date: 2024/12/11 17:03
 * @version: v1.0
 */
@Service
public class SysInformationTypeServiceImpl implements ISysInformationTypeService {

    @Autowired
    SysInformationTypeMapper sysInformationTypeMapper;

    @Override
    public List<SysInformationType> getSysInformationTypeList() {
        return sysInformationTypeMapper.selectSysInformationTypeList();
    }

    @Override
    public SysInformationType getSysInformationTypeByStatusCode(Integer statusCode) {
        return sysInformationTypeMapper.selectSysInformationTypeByStatusCode(statusCode);
    }

    @Override
    public int addSysInformationType(SysInformationType sysInformationType) {
        return sysInformationTypeMapper.insertSysInformationType(sysInformationType);
    }

    @Override
    public int updateSysInformationType(SysInformationType sysInformationType) {
        return sysInformationTypeMapper.updateSysInformationType(sysInformationType);
    }

    @Override
    public int deleteSysInformationType(Integer typeId) {
        return sysInformationTypeMapper.deleteSysInformationType(typeId);
    }
}
