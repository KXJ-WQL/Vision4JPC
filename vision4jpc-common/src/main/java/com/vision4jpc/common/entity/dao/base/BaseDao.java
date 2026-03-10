package com.vision4jpc.common.entity.dao.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.entity.dao.base
 * @className: BaseDao
 * @author: WQL-KXJ
 * @description: TODO 基础DAO类，所以DAO都继承这个基础类
 * @date: 2024/8/25 13:51
 * @version: v1.0
 */
public class BaseDao implements Serializable {

    private static final long serialVersionUID = 1L;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
