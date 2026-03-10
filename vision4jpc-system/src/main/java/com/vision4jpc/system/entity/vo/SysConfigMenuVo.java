package com.vision4jpc.system.entity.vo;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.entity.vo
 * @className: ConfigMenuVo
 * @author: WQL-KXJ
 * @description: TODO
 * @date: 2024/9/1 14:56
 * @version: v1.0
 */
public class SysConfigMenuVo {

    // 配置菜单id
    private Long configMenuId ;

    // 提示项名称(I18国际化的关键字)
    private String toolTitle ;

    // 菜单图标
    private String icon ;

    // 鼠标触动icon动画(填写animate.css动画库的动画名)
    private String animation ;

    // 是否使用徽章提示(1:使用 2:不使用)
    private String isBadgeinfo ;

    // 菜单组件路由
    private String component;

    public Long getConfigMenuId() {
        return configMenuId;
    }

    public void setConfigMenuId(Long configMenuId) {
        this.configMenuId = configMenuId;
    }

    public String getToolTitle() {
        return toolTitle;
    }

    public void setToolTitle(String toolTitle) {
        this.toolTitle = toolTitle;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIsBadgeinfo() {
        return isBadgeinfo;
    }

    public void setIsBadgeinfo(String isBadgeinfo) {
        this.isBadgeinfo = isBadgeinfo;
    }
}
