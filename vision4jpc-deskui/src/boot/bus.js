//引入boot对象
import { boot } from "quasar/wrappers";
//引入quasar全局事件总线
import { EventBus } from "quasar";

//导出并使用boot方法(app：Vue应用实例对象)
export default boot(({ app }) => {
  const bus = new EventBus();

  //在vue中原型中绑定bus全局事件总线对象
  app.config.globalProperties.$bus = bus;

  // 在根组件中使用provide提供bus(子组件通过inject接收，组件深层次结构传递数据)
  app.provide("bus", bus);
});
