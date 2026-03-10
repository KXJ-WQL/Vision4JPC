package com.vision4jpc.system.converter;

import com.vision4jpc.common.entity.dao.SysNavigation;
import com.vision4jpc.system.entity.vo.NavigationRouterVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.system.converter
 * @className: SysNavigationConvert
 * @author: WQL-KXJ
 * @description: TODO SysNavigation转化为NavigationRouterVo的转化方法
 * @date: 2024/8/28 21:45
 * @version: v1.0
 */
@Mapper(componentModel = "spring") //componentModel参数: 通过Spring的依赖注入来创建
public interface SysNavigationConvert {

    // 静态实例，允许使用实例化的方式调用映射器方法(这是Mapstruct的标准写法)
    SysNavigationConvert INSTANCE = Mappers.getMapper(SysNavigationConvert.class);

    /**
     * 映射方法：将SysNavigation对象转换为NavigationRouterVo对象。
     * 其中meta属性需要从SysNavigation的多个字段中获取。
     */
    @Mapping(source = "path", target = "path")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "component", target = "component")
    @Mapping(source = "icon", target = "meta.icon")
    @Mapping(source = "title", target = "meta.title")
    @Mapping(source = "iconSize", target = "meta.iconSize")
    @Mapping(source = "type", target = "meta.type")
    @Mapping(source = "isSeparator", target = "meta.isSeparator")
    @Mapping(source = "animation", target = "meta.animation")
    @Mapping(source = "cacheComponentName", target = "meta.cacheComponentName")
    @Mapping(source = "children", target = "children")
    NavigationRouterVo sysNavigationToNavigationRouterVo(SysNavigation sysNavigation);

    /**
     * 定义接口默认方法(JDK8新特性)：将SysNavigation列表递归地转换为NavigationRouterVo列表。
     * 该方法处理子路由列表（children）的递归转换。
     */
    default List<NavigationRouterVo> sysNavigationsToNavigationRouterVos(List<SysNavigation> sysNavigations) {
        return sysNavigations.stream()
                .map(this::convertWithChildren) // 使用递归转换children子元素
                .collect(Collectors.toList());
    }

    /**
     * 定义接口私有方法(JDK9新特性)：递归转换children子路由列表。
     * 该方法用于处理每个SysNavigation对象的children字段的递归转换。
     */
    private NavigationRouterVo convertWithChildren(SysNavigation sysNavigation) {
        // 映射当前SysNavigation对象为NavigationRouterVo
        NavigationRouterVo vo = sysNavigationToNavigationRouterVo(sysNavigation);

        // 如果当前SysNavigation对象包含子路由，则递归转换这些子路由
        if (sysNavigation.getChildren() != null && !sysNavigation.getChildren().isEmpty()) {
            List<NavigationRouterVo> childrenVos = sysNavigationsToNavigationRouterVos(sysNavigation.getChildren());
            vo.setChildren(childrenVos); // 设置转换后的子路由列表
        }
        return vo;
    }
}