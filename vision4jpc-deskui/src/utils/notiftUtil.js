// 从 Quasar 引入 Notify 组件，用于显示通知
import { Notify } from 'quasar';

// 从 boot 文件中导入 i18n 实例，以便在外部 JS 文件中使用
import { i18n } from '../boot/i18n';

// 定义一个用于创建成功通知的函数
const successNotift = (message) => {
  Notify.create({
    type: 'positive', // 设置通知类型为积极（成功）
    position: 'top', // 设置通知显示在页面的顶部
    message: i18n.global.t(message), // 使用 i18n 实例的 t 函数来翻译通知消息
    color: 'green-9', // 设置通知的颜色为绿色（9级）
    timeout: 500, // 设置通知显示时间为 500 毫秒
    progress: true, //开启倒计时进度条
  });
};

// 定义一个用于创建警告通知的函数
const wranNotift = (message) => {
  Notify.create({
    type: 'warning', // 设置通知类型为积极（成功）
    position: 'top', // 设置通知显示在页面的顶部
    message: message, // 后端做国际化
    color: 'orange-9', // 设置通知的颜色为橘黄色（9级）
    timeout: 500, // 设置通知显示时间为 500 毫秒
    progress: true, //开启倒计时进度条
  });
};

// 定义一个用于创建错误异常通知的函数
const errorNotift = (message) => {
  Notify.create({
    type: 'negative', // 设置通知类型为积极（成功）
    position: 'top-right', // 设置通知显示在页面的顶部
    message: message, // 后端做国际化
    color: 'red-9', // 设置通知的颜色为红色（9级）
    timeout: 800, // 设置通知显示时间为 800 毫秒
    progress: true, //开启倒计时进度条
  });
};

// 导出函数
export {
  successNotift,
  wranNotift,
  errorNotift
};

