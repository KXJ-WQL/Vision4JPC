// 使用 Vite 的 Glob 导入 `pages` 目录下的所有组件
const pages = import.meta.glob('pages/**/*.vue');

// 调整路径的方法(配置组件都要位于page包下，格式：包名/文件名)
function adjustPath(path) {
  return `../pages/${path}.vue`;
}

// 动态加载组件
function loadComponent(path) {
  // 调用 `adjustPath` 方法来生成调整后的路径
  const adjustedPath = adjustPath(path);

  // 检查是否存在对应的组件，存在则返回组件的动态导入函数
  if (pages[adjustedPath]) {
    return pages[adjustedPath]().then((module) => module.default);
  }

  // 路径不匹配时返回 `null`
  return null;
}

// 导出
export { loadComponent };
