package com.vision4jpc.system.entity.dao;


import com.vision4jpc.common.entity.dao.base.BaseDao;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity.dao
 * @className: SysInformationType
 * @author: WQL-KXJ
 * @description: TODO 系统异常信息类型表
 * @date: 2024/11/14 15:32
 * @version: v1.0
 */
public class SysInformationType extends BaseDao {

    // 系统异常信息类型ID
    private Long typeId;

    // 系统异常信息类型状态码
    private Integer statusCode;

    // 系统异常信息类型名称
    private String name;

    // 系统异常的解决办法
    private String solution;

    public SysInformationType() {}

    public SysInformationType(Long typeId, Integer statusCode, String name, String solution) {
        this.typeId = typeId;
        this.statusCode = statusCode;
        this.name = name;
        this.solution = solution;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
