//引入contextBridge桥接对象
import { contextBridge } from "electron";

//引入ipcRenderer封装模块
import { ipcApiSend, ipcApiListener } from "../ipc/ipc-renderer";

//引入remote封装模块(主窗口的操作)
import winApi from "../remote/electron-remote";

//将ipcApiSend绑定到windows对象中
contextBridge.exposeInMainWorld("ipcApiSend", ipcApiSend);

//将ipcApiListener绑定到windows对象中
contextBridge.exposeInMainWorld("ipcApiListener", ipcApiListener);

//将winApi绑定到windows对象中
contextBridge.exposeInMainWorld("winApi", winApi);
