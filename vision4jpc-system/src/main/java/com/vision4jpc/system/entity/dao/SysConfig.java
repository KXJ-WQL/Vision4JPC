package com.vision4jpc.system.entity.dao;


import com.vision4jpc.common.entity.dao.base.BaseDao;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.entity.dao
 * @className: SysConfig
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/3 20:36
 * @version: v1.0
 */

public class SysConfig extends BaseDao {

    // 配置项ID
    private Long configId;

    // 配置项的键名(设置唯一键)
    private String key;

    // 配置项的值
    private String value;

    // 配置项的描述
    private String description;

    // 配置项菜单的ID
    private Long configMenuId;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getConfigMenuId() {
        return configMenuId;
    }

    public void setConfigMenuId(Long configMenuId) {
        this.configMenuId = configMenuId;
    }
}
