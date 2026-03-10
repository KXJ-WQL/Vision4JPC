// 引入os模块，用于获取操作系统信息
import os from "os";

// 引入electron的app(主进程)和BrowserWindow(窗体)
import { app, BrowserWindow } from "electron";

// 引入远程对象的(渲染进程可以通过remote调用主进程中的方法)
import { initialize, enable } from "@electron/remote/main";

//引入websocket通信服务模块
import WebSocketService from '../services/websocket/sysWebscoket';

// 引入封装后的ipcMain模块方法
import { closeWin } from "../ipc/ipc-main";

// 引入Spring Boot启动管理服务
import { startSpringBoot, stopSpringBoot } from '../services/springBootManager';

// 引入windows窗体配置信息文件
import windowsConfig from '../config/windows-config';

// 获取平台信息
const platform = process.platform || os.platform();

// 声明主窗体为全局变量
let mainWindow, loadWindow;

//springBoot Jar进程管理对象
let springBootProcess;

// 初始化remote
initialize();

//应用单例化处理(不允许同时启动多个程序开启多窗口) -- 以下代码不能封装到js文件中
const getTheLock = app.requestSingleInstanceLock() // 获取系统应用单例锁的方法

// 如果未能获取到锁（说明已经有一个实例在运行）
if (!getTheLock) {
  app.quit();
} else {
  // 如果获取到了锁，设置在第二次实例启动时的行为
  app.on('second-instance', (event, commandLine, workingDirectory) => {
    // 当运行第二个实例时，聚焦到mainWindow这个窗口
    if (mainWindow) { // 如果mainWindow窗口对象存在
      if (mainWindow.isMinimized()) { // 如果窗口被最小化
        mainWindow.restore(); // 恢复窗口
      }
      mainWindow.focus(); // 聚焦到窗口
    }
  });
}

// 创建加载页面窗体函数
function createLoadingWindow() {
  // 创建加载窗口实例，使用配置文件中的加载窗口配置
  loadWindow = new BrowserWindow(windowsConfig.loadingWindow);

  // 使用 ready-to-show 事件确保内容加载完再显示窗口
  loadWindow.once('ready-to-show', () => {
    // 强制刷新
    loadWindow.show();
  });

  /*
  * 在页面窗口加载完成后触发，加这个是由于electron的问题，我设置背景色透明第一次显示时总还是白色背景，需要再次显示才能生效，所以再显示之后就重新再显示一遍
  * 注：如果找到好的办法，把这个代码删除掉
  * */
  loadWindow.webContents.on('did-finish-load', () => {
    // 确保加载完成后背景仍然是透明的
    loadWindow.setBackgroundColor('#00000000');
    // 再次显示透明色
    loadWindow.show();
  });

  // 加载loading页面，获取资源路径中的loading.html
  loadWindow.loadFile(windowsConfig.getResourcePath('loading.html'));

  // 加载窗口显示后，创建主应用窗口
  loadWindow.once('show', createWindow);
}

// 创建系统应用窗体函数
function createWindow() {
  // 创建主窗口实例，使用配置文件中的主窗口配置
  mainWindow = new BrowserWindow(windowsConfig.mainWindow);

  // remote注册上下文
  enable(mainWindow.webContents);

  //开发环境不加载Jar包
  if(process.env.DEV){ //DEV
    // 直接加载页面
    mainWindow.loadURL(process.env.APP_URL);
  }

  //生产环境加载Springboot Jar包
  if(process.env.PROD){ //PROD
    //启动SpringBoot后端程序再渲染应用页面
    springBootProcess = startSpringBoot(() => {
      mainWindow.loadURL(process.env.APP_URL);
    });
  }

  // 开发模式打开调试窗口
  if (process.env.DEV) {
    mainWindow.webContents.openDevTools(); // 开启开发者工具
  } else {
    mainWindow.webContents.on("devtools-opened", () => {
      mainWindow.webContents.closeDevTools(); // 在非调试模式下关闭开发者工具
    });
  }

  // 加载页面成功后触发监听显示窗体
  mainWindow.on("ready-to-show", () => {
    loadWindow.hide(); // 隐藏加载窗口
    loadWindow.close(); // 关闭加载窗口
    mainWindow.maximize(); // 最大化窗口
    mainWindow.show(); // 显示主窗口
    initWebsocket(); // 初始化websocket通信
  });

  // 窗口关闭事件
  mainWindow.on("closed", () => {
    mainWindow = null; // 释放主窗口对象
  });

  // 关闭窗口的IPC监听
  closeWin(() => {
    mainWindow.close(); // 监听到关闭窗口事件时关闭主窗口
  });
}

//初始化Websocket
function initWebsocket() {
  // 始化WebSocket构造函数
  const websocketService = new WebSocketService(
    process.env.BACKEND_WEBSOCKET_URL
  );
  // 初始化连接传递信息回调方法
  websocketService.init((data) => {
    // 发送消息给渲染进程(?.可选链接操作符)
    mainWindow ?. webContents.send("websocket-message", data);
  });
}

// 禁止硬件加速
app.disableHardwareAcceleration();

// Electron完成初始化后创建加载窗口
app.whenReady().then(() => {
  createLoadingWindow(); // 创建并显示加载窗口
});

// 窗体全部关闭时触发
app.on('window-all-closed', () => {
  if (platform !== "darwin") { // 如果不是macOS平台
    //Spring Boot进程服务是否存在
    if (springBootProcess) {
      stopSpringBoot(springBootProcess); // 停止Spring Boot服务
    }
    app.quit(); // 退出应用
  }
});

// 应用被重新激活事件
app.on("activate", () => {
  if (mainWindow === null) { // 只有在主窗口不存在时重新创建窗口
    createWindow();
  }
});
