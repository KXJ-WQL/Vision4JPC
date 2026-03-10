<template>
  <q-page padding class="pages">

    <div class="row q-col-gutter-lg">

      <div class="col-12 col-md-6">
        <q-card class="full-height" bordered>

          <q-card-section class="text-center">
            <img class="logo" src="~assets/image/logo-back.png"/>
          </q-card-section>

          <q-separator/>

          <q-card-section class="text-grey-8">
            自从接触开发以来，我一直很想想设计并实现一套 PC 端前后端一体化的开发脚手架。在非牛马的时间里，我逐步进行了探索与实现，目前已经完成了一个基础框架的开发。
            该系统是一款 <b>本地服务化的 PC 应用模块框架</b>。后端通过本地服务提供统一接口能力，前端以桌面应用的形式运行。开发者可以在此脚手架基础上进行二次开发，快速构建各类本地桌面应用系统。
            该脚手架二次开发后的典型应用场景如下：
            <ul class="q-mt-sm">
              <li>
                <b>工控机软件</b>：在工控机（IPC）上运行本地服务，通过接口或通信协议与设备进行交互，可用于构建设备管理与控制系统、生产辅助系统、本地数据采集处理与可视化监控等应用。
              </li>
              <li>
                <b>离线系统 / 局域网系统</b>：在无法连接互联网或对网络依赖较低的环境中运行，例如工厂车间、实验室或内网环境，通过本地服务完成业务处理与数据管理。
              </li>
              <li>
                <b>桌面工具软件</b>：用于开发各类企业内部工具或开发辅助工具，例如文件批处理工具、数据导入导出工具、日志分析工具、批量接口测试工具、运维辅助工具等。
              </li>
              <li>
                <b>设备调试与开发工具</b>：用于设备调试、协议测试或接口联调，例如串口通信调试、网络协议调试、设备状态监控等开发辅助工具。
              </li>
            </ul>

          </q-card-section>

        </q-card>
      </div>

      <div class="col-12 col-md-6">
        <q-card class="full-height" bordered>

          <q-card-section>
            <div class="text-h6">技术选型</div>
          </q-card-section>

          <q-separator/>

          <q-card-section>
            <div class="tech-stack">

              <div class="q-mb-sm">
                <strong>后端技术：</strong>
                <q-chip color="purple" text-color="white">Java 17</q-chip>
                <q-chip color="orange-8" text-color="white">SpringBoot</q-chip>
                <q-chip color="blue-grey" text-color="white">MyBatis</q-chip>
                <q-chip color="light-green-9" text-color="white">Redis</q-chip>
                <q-chip color="blue" text-color="white">Fastjson2</q-chip>
                <q-chip color="brown" text-color="white">......</q-chip>
              </div>

              <div>
                <strong>前端技术：</strong>
                <q-chip color="green" text-color="white">JavaScript</q-chip>
                <q-chip color="teal" text-color="white">Vue3</q-chip>
                <q-chip color="deep-purple" text-color="white">Electron</q-chip>
                <q-chip color="orange" text-color="white">Quasar</q-chip>
                <q-chip color="pink" text-color="white">Vuex</q-chip>
                <q-chip color="blue-grey" text-color="white">Vite</q-chip>
                <q-chip color="brown" text-color="white">......</q-chip>
              </div>

            </div>
          </q-card-section>

        </q-card>
      </div>

      <div class="col-12 col-md-6">
        <q-card class="version-card" bordered>

          <q-card-section>
            <div class="text-h6">版本信息: 当前 V1.0.0-Beta(开发测试版本)</div>
          </q-card-section>

          <q-separator/>

          <q-scroll-area class="version-scroll">

            <q-card-section>

              <q-expansion-item
                expand-separator
                icon="wind_power"
                label="V1.0.0-Beta - 2025-4-18"
                header-class="text-weight-medium"
              >

                <div class="q-pl-md q-pb-sm">
                  <ul class="log-list">
                    <li>项目初始版本发布</li>
                    <li>日志和异常统一处理</li>
                    <li>实现桌面端的基础布局</li>
                    <li>实现跨窗口通信与多窗口管理</li>
                    <li>实现动态导航菜单系统</li>
                    <li>提供开发者功能模块</li>
                  </ul>
                </div>
              </q-expansion-item>
            </q-card-section>
          </q-scroll-area>
        </q-card>
      </div>

      <div class="col-12 col-md-6">
        <q-card class="full-height" bordered>

          <q-card-section>
            <div class="text-h6">联系作者</div>
          </q-card-section>

          <q-separator/>

          <q-card-section class="row items-center">

            <div class="col">
              <div class="q-mb-sm">
                GitHub：
                <a href="https://github.com/KXJ-WQL" target="_blank" rel="noopener noreferrer">
                  https://github.com/KXJ-WQL
                </a>
              </div>

              <div class="q-mb-sm">
                Gitee：
                <a href="https://github.com/KXJ-WQL" target="_blank" rel="noopener noreferrer">
                  https://gitee.com/WQL-KXJ
                </a>
              </div>

              <div class="q-mb-sm">
                Email：2562694007@qq.com
              </div>

              <div>
                微信 / QQ：2562694007
              </div>
            </div>

            <div class="col-auto text-center">
              <img class="qrcode" src="~assets/image/wx.png"/>
              <div class="text-caption text-grey">扫码联系作者</div>
            </div>

          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script setup>
import { defineOptions } from "vue";

defineOptions({name: "HomePage"});

</script>

<style scoped>

/* 四宫格卡片通用样式 */
.q-card {
  transition: all 0.3s;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.08);
}

.q-card:hover {
  box-shadow: 0 8px 20px rgba(0,0,0,0.15);
  transform: translateY(-2px);
}

/* LOGO */
.logo{
  width: 380px;
  max-width: 100%;
  transition: transform 0.3s;
}

.q-card-section.text-center:hover .logo{
  transform: scale(1.05);
}

/* 技术选型 */
.tech-stack{
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 8px;
}

.tech-stack q-chip{
  cursor: default;
  transition: all 0.3s;
}

.tech-stack q-chip:hover{
  transform: scale(1.05);
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}

/* 版本信息滚动条美化 */
.version-card{
  height: 35vh;
}

.version-scroll{
  height: calc(100% - 60px);
}

/* 自定义滚动条 (Chrome/Edge) */
.version-scroll::-webkit-scrollbar {
  width: 6px;
}

.version-scroll::-webkit-scrollbar-track {
  background: #f0f0f0;
  border-radius: 3px;
}

.version-scroll::-webkit-scrollbar-thumb {
  background: #999;
  border-radius: 3px;
}

.version-scroll::-webkit-scrollbar-thumb:hover {
  background: #666;
}

/* 版本日志 */
.log-list{
  padding-left: 18px;
  line-height: 26px;
}

.log-list li{
  margin-bottom: 4px;
}

/* 联系方式二维码 */
.qrcode{
  width: 120px;
  transition: transform 0.3s;
  cursor: pointer;
}

.qrcode:hover{
  transform: scale(1.15);
}

/* 联系信息链接效果 */
a{
  color: #1976d2;
  text-decoration: none;
}

a:hover{
  text-decoration: underline;
}

/* 卡片标题 */
.text-h6{
  font-weight: 600;
}

/* 响应式间距 */
@media (max-width: 1024px){
  .q-card{
    margin-bottom: 16px;
  }
}
</style>
