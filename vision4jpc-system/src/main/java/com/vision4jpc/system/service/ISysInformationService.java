package com.vision4jpc.system.service;

import com.vision4jpc.system.entity.dao.SysInformation;
import com.vision4jpc.system.entity.vo.SysInformationVo;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.service
 * @className: ISysInformationService
 * @author: WQL-KXJ
 * @description: TODO 系统信息业务处理接口类
 * @date: 2024/12/11 17:14
 * @version: v1.0
 */
public interface ISysInformationService {

    // 查询所有系统异常信息
    public List<SysInformationVo> getAllSysInformation();

    // 指定ID查询系统异常信息
    public SysInformationVo getSysInformationByID(Integer informationId);

    // 全部已读
    public Integer updateAllToRead();

    // 添加系统异常信息
    public Integer addSysInformation(SysInformation sysInformation);

    // 修改系统异常信息
    public Integer updateSysInformation(SysInformation sysInformation);

    // 删除系统异常信息
    public Integer deleteSysInformation(List<Long> informationId);
}
