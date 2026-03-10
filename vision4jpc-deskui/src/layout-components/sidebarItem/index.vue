<!-- 主页右侧功能栏 -->
<template>
  <q-item
    clickable
    v-ripple
    @mouseover="showAnimation"
    @mouseleave="hideAnimation"
    @click="handleClick"
  >
    <!--图标-->
    <q-icon
      :class="getIconClasses(iconAnimation, isHovered)"
      size="sm"
      :name="iconName"
      class="q-pa-xs"
    ></q-icon>
    <!--提示文字-->
    <q-tooltip anchor="center left" self="center right" :offset="[10, 10]">
      <strong>{{ $t(tipToolTitle) }}</strong>
    </q-tooltip>
    <!--徽章提示-->
    <q-badge
      v-if="hasBadge"
      :class="getIconClasses(iconAnimation, isHovered)"
      rounded
      color="red"
      class="q-mt-sm q-mr-sm"
      floating
      transparent
      >{{ badgeCount }}</q-badge
    >
  </q-item>
  <q-separator />
</template>

<script setup>
//ref响应式对象和生命周期对象
import { ref, onMounted, onBeforeUnmount } from "vue";
//获取inject数据对象接收器
import { inject } from "vue";
//导入悬浮动画工具类
import { useHoverAnimation, getIconClasses } from "utils/floatingAnimation";

defineOptions({
  name: "SidebarItem",
});

//接收props(限制类型)
const props = defineProps({
  //ID编号
  configMenuId:{
    type: Number,
    required: true,
  },
  //图标名称
  iconName: {
    type: String,
    required: true,
  },
  //提示文本
  tipToolTitle: {
    type: String,
    required: true,
  },
  //icon动画效果
  iconAnimation: {
    type: String,
    //默认rubberBand动画
    default: "rubberBand",
  },
  //是否启用徽章提示功能
  isBadgeInfo: {
    type: String,
    default: '',
  },
  //动态组件名
  configComponent: {
    type: String,
    default: '',
  }
});

// 使用工具函数来管理悬浮动画状态
const { isHovered, showAnimation, hideAnimation } = useHoverAnimation();

//获取bus全局事件总线
const bus = inject("bus");

//获取自定义事件对象
const emit = defineEmits(["handleClick"]);

//是否显示Badge徽章标记(但没有通知值的时候也不显示)
const hasBadge = ref( false && props.isBadgeInfo=='1');

//徽章标记内容
const badgeCount = ref(0);

//item点击事件方法
const handleClick = () => {
  //调用父组件方法(根据clickName区分不同的回调方法)
  emit("handleClick", props);
};

// 更新徽章标记的值
const updateBadge = (count) => {
  badgeCount.value = count;
  //通知值大于0且开启徽章显示通知
  hasBadge.value = Boolean(count) && props.isBadgeInfo=='1';
};

// 如果启用了徽章提示功能，监听全局事件
if (hasBadge.value) {

  //组件挂载完毕触发(Mounted生命周期)
  onMounted(() => {
    // 监听全局事件以更新 badge的值和状态
    bus.on(`update-badge-${props.iconName}`, updateBadge);
  });

  //组件被卸载之前触发(BeforeUnmount生命周期)
  onBeforeUnmount(() => {
    // 解绑全局事件
    bus.off(`update-badge-${props.iconName}`, updateBadge);
  });
}
</script>
