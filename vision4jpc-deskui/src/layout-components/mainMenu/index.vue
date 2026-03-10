<!-- 顶部设置功能关于 -->
<template>
  <q-btn
    @click="processMethod"
    size="sm"
    @mouseover="showAnimation"
    @mouseleave="hideAnimation"
  >
    <!--按钮图标-->
    <q-icon
      :class="getIconClasses(iconAnimation, isHovered)"
      :size="iconSize"
      :name="iconName"
    />
    <!--菜单名称-->
    <div>{{ mainMenuName }}</div>
    <!--下拉菜单-->
    <q-menu
      :transition-show="menuShowAnimation"
      :transition-hide="menuHideAnimation"
    >
      <!--下拉菜单列表-->
      <q-list dense>
        <!--下拉菜单列表插槽-->
        <slot name="dropdownMenu"> </slot>
      </q-list>
    </q-menu>
  </q-btn>
</template>

<script setup>
//导入悬浮动画工具类
import { useHoverAnimation, getIconClasses } from "utils/floatingAnimation";

defineOptions({
  name: "MainMenu",
});

//接收父组件数据(限制类型)
const props = defineProps({
  //主菜单名称
  mainMenuName: {
    type: String,
    required: true,
  },
  //主菜单图片名称
  iconName: {
    type: String,
    required: true,
  },
  //主菜单图片图标大小
  iconSize: {
    type: String,
    default: "xs",
  },
  //下拉菜单下拉动画
  menuShowAnimation: {
    type: String,
    default: "jump-down",
  },
  //下拉菜单上拉隐藏动画
  menuHideAnimation: {
    type: String,
    default: "jump-up",
  },
  //鼠标悬浮图标动画名称
  iconAnimation: {
    type: String,
    default: "rotateIn",
  },
});

// 使用工具函数来管理悬浮动画状态
const { isHovered, showAnimation, hideAnimation } = useHoverAnimation();

//获取父组件传输的自定义事件
const mainMenuClick = defineEmits(["menuClick"]);

//主菜单点击事件处理
const processMethod = () => {
  //回调父组件方法(自定义事件)
  mainMenuClick("menuClick");
};
</script>
