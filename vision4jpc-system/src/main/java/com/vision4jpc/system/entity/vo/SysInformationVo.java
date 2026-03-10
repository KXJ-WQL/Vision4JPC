package com.vision4jpc.system.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity.vo
 * @className: SysInformationVo
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/12/11 15:58
 * @version: v1.0
 */
public class SysInformationVo {

    // 系统异常信息ID主键
    private Long informationId;

    // 系统异常信息内容
    private String content;

    // 系统异常信息是否已读(1.未读 2.已读)
    private String ifReadAlready;

    // 系统异常类型
    private String typeName;

    // 系统异常信息的解决办法
    private String solution;

    // 系统信息的产生时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

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

    public String getIfReadAlready() {
        return ifReadAlready;
    }

    public void setIfReadAlready(String ifReadAlready) {
        this.ifReadAlready = ifReadAlready;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "SysInformationVo{" +
                "informationId=" + informationId +
                ", content='" + content + '\'' +
                ", ifReadAlready='" + ifReadAlready + '\'' +
                ", typeName='" + typeName + '\'' +
                ", solution='" + solution + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
