package com.vision4jpc.system.entity.dao;


import com.vision4jpc.common.entity.dao.base.BaseDao;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity.dao
 * @className: SysInformation
 * @author: WQL-KXJ
 * @description: TODO 系统异常信息表
 * @date: 2024/11/14 15:24
 * @version: v1.0
 */
public class SysInformation extends BaseDao {

    // 系统异常信息ID主键
    private Long informationId;

    // 系统异常信息内容
    private String content;

    // 系统异常信息状态码
    private Integer typeStatusCode;

    // 系统异常信息是否已读(1.未读 2.已读)
    private String ifReadAlready;

    public SysInformation() {}

    public SysInformation(Long informationId, String content, Integer typeStatusCode, String ifReadAlready) {
        this.informationId = informationId;
        this.content = content;
        this.typeStatusCode = typeStatusCode;
        this.ifReadAlready = ifReadAlready;
    }

    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTypeStatusCode() {
        return typeStatusCode;
    }

    public void setTypeStatusCode(Integer typeStatusCode) {
        this.typeStatusCode = typeStatusCode;
    }

    public String getIfReadAlready() {
        return ifReadAlready;
    }

    public void setIfReadAlready(String ifReadAlready) {
        this.ifReadAlready = ifReadAlready;
    }
}
