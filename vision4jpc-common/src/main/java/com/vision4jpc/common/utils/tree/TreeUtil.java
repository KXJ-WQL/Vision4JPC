package com.vision4jpc.common.utils.tree;
import com.vision4jpc.common.entity.dao.SysModule;
import com.vision4jpc.common.entity.dao.SysNavigation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: Vision4JPC
 * @package: com.vision4jpc.common.utils.tree
 * @className: TreeUtil
 * @author: WQL-KXJ
 * @description: TODO 构建树形结构列表
 * @date: 2024/8/27 14:35
 * @version: v1.0
 */
public class TreeUtil {

    /**
     * 构建功能模块树形结构：使用for和if构建
     *
     * @param modules 所有模块列表
     * @return 树形结构的模块列表
     */
    public static List<SysModule> buildModulesTree(List<SysModule> modules) {
        // 结果树
        List<SysModule> tree = new ArrayList<>();
        // 模块映射，以 moduleId 为键
        Map<Long, SysModule> moduleMap = new HashMap<>();

        // 初始化模块映射
        for (SysModule module : modules) {
            moduleMap.put(module.getModuleId(), module);
        }

        // 构建父子关系
        for (SysModule module : modules) {
            // 如果没有父模块，说明是根模块，加入树中
            if (module.getParentId() == 0) {
                tree.add(module);
            } else {
                // 查找父模块
                SysModule parent = moduleMap.get(module.getParentId());
                if (parent != null) {
                    // 父模块存在，将当前模块加入父模块的子模块列表中
                    parent.getChildrenModule().add(module);
                }
            }
        }
        return tree;
    }


    /**
     * 构建导航栏路由菜单树形结构：使用Stream流构建
     *
     * @param allNavigations 经过过滤后的所有导航栏列表
     * @return 树形结构的导航栏路由列表
     */
    public static List<SysNavigation> buildNavigationTree(List<SysNavigation> allNavigations) {

        // 创建一个 Map 来根据 id 查找导航，方便后续构建树形结构
        Map<Long, SysNavigation> navigationMap = allNavigations.stream()
                .collect(Collectors.toMap(SysNavigation::getNavigationId, nav -> nav));

        // 创建根节点列表
        List<SysNavigation> rootNodes = allNavigations.stream()
                .filter(nav -> nav.getParentId() == null || nav.getParentId() == 0)
                .collect(Collectors.toList());

        // 遍历所有导航，构建树形结构
        allNavigations.forEach(nav -> {
            // 如果当前导航项有父项
            if (nav.getParentId() != null && nav.getParentId() != 0) {
                // 查找当前导航项的父项
                SysNavigation parent = navigationMap.get(nav.getParentId());
                // 如果父项存在，将当前导航项添加到父项的子节点列表中
                if (parent != null) {
                    parent.getChildren().add(nav);
                }
            }
        });
        // 对菜单路由列表和每个父路由菜单的子节点列表进行排序
        sortNavigationTree(rootNodes);
        //返回
        return rootNodes;
    }

    //进行菜单路由进行递归排序
    private static void sortNavigationTree(List<SysNavigation> nodes) {
        // 先对当前路由菜单节点列表进行排序
        nodes.sort(Comparator.comparingInt(SysNavigation::getOrder));

        // 递归对子路由菜单节点进行排序
        for (SysNavigation node : nodes) {
            if (!node.getChildren().isEmpty()) {
                sortNavigationTree(node.getChildren());
            }
        }
    }
}
