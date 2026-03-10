// 引入boot对象
import { boot } from "quasar/wrappers";
import { wranNotift } from "utils/notiftUtil";
// 引入axios组件
import axios from "axios";

/*
 * 创建axios实例对象api, 并设置基础 URL
 * 1.开发环境使用devserver代理服务器，设置前缀即可
 * 2.electron打包后不能使用devserver代理服务器，需要直接访问(无重写地址需要注意URL)
 * */
const api = axios.create({
  baseURL: process.env.DEV
    ? "/vision4jpc"
    : process.env.PROD
    ? "http://127.0.0.1:8080"
    : null,
});

// 创建响应拦截器
api.interceptors.response.use(
  (response) => {
    if (response.data.code == 200) {
      // 如果响应 code 为 200，则返回响应的 data 字段数据
      return response.data;
    } else {
      // 如果响应 code 不是 200，则显示警告信息
      wranNotift(response.data.message);
      // 返回一个被拒绝的 Promise，这样不会触发后续回调
      return Promise.reject(new Error(response.data.message));
    }
  },
  (error) => {
    //响应请求错误信息
    Promise.reject(error);
  }
);

// boot函数来定义插件，该插件将axios和api实例挂载到Vue的全局属性上
export default boot(({ app }) => {
  // 将 axios 挂载到 Vue 应用的全局属性上
  app.config.globalProperties.$axios = axios;

  // 将自定义的 api 实例挂载到 Vue 应用的全局属性上
  app.config.globalProperties.$api = api;
});

// 导出api实例，以便在其他地方使用
export { api };
