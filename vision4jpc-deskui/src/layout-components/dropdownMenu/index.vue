<!-- 顶部设置功能设置下拉栏内容 -->
<template>
  <!--为了增强模块的复用性，没有子下拉菜单列表触发@click，否则触发插槽的子下拉列表-->
  <q-item clickable @click="handleMenu">

    <!--菜单选项图标-->
    <q-icon class="q-ma-xs" :name="menuOptionsIcon"  size="xs"/>

    <q-item-section>
      <!--菜单选项名称-->
      {{ menuOptionsTitle }}
    </q-item-section>
    <q-item-section side>
      <!--图标插槽-->
      <slot name="menuIcon">
        <q-icon size="xs" name="keyboard_arrow_right" />
      </slot>
    </q-item-section>
    <q-menu anchor="top end" self="top start">
      <!--列表插槽-->
      <slot name="submenuList"> </slot>
    </q-menu>
  </q-item>
</template>

<script setup>
defineOptions({
  name: "DropdownMenu",
});

//接收父组件数据(限制类型)
defineProps({
  //菜单名称
  menuOptionsTitle: {
    type: String,
    required: true,
  },

  //菜单图标
  menuOptionsIcon: {
    type: String,
    required: true,
  }
});

//获取点击事件：onMenu --> 普通菜单事件，
const onDropMenu = defineEmits(["onMenu"]);

//普通菜单点击事件方法
const handleMenu = () => {
  //触发自定义事件
  onDropMenu("onMenu");
};
</script>
