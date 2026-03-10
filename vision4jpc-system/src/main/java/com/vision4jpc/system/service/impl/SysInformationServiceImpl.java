package com.vision4jpc.system.service.impl;

import com.vision4jpc.system.entity.dao.SysInformation;
import com.vision4jpc.system.entity.vo.SysInformationVo;
import com.vision4jpc.system.mapper.SysInformationMapper;
import com.vision4jpc.system.service.ISysInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service.impl
 * @className: SysInformationServiceImpl
 * @author: WQL-KXJ
 * @description: TODO 系统信息业务处理接口实现类
 * @date: 2024/12/11 17:17
 * @version: v1.0
 */
@Service
public class SysInformationServiceImpl implements ISysInformationService {

    @Autowired
    SysInformationMapper sysInformationMapper;

    @Override
    public List<SysInformationVo> getAllSysInformation() {
        return sysInformationMapper.selectAllSysInformation();
    }

    @Override
    public SysInformationVo getSysInformationByID(Integer informationId) {
        return sysInformationMapper.selectSysInformationByID(informationId);
    }

    @Override
    public Integer updateAllToRead() {
        return sysInformationMapper.updateAllToRead();
    }

    @Override
    public Integer addSysInformation(SysInformation sysInformation) {
        return sysInformationMapper.insertSysInformation(sysInformation);
    }

    @Override
    public Integer updateSysInformation(SysInformation sysInformation) {
        return sysInformationMapper.updateSysInformation(sysInformation);
    }

    @Override
    public Integer deleteSysInformation(List<Long> informationId) {
        return informationId.stream()
                .mapToInt(sysInformationMapper::deleteSysInformation) //将每次操作的影响函数结果映射为 int
                .sum(); //影响行数累加
    }
}
