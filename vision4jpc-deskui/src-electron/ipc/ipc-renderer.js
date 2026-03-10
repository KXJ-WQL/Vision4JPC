//引入ipcRenderer渲染进程ipc通信对象
import { ipcRenderer } from 'electron';

//窗口IPC通信发送API的封装
const ipcApiSend ={
  //关闭窗口
  closeWin: (data) => {
    ipcRenderer.send("closeWin", data);
  }
}

//窗口IPC通信监听API的封装
const ipcApiListener = {

  logInfoListener: (channel, listener) => {
    ipcRenderer.once(channel, (event, ...args) => listener(...args));
  },

  //监听后台系统websocket信息
  websocketMessageListener: (listener) => {
    ipcRenderer.on("websocket-message", (event, data) => listener(data));
  }
}
//导入封装的ipcApi对象
export{
  ipcApiSend,
  ipcApiListener
}

