//引入remote的控制窗口对象
import { BrowserWindow } from '@electron/remote'

//窗口操作对象
const winApi = {

  //最小化窗口
  minimize () {

    BrowserWindow.getFocusedWindow().minimize()
  },

  //判断是否为最大化
  isMaxiAndMin(){
    return  BrowserWindow.getFocusedWindow().isMaximized()
  },

  //窗口最大化
  toggleMaximize () {
    const win = BrowserWindow.getFocusedWindow()

    if (win.isMaximized()) {
      win.unmaximize()
    } else {
      win.maximize()
    }
  },

  //窗口关闭
  close () {
    BrowserWindow.getFocusedWindow().close()
  }
}

//导出对象
export default winApi

