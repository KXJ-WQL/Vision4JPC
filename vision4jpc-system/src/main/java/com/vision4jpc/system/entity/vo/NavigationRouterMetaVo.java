package com.vision4jpc.system.entity.vo;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity.vo
 * @className: NavigationRouterMetaVo
 * @author: WQL-KXJ
 * @description: TODO 导航路由的meta的参数Vo对象
 * @date: 2024/8/28 20:40
 * @version: v1.0
 */
public class NavigationRouterMetaVo {

    // 导航路由图标
    private String icon;

    // 导航栏的显示名称(I18国际化的关键字)
    private String title;

    // 导航路由类型(1菜单目录 2菜单选项 3普通页面)
    private String type;

    // 导航路由图标大小(使用Quasar提供的单位)
    private String iconSize;

    // 导航是否需要分割线(1: 需要; 2:不需要)
    private String isSeparator;

    // 导航鼠标触动动画(填写animate.css动画库的动画名)
    private String animation;

    // 缓存组件名称(keeplive通过这个名称进行页面缓存)
    private String cacheComponentName;

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
}
