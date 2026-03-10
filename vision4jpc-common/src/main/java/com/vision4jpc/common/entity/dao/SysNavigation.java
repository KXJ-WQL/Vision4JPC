package com.vision4jpc.common.entity.dao;


import com.vision4jpc.common.entity.dao.base.BaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.entity.dao
 * @className: SysNavigation
 * @author: WQL-KXJ
 * @description: TODO 系统导航栏路由数据对象
 * @date: 2024/8/28 17:41
 * @version: v1.0
 */

public class SysNavigation extends BaseDao {

    // 系统导航栏主键
    private Long navigationId;

    // 导航路由路径
    private String path;

    // 导航路由名称
    private String name;

    // 导航路由类型(1菜单目录 2普通页面 )
    private String type;

    // 父导航路由的ID
    private Long parentId;

    // 页面组件路径
    private String component;

    // 导航路由图标
    private String icon;

    // 导航栏的显示名称(I18国际化的关键字)
    private String title;

    // 导航路由图标大小(使用Quasar提供的单位)
    private String iconSize;

    // 导航是否需要分割线(1: 需要; 2:不需要)
    private String isSeparator;

    // 导航鼠标触动动画(填写animate.css动画库的动画名)
    private String animation;

    // 缓存组件名称(keeplive通过这个名称进行页面缓存)
    private String cacheComponentName;

    // 导航栏排列顺序
    private Integer order;

    // 系统功能模块ID
    private Long moduleId;

    //子路由菜单
    private List<SysNavigation> children = new ArrayList<>();

    public Long getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(Long navigationId) {
        this.navigationId = navigationId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconSize() {
        return iconSize;
    }

    public void setIconSize(String iconSize) {
        this.iconSize = iconSize;
    }

    public String getIsSeparator() {
        return isSeparator;
    }

    public void setIsSeparator(String isSeparator) {
        this.isSeparator = isSeparator;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getCacheComponentName() {
        return cacheComponentName;
    }

    public void setCacheComponentName(String cacheComponentName) {
        this.cacheComponentName = cacheComponentName;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public List<SysNavigation> getChildren() {
        return children;
    }

    public void setChildren(List<SysNavigation> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SysNavigation{" +
                "navigationId=" + navigationId +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", parentId=" + parentId +
                ", component='" + component + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", iconSize='" + iconSize + '\'' +
                ", isSeparator='" + isSeparator + '\'' +
                ", animation='" + animation + '\'' +
                ", cacheComponentName='" + cacheComponentName + '\'' +
                ", order=" + order +
                ", moduleId=" + moduleId +
                ", children=" + children +
                '}';
    }
}
