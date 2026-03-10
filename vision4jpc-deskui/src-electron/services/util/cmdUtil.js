// 从path模块导入path，用于处理和转换文件路径
import path from 'path';
// 从net模块导入net，用于创建网络服务器和客户端
import net from 'net';
// 从child_process子进程模块导入exec执行系统指令
import {exec} from "child_process";

const platform = process.platform;

// 获取Spring Boot JAR包路径的方法
const getJarPath = () => {
  // 如果存在环境变量DEV，则使用DEV环境下的路径
  return process.env.DEV
    ? path.join(process.env.DEV_PACKAGERE_RESOURCES, process.env.SPRINGBOOT_JAR_NAME) // DEV环境下拼接JAR包路径
    : path.join(process.cwd(), 'resources/resources', 'vision4jpc-system-1.0.0-Beta.jar'); // 非DEV环境下拼接JAR包路径
};

// 检查指定端口是否被占用的方法
const isPortTaken = (port, callback) => {
  // 创建一个TCP服务器，尝试监听指定端口
  const server = net.createServer()
    .once('error', (err) => { // 如果监听失败（端口被占用）
      // 错误码为EADDRINUSE表示端口被占用
      if (err.code === 'EADDRINUSE') {
        //调用回调函数，传递true表示端口被占用
        callback(true);
      }
    })
    .once('listening', () => { // 如果监听成功（端口未被占用）
      //关闭服务器
      server.close();
      // 调用回调函数，传递false表示端口未被占用
      callback(false);
    })
    .listen(port); //尝试监听指定的端口
};

//查找并杀死占用指定端口的进程
const killProcessOnPort = (port) => {
  let command = ''; //定义命令字符串

  if (platform === 'win32') { //如果操作系统是Windows
    //CMD命令：查询占用端口的进程ID并终止
    command = `for /f "tokens=5" %a in ('netstat -aon ^| findstr :${port} ^| findstr LISTENING') do (taskkill /F /PID %a)`;
  } else { //如果操作系统是Unix（包括Linux和Mac）
    //SHELL命令：查询占用端口的进程ID并终止
    command = `lsof -t -i:${port} | xargs kill -9`;
  }

  //执行生成的命令，杀死占用指定端口的进程
  exec(command);
};

//导出
export {
  getJarPath,
  isPortTaken,
  killProcessOnPort}
