<p align="center">
  <img src="https://raw.githubusercontent.com/KXJ-WQL/Vision4JPC/refs/heads/master/doc/image/logo.png" alt="logo" width="200">
</p>

<div align="center" style="margin-top: 10px;">
    <div style="display: flex; justify-content: center; gap: 10px; flex-wrap: wrap;">
        <img src="https://img.shields.io/badge/OpenJdk-17+-986724.svg?style=flat-square&logo=openjdk" alt="Java version">
        <img src="https://img.shields.io/badge/Spring-6.0.21+-006699.svg?style=flat-square&logo=spring" alt="Spring version">
        <img src="https://img.shields.io/badge/SpringBoot-3.1.12+-6DB33F.svg?style=flat-square&logo=springboot" alt="SpringBoot version">
        <img src="https://img.shields.io/badge/NodeJS-18.16.0+-584578.svg?style=flat-square&logo=nodedotjs" alt="NodeJs version">
        <img src="https://img.shields.io/badge/Vue-3.4.18+-4F5B93.svg?style=flat-square&logo=vue.js" alt="Vue version">
        <img src="https://img.shields.io/badge/Electron-31.3.1+-5e2866.svg?style=flat-square&logo=Electron" alt="Electron version">
        <img src="https://img.shields.io/badge/Quasar-2.16.0+-46796b.svg?style=flat-square&logo=Quasar" alt="Quasar version"> 
    </div>
</div>

### Vision4JPC简介🎡

自从接触开发以来，我一直很想设计并实现一套 PC 端前后端一体化的开发脚手架。在非牛马的时间里，我逐步进行了探索与实现，目前已经完成了一个基础框架的开发。

该系统是一款 **本地服务化的 PC 应用模块框架**。后端通过本地服务提供统一接口能力，前端以桌面应用的形式运行。开发者可以在此脚手架基础上进行二次开发，快速构建各类本地桌面应用系统。

该脚手架二次开发后的典型应用场景如下：

- **工控机软件**：在工控机（IPC）上运行本地服务，通过接口或通信协议与设备进行交互，可用于构建设备管理与控制系统、生产辅助系统、本地数据采集处理与可视化监控等应用。
- **离线系统 / 局域网系统**：在无法连接互联网或对网络依赖较低的环境中运行，例如工厂车间、实验室或内网环境，通过本地服务完成业务处理与数据管理。
- **桌面工具软件**：用于开发各类企业内部工具或开发辅助工具，例如文件批处理工具、数据导入导出工具、日志分析工具、批量接口测试工具、运维辅助工具等。
- **设备调试与开发工具**：用于设备调试、协议测试或接口联调，例如串口通信调试、网络协议调试、设备状态监控等开发辅助工具。

### ⚓技术栈
|  | 技术栈                   |      版本       |
|:---:|:----------------------|:-------------:|
| **后端** | 核心框架：Spring Boot      |    3.1.12+    |
| | JDK版本：OpenJDK         |      17+      |
| | 数据库：MySQL、Redis       | 5.7+ / 7.4.0+ |
| | ORM框架：MyBatis         |    2.3.1+     |
| **前端** | 核心框架：Vue              |    3.4.18+    |
| | UI组件库：Quasar          |    2.16.0+    |
| | 状态管理：Vuex             |    4.0.1+     |
| | 路由管理：Vue Router       |    4.0.12+    |
| **桌面应用** | 桌面框架：Electron         |    31.3.1+    |
| | 打包工具：electron-builder |   24.13.3+    |

✨ **项目特性**
- 前后端一体化：基于Spring Boot + Vue + Electron，一套代码同时构建后端服务和桌面应用
- 本地服务优先：服务在本地运行，可以不依赖外部网络
- 模块化设计：业务模块可独立开发、独立部署，便于功能扩展
- 开箱即用：提供基础的路由管理、模块管理、日志记录等功能，后续有时间会开发新功能
- 平台支持：目前仅支持Windows，后续会兼容Linux和IOS主流操作系统

### 🚀 快速演示
**准备环境：** 启动应用前，请确保本地已安装并运行以下服务
- MySQL 5.7+：需提前创建数据库 vision4jpc，并导入项目提供的 SQL 文件（默认用户名和密码均为 root）
- Redis 7.4.0+：确保 Redis 服务已正常启动

下载 Vision4JPC-Setup-Win64.exe 安装程序，双击运行完成安装即可启动
<p align="center">
  <img src="https://raw.githubusercontent.com/KXJ-WQL/Vision4JPC/refs/heads/master/doc/image/Vision4JPC-Show.gif" alt="logo" width="100%">
</p>
