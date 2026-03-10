// 最大化窗口和取消最大化图标转换
let maximizeIcon = "crop_square";

// 窗口最小化
const minimize = () => {
  if (process.env.MODE === "electron") {
    window.winApi.minimize();
  }
};

// 窗口最大化
const toggleMaximize = () => {
  if (process.env.MODE === "electron") {
    window.winApi.toggleMaximize();
    maximizeIcon = window.winApi.isMaxiAndMin() ? "content_copy" : "crop_square";
  }
};

// 关闭窗口应用
const closeWin = () => {
  if (process.env.MODE === "electron") {
    window.winApi.close();
  }
};

// 统一暴露
export { minimize, toggleMaximize, closeWin, maximizeIcon };
