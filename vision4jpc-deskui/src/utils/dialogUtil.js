// 从 Quasar 引入 Dialog 组件，用于对话框用户确认
import { Dialog } from 'quasar'

// 从 boot 文件中导入 i18n 实例，以便在外部 JS 文件中使用
import { i18n } from '../boot/i18n';

// 系统确认对话框
const systemConfirmationDialog = (title, message, callback) => {
  Dialog.create({
    //对话框标题(18n国际化)
    title: i18n.global.t(title),
    //对话框信息
    message: i18n.global.t(message),
    //确认按钮样式
    ok: {
      color: 'green-7',
      flat : true
    },
    //关闭按钮样式
    cancel: {
      color: 'blue-grey-6',
      flat : true
    }
  }).onOk(() => {//确认回调
    //调用回调方法
    callback()
  })
}

export { systemConfirmationDialog }


