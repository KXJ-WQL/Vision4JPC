package com.vision4jpc.common.entity.dao;
import com.vision4jpc.common.entity.dao.base.BaseDao;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity
 * @className: SysModule
 * @author: WQL-KXJ
 * @description: TODO 系统功能模块数据对象
 * @date: 2024/8/25 13:22
 * @version: v1.0
 */
public class SysModule extends BaseDao {

    // 模块id
    private Long moduleId ;

    // 功能名称
    private String name ;

    // 功能标识唯一Key(主要用于客户端发送使用)
    private String key ;

    // 功能图标
    private String icon ;

    // 功能描述
    private String description ;

    // 是否开启该功能(1开启 2关闭)
    private String isEnabled ;

    // 是否允许用户开启关闭功能模块(1允许 2禁止)
    private String isAlter ;

    // 父功能模块ID
    private Long parentId ;

    // 子功能模块
    private List<SysModule> childrenModule = new ArrayList<>();

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getIsAlter() {
        return isAlter;
    }

    public void setIsAlter(String isAlter) {
        this.isAlter = isAlter;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<SysModule> getChildrenModule() {
        return childrenModule;
    }

    public void setChildrenModule(List<SysModule> childrenModule) {
        this.childrenModule = childrenModule;
    }
}
