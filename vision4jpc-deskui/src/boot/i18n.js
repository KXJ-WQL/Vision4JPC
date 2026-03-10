//引入boot对象
import { boot } from "quasar/wrappers";
//引入createI18n对象
import { createI18n } from "vue-i18n";
//引入语言数据对象
import messages from "src/i18n";
//引入Quasar系统对象
import { Quasar } from "quasar";

//创建并初始化
const i18n = createI18n({
  locale: Quasar.lang.getLocale(), //检查地区动态获取语言
  globalInjection: true, //允许全局注入 i18n 实例,在整个 Vue 应用中使用 this.$t 或 $t 方法进行翻译
  messages, //语言对象
});

//使用boot启动文件对象(注：使用boot启动对象代替传统vue的main.js，组件的注册和声明全局组件都使用boot)
export default boot(({ app }) => {

  //注册全局组件
  app.use(i18n);
});

//导出i18n供外部js使用
export { i18n }
