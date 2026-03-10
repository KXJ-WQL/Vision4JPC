// 从child_process模块导入spawn，用于执行外部命令
import { spawn } from 'child_process';
// 导入工具类
import { getJarPath, isPortTaken, killProcessOnPort } from './util/cmdUtil';


// 定义Spring Boot应用监听的端口号
const springBootPort = 9999;

// 启动Spring Boot应用，并在启动成功后调用回调函数
const startSpringBoot = (callback) => {
  const jarPath = getJarPath(); // 获取Spring Boot JAR包路径

  // 检查Spring Boot应用监听的端口是否被占用
  isPortTaken(springBootPort, (isTaken) => {
    if (isTaken) { // 如果端口被占用
      killProcessOnPort(springBootPort); // 杀死占用端口的进程
    }

    // 使用spawn命令启动Spring Boot进程
    const springBootProcess = spawn('java', ['-jar', jarPath]);

    // 监听Spring Boot进程的标准输出流，判断应用是否启动成功
    springBootProcess.stdout.on('data', (data) => {
      // 如果输出包含成功启动的信息
      if (data.toString().includes("Vision4JPC started successfully")) {
        // 调用回调函数，表示应用启动成功，调用回调方法渲染应用程序页面
        callback && callback();
      }
    });

    // 返回Spring Boot进程对象
    return springBootProcess;
  });
};

// 停止Spring Boot应用的方法
const stopSpringBoot = (springBootProcess) => {
  if (springBootProcess) { // 如果Spring Boot进程对象存在
    springBootProcess.kill('SIGINT'); // 向进程发送SIGINT信号，终止进程
  }
};

// 统一暴露模块中的方法
export { startSpringBoot, stopSpringBoot };

