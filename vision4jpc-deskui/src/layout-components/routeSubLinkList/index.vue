<!-- 左侧2级目录 -->
<template>
  <q-item
    clickable
    :active="isActive"
    @click="handleClick"
    @mouseover="showAnimation"
    @mouseleave="hideAnimation"
    active-class="bg-positive text-primary text-overline"
    class = "items-center text-overline"
    v-ripple
  >
    <q-icon
      :class="getIconClasses(menuRoute.meta.animation, isHovered)"
      :name="menuRoute.meta.icon"
      :size="menuRoute.meta.iconSize"
    />
    &nbsp;
    <span :class="getIconClasses(menuRoute.meta.animation, isHovered)"> {{ $t(menuRoute.meta.title) }} </span>
  </q-item>
</template>

<script setup>
  import { toRefs, computed, onMounted } from "vue";

  // 导入用于悬浮动画效果的工具函数
  import { useHoverAnimation, getIconClasses } from "utils/floatingAnimation";

  defineOptions({
    name: "RouteLinkList",
  });

  const props = defineProps({
    // 路由信息对象，包含菜单项的元数据
    menuRoute: {
      type: Object,
      required: true,
    },
    // 当前激活的菜单项标签
    activeLabel: {
      type: String,
      required: true,
    },
  });

  // 将 props 中的 menuRoute 转换为响应式引用
  const { menuRoute } = toRefs(props);

  // 使用工具函数来管理悬浮动画的状态
  const { isHovered, showAnimation, hideAnimation } = useHoverAnimation();

  // 定义自定义事件，用于更新激活的导航栏标签
  const emits = defineEmits(["update:activeLabel"]);

  // 计算属性，判断当前导航栏是否为激活状态
  const isActive = computed(
    () => props.menuRoute.name === props.activeLabel
  );

  // 处理点击事件，更新激活的导航栏标签
  const handleClick = () => {

    // 当点击并非当前导航栏标签触发自定义事件，传递当前导航栏的路由信息
    emits("update:activeLabel", props.menuRoute)
  };
</script>
