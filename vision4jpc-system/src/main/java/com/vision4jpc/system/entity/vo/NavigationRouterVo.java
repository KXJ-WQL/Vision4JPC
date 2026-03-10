package com.vision4jpc.system.entity.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity.vo
 * @className: NavigationRouterVo
 * @author: WQL-KXJ
 * @description: TODO 导航路由Vo对象
 * @date: 2024/8/28 20:39
 * @version: v1.0
 */
public class NavigationRouterVo {

    // 导航路由路径
    private String path;

    // 导航路由名称
    private String name;

    // 页面组件路径
    private String component;

    // 路由携带参数信息
    private NavigationRouterMetaVo meta;

    //子路由菜单
    private List<NavigationRouterVo> children = new ArrayList<>();


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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public NavigationRouterMetaVo getMeta() {
        return meta;
    }

    public void setMeta(NavigationRouterMetaVo meta) {
        this.meta = meta;
    }

    public List<NavigationRouterVo> getChildren() {
        return children;
    }

    public void setChildren(List<NavigationRouterVo> children) {
        this.children = children;
    }
}
