// 引入boot对象
import { boot } from "quasar/wrappers";

//引入echarts
import * as echarts from "echarts";

//导出并使用boot方法(app：Vue应用实例对象)
export default boot(({ app }) => {

  //通过provide提供echarts
  app.provide("echarts", echarts);
})
