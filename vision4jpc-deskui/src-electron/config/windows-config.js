import path from 'path';
import {app} from "electron"; // 引入Node.js的path模块，用于处理和转换文件路径

// 路径处理函数：根据环境变量获取资源路径
const getResourcePath = (fileName) =>{
  return process.env.DEV
    ? path.join(process.env.DEV_PACKAGERE_RESOURCES, fileName) // 开发环境使用指定的DEV资源路径
    : path.join(process.cwd(), 'resources/resources', fileName); // 生产环境使用当前工作目录下的资源路径(放在env中无需，只能直接写)
}

// 配置对象：包含窗口配置和路径处理函数
const config = {

  // 主窗口配置
  mainWindow: {
    icon: path.resolve(__dirname, "icons/icon.png"), // 主窗口的图标路径
    width: 1260, // 主窗口的宽度
    height: 615, // 主窗口的高度
    minWidth: 1260, // 窗体缩放的最小宽度
    minHeight: 610, // 窗体缩放的最小高度
    show: false, // 默认不显示窗口，等待内容加载完成后手动触发显示
    useContentSize: true, // 将宽度和高度直接应用于页面内容
    frame: false, // 隐藏窗口边框和菜单
    webPreferences: {
      contextIsolation: true, // 启用上下文隔离
      sandbox: false, // 关闭沙盒模式，以便渲染进程使用更多Node.js功能
      nodeIntegration: true, // 允许在渲染进程中使用Node.js模块
      enableRemoteModule: true, // 启用remote模块，允许渲染进程访问主进程的功能
      preload: path.resolve(__dirname, process.env.QUASAR_ELECTRON_PRELOAD), // 指定预加载脚本的路径
      webSecurity: false //允许跨域访问
    },
  },

  // 加载窗口配置
  loadingWindow: {
    icon: path.resolve(__dirname, "icons/icon.png"), // 显示图标路径
    transparent: true, // 设置窗口背景为透明
    backgroundColor: '#00000000', // 确保背景色透明
    width: 350, // 加载窗口的宽度
    height: 300, // 加载窗口的高度
    show: false, // 默认不显示窗口，等待内容加载完成后显示
    useContentSize: true, // 将宽度和高度直接应用于页面内容
    frame: false, // 隐藏窗口边框和菜单
    webPreferences: {
      backgroundThrottling: false, // 禁用后台节流，确保窗口在后台时仍然正常渲染
      nodeIntegration: true,  // 允许在渲染进程中使用Node.js模块
      webSecurity: false //允许跨域访问
    }
  },
  getResourcePath, // 路径处理函数，用于获取资源文件的路径
};

export default config; // 导出配置对象，供主线程模块使用
