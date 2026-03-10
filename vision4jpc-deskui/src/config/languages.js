//导入 i18n 国际化组件
import { useI18n } from "vue-i18n";

//语言选择列表
const languages = [
  { value: "zh-CN", color: "red" },
  { value: "en-US", color: "teal" },
  { value: "ru", color: "purple" },
  { value: "ar", color: "orange" },
];

// 工厂函数来创建 useI18n 实例
const createI18nLanguageHelper = () => {
  //获取当前语言对象
  const { locale } = useI18n();
  //切换全局语言方法
  const switchLanguage = (lang) => {
    //切换语言
    locale.value = lang;
  };
  //导出
  return {
    locale,
    switchLanguage,
  };
};

export { languages, createI18nLanguageHelper };
