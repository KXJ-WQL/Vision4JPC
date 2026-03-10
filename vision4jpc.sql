/*
 Navicat Premium Data Transfer

 Source Server         : WQL-KXJ
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : vision4jpc

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 09/03/2026 22:01:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置项ID',
  `key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '配置项的键名(设置唯一键)',
  `value` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '配置项的值',
  `description` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '配置项的描述',
  `config_menu_id` bigint(20) NULL DEFAULT NULL COMMENT '配置项菜单的ID',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_id`) USING BTREE,
  UNIQUE INDEX `unique_key`(`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统配置项表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_menu`;
CREATE TABLE `sys_config_menu`  (
  `config_menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置项菜单ID',
  `tool_title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '配置菜单名称(I18国际化的关键字)',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单图标',
  `animation` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '鼠标触动icon动画(填写animate.css动画库的动画名)',
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单组件路径',
  `order` int(10) NULL DEFAULT NULL COMMENT '菜单显示排列顺序',
  `is_badgeinfo` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否使用徽章提示(1:使用 2:不使用)',
  `module_id` bigint(20) NULL DEFAULT NULL COMMENT '系统功能模块ID',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`config_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统配置菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config_menu
-- ----------------------------
INSERT INTO `sys_config_menu` VALUES (1, 'systemInformation', 'mail', 'rubberBand', 'system/systemInformation/index', 1, '1', 1, '2024-09-01 14:33:00', NULL);

-- ----------------------------
-- Table structure for sys_information
-- ----------------------------
DROP TABLE IF EXISTS `sys_information`;
CREATE TABLE `sys_information`  (
  `information_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '异常消息ID',
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常消息内容',
  `type_status_code` int(20) NULL DEFAULT NULL COMMENT '异常信息状态码',
  `if_read_already` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '异常信息是否已读(1.未读 2.已读)',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`information_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统异常信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_information
-- ----------------------------
INSERT INTO `sys_information` VALUES (1, '采集器断开连接', 201, '1', '2025-02-28 15:37:12', NULL);

-- ----------------------------
-- Table structure for sys_information_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_information_type`;
CREATE TABLE `sys_information_type`  (
  `type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统异常信息类型ID',
  `status_code` int(20) NULL DEFAULT NULL COMMENT '系统异常信息类型状态码',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统异常信息类型名称',
  `solution` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统异常的解决办法',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`type_id`) USING BTREE,
  UNIQUE INDEX `unique_key`(`status_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统异常信息类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_information_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module`  (
  `module_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目功能模块ID',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能模块名称',
  `key` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能模块唯一key',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能模块图标(使用fonts.google.com图标)',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '功能模块描述',
  `is_enabled` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '是否开启该功能模块(1开启,2关闭)',
  `is_alter` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户是否可开启关闭此功能模块(1允许,2禁止)',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父功能模块ID(0表示无父模块)',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统功能表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES (1, '开发者模块', 'SystemCoreModule', 'account_circle', '模块管理/菜单管理/配置管理', '1', '1', 0, '2025-06-19 15:05:11', '2026-03-09 12:04:38');

-- ----------------------------
-- Table structure for sys_navigation
-- ----------------------------
DROP TABLE IF EXISTS `sys_navigation`;
CREATE TABLE `sys_navigation`  (
  `navigation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统导航栏主键',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航路由路径',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航路由名称',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航路由类型(1菜单 2普通页面)',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父导航路由的ID',
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面组件路径',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航路由图标',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航栏的显示名称(I18国际化的关键字)',
  `icon_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航路由图标大小(使用Quasar提供的单位)',
  `is_separator` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航是否需要分割线(1: 需要; 2:不需要)',
  `animation` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '导航鼠标触动动画(填写animate.css动画库的动画名)',
  `cache_component_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '缓存组件名称(keeplive通过这个名称就行页面缓存)',
  `order` int(10) NULL DEFAULT NULL COMMENT '导航栏排列顺序',
  `module_id` bigint(20) NULL DEFAULT NULL COMMENT '系统功能模块ID',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`navigation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系统导航栏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_navigation
-- ----------------------------
INSERT INTO `sys_navigation` VALUES (1, '/developer', 'Developer', '1', 0, NULL, 'account_circle', 'developer', 'sm', '2', 'swing', NULL, 1, 1, '2024-08-28 17:32:16', '2026-03-08 21:24:00');
INSERT INTO `sys_navigation` VALUES (2, '/developer/navigationMenu', 'NavigationMenu', '1', 1, 'developer/navigationMenu/index', 'token', 'navigationMenu', 'sm', '1', 'jello', 'NavigationMenu', 2, 1, '2024-08-28 17:32:16', '2026-03-08 21:38:59');
INSERT INTO `sys_navigation` VALUES (25, '/developer/functionalModule', 'FunctionalModule', '1', 1, 'developer/functionalModule/index', 'webhook', 'functionalModule', 'sm', '1', 'jello', 'FunctionalModule', 1, 1, '2026-03-08 21:36:28', '2026-03-08 21:38:33');
INSERT INTO `sys_navigation` VALUES (26, '/developer/configMenu', 'ConfigMenu', '1', 1, 'developer/configMenu/index', 'control_camera', 'configMenu', 'sm', '1', 'jello', 'ConfigMenu', 3, 1, '2026-03-09 12:02:06', NULL);

SET FOREIGN_KEY_CHECKS = 1;
