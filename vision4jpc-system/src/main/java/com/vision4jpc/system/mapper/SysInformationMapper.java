package com.vision4jpc.system.mapper;

import com.vision4jpc.system.entity.dao.SysInformation;
import com.vision4jpc.system.entity.vo.SysInformationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.mapper
 * @className: SysInformationMapper
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/11/14 16:36
 * @version: v1.0
 */
public interface SysInformationMapper {

    // 查询所有系统异常信息
    public List<SysInformationVo> selectAllSysInformation();

    // 指定ID查询系统异常信息
    public SysInformationVo selectSysInformationByID(@Param("informationId") Integer informationId);

    // 全部已读
    public Integer updateAllToRead();

    // 插入系统异常信息
    public Integer insertSysInformation(SysInformation sysInformation);

    // 更新系统异常信息
    public Integer updateSysInformation(SysInformation sysInformation);

    // 删除系统异常信息
    public Integer deleteSysInformation(@Param("informationId") Long informationId);
}
