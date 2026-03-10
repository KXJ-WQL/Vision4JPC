//导入暗黑模式插件
import { Dark } from "quasar";
//切换系统颜色工具函数
import { setCssVar } from "quasar";

//主题配色列表
const themes = [
  { value: "light", color: "grey-5" },
  { value: "dark", color: "grey-10" },
  { value: "blue", color: "blue" },
];

//切换主题配色方法
const switchThemesColors = (value) => {
  switch (value) {
    case "dark":
      Dark.set(true);
      darkSetting();
      break;
    case "light":
      lightSetting();
      break;
    case "blue":
      blueSetting();
      break;
  }
};

//当前暗黑模式是否开启(如果开启进行关闭)
const isDarkActive = () => {
  if (Dark.isActive) {
    Dark.set(false);
  }
};

//暗黑主题配色
const darkSetting = () => {
  setCssVar("primary", "#272a2a");
  setCssVar("positive", "#fcfdfa");

};

//浅色主题配色
const lightSetting = () => {
  isDarkActive();
  setCssVar("primary", "#ffffff");
  setCssVar("positive", "#272a2a");
};

//蓝色色主题配色
const blueSetting = () => {
  isDarkActive();
  setCssVar("primary", "#3362a9");
  setCssVar("positive", "#e0e1e4");
};

export { themes, switchThemesColors };
