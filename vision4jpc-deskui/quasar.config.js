const { configure } = require("quasar/wrappers");
const path = require("path");

module.exports = configure(function (/* ctx */) {
  return {
    // boot文件在Vue应用启动时自动加载
    boot: [
      "i18n", // 国际化
      "axios", // HTTP客户端
      "bus", // 全局事件总线
      "echarts", //echarts图表
    ],

    // 自定义SCSS文件
    css: ["application.scss"],

    // 引入额外的字体和图标
    extras: [
      "roboto-font", // Roboto字体
      "material-icons", // Material图标
    ],

    build: {
      target: {
        // 浏览器目标版本
        browser: ["es2019", "edge88", "firefox78", "chrome87", "safari13.1"],
        node: "node20", // Node.js版本
      },

      //构建添加process.env变量
      env: {
        ELECTRON_DISABLE_SECURITY_WARNINGS: true,
        //electron生产模式下的静态资源路径
        DEV_PACKAGERE_RESOURCES: path.join(process.cwd(), "src-electron/resources"),
        //electron开发模式下的静态资源路径
        PROD_PACKAGERE_RESOURCES: path.join(process.cwd(), "resources/resources"),
        //SpringBoot Jar包名称
        SPRINGBOOT_JAR_NAME: "vision4jpc-system-1.0.0-Beta.jar",
        //系统应用加载页的名称
        APPLICATION_LOADING_NAME: "loading.html",
        //系统websocket服务地址
        BACKEND_WEBSOCKET_URL: "ws://127.0.0.1:9999/targetlocation",
        //后端服务地址
        BACKEND_SERVER_URL: "http://127.0.0.1:8080"
      },

      vueRouterMode: "hash", // 路由模式，使用hash

      //文件别名
      alias: {
        //@相对路由
        "@": path.join(__dirname, "./src"),
        //系统配置设置路径(如设置语言、主题等内容)
        config: path.join(__dirname, "./src/config"),
        //自定义layout布局基础子组件路径
        layoutcomponents: path.join(__dirname, "./src/layout-components"),
        //自定义page页面基础子组件路径
        pagecomponents: path.join(__dirname, "./src/page-components"),
        //自定义基本组件路径(如自定义基础的list、from等)
        basiscomponents: path.join(__dirname, "./src/basis-components"),
        //组件逻辑封装成单独js(简化vue的script代码)
        encapsulation: path.join(__dirname, "./src/encapsulation"),
        //http请求封装
        apis: path.join(__dirname, "./src/apis"),
        //js工具类(各组件都可共有)
        utils: path.join(__dirname, "./src/utils"),
        //router路由路径
        router: path.join(__dirname, "./src/router"),
      },

      // Vite插件配置
      vitePlugins: [
        [
          "@intlify/vite-plugin-vue-i18n",
          {
            include: path.resolve(__dirname, "./src/i18n/**"), // 包含i18n文件
          },
        ],
        [
          "vite-plugin-checker",
          {
            eslint: {
              lintCommand: 'eslint "./**/*.{js,mjs,cjs,vue}"', // eslint命令
            },
          },
          { server: false },
        ], // 关闭服务端检查
      ],
    },

    // devServer 配置对象用于设置开发服务器的相关选项
    devServer: {
      // 设置开发服务器的端口号
      port: 8888,
      // 设置为 true 时，开发服务器启动后会自动在默认浏览器中打开应用
      open: true,
      // 配置代理服务器
      proxy: {
        // 字符串匹配：当请求路径以 /api 开头时，进行代理处理
        "/vision4jpc": {
          // 目标服务器的地址
          target: "http://127.0.0.1:8080",
          // 是否更改请求头中的源地址为目标服务器地址
          changeOrigin: true,
          // 重写请求路径，将 /shoot 前缀替换为空字符串
          rewrite: (path) => path.replace(/^\/vision4jpc/, ""),
        },
      },
    },

    framework: {
      //添加配置项
      config: { notify: {} },
      //添加插件
      plugins: ["Notify", "Dialog"], //Notify通知, Dialog对话框
      //设置为中文
      lang: "zh-CN",
      //设置字体图标库
      iconSet: "material-icons",
    },

    //导入Animate.css动画资源
    animations: "all",

    ssr: {
      pwa: false, // 服务器端渲染不使用PWA
      prodPort: 3000, // 生产环境端口
      middlewares: [
        "render", // 保持最后一个中间件
      ],
    },

    pwa: {
      workboxMode: "generateSW", // 使用generateSW模式
      injectPwaMetaTags: true, // 注入PWA元标签
      swFilename: "sw.js", // Service Worker文件名
      manifestFilename: "manifest.json", // manifest文件名
      useCredentialsForManifestTag: false, // 不使用凭据
    },

    cordova: {
      // Cordova配置
    },

    capacitor: {
      hideSplashscreen: true, // 隐藏启动屏幕
    },

    //定义源文件变量
    sourceFiles: {
      //electron主文件地址
      electronMain: "src-electron/main/electron-main",
      //electron预加载文件地址
      electronPreload: "src-electron/preload/electron-preload",
    },

    electron: {
      inspectPort: 5858, // 调试端口

      bundler: "packager", // 使用packager打包

      // Electron Packager配置
      packager: {
        // electron打包自定义的资源文件（SpringBoot Jar、加载页都要放在这个目录下就行打包）
        extraResource: "src-electron/resources/",
      },

      // electron-builder 配置
      builder: {
        // 应用程序的唯一标识符
        appId: "vision4jpc",
        // Windows 平台的配置选项
        win: {
          // 发布者名称
          publisherName: "空想家",
          // 打包目标和架构配置
          target: {
            // 指定打包目标类型为 NSIS（Nullsoft Scriptable Install System），用于创建 Windows 安装程序
            target: "nsis",
            // 指定打包架构为 ia32（32 位架构）
            arch: ["ia32"],
          },
        },
      },
    },

    bex: {
      contentScripts: [
        "my-content-script", // 浏览器扩展内容脚本
      ],
    },
  };
});
