import { ref } from "vue";

// 管理悬浮动画状态的 hook
const useHoverAnimation = () => {
  const isHovered = ref(false);

  // 显示图标和文字动画
  const showAnimation = () => {
    isHovered.value = true;
  };

  // 隐藏图标和文字动画
  const hideAnimation = () => {
    isHovered.value = false;
  };

  return {
    isHovered,
    showAnimation,
    hideAnimation,
  };
};

// 获取图标类名的方法
const getIconClasses = (animationType, isAnimation) => ({
  animated: isAnimation,
  [`${animationType}`]: isAnimation,
});

export { useHoverAnimation, getIconClasses };
