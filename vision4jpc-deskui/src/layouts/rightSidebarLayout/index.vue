<!-- 主页面中右侧导航 -->
<template>
  <!-- 右侧抽屉，设置为右边显示 -->
  <q-drawer
    show-if-above
    side="right"
    :mini="true"
    :breakpoint="100"
    :mini-width="50"
    mini-to-overlay
    bordered
  >
    <!-- 滚动区域，横向滚动条透明 -->
    <q-scroll-area class="fit" :horizontal-thumb-style="{ opacity: 0 }">
      <q-list>
        <!-- 循环渲染 SidebarItem 组件，右侧每一项 -->
        <SidebarItem
          v-for="item in sideConfigBar"
          :key="item.configMenuId"
          :configMenuId="item.configMenuId"
          :iconName="item.icon"
          :tipToolTitle="item.toolTitle"
          :iconAnimation="item.animation"
          :isBadgeInfo="item.isBadgeinfo"
          :configComponent="item.component"
          @handleClick="handleItemClick"
        />
      </q-list>
    </q-scroll-area>

    <!-- 配置对话框(currentDialogComponent不为 null且configDialogVisible为true时显示对话框)-->
    <q-dialog  :modelValue="configDialogVisible" v-model="configDialogVisible" v-if="currentDialogComponent" >
      <!-- 动态渲染加载的组件 -->
      <component :is="currentDialogComponent" :configData="currentDialogData"/>
    </q-dialog>
  </q-drawer>
</template>

<script setup>
  import SidebarItem from "layoutcomponents/sidebarItem/index.vue";
  import { ref, shallowRef, onBeforeMount } from "vue";
  import { inject } from "vue";
  import { loadComponent } from "encapsulation/sidebarLayout";
  import { getSysConfigMenuVoList } from 'apis/system/configMenu';
  import { setupWebSocketListener } from "config/websocketListener";
  import { createTypeHandlers } from "encapsulation/socketHandler/exceptionWebsocketHandler";

  // 设置组件名称
  defineOptions({
    name: "RightSidebarLayout",
  });

  //获取bus全局事件总线
  const bus = inject("bus");

  // 响应式数据，用于存储侧边栏配置项
  const sideConfigBar = ref([]);

  // 控制配置对话框是否显示
  const configDialogVisible = ref(false);

  // 当前加载的对话框组件(使用shallowRef只对ref中的顶层对象做响应式处理,使用存储动态组件信息可以避免ref递归内部对象的开销)
  const currentDialogComponent = shallowRef(null);

  //当前加载的对话框需要传递的数据
  const currentDialogData = shallowRef(null)

  // 组件挂载前获取系统配置菜单数据
  onBeforeMount(() => {

    // 在组件或模块中定义websocket响应式对象
    const typeHandlers = createTypeHandlers({});

    // 使用创建的类型处理函数对象设置 WebSocket 监听器
    setupWebSocketListener(window.ipcApiListener, typeHandlers);

    //获取配置菜单信息
    fetchConfigMenuVoList()

    //绑定update-navigation全局事件(刷新配置项菜单)
    bus.on("update-navigation",()=>{ fetchConfigMenuVoList() })

  });

  //获取配置菜单信息并响应式渲染
  const fetchConfigMenuVoList = () => {
    getSysConfigMenuVoList((data) => {sideConfigBar.value = data.data});
  }

  // 处理 SidebarItem 点击事件的函数
  const handleItemClick = async (config) => {

    // 动态加载点击项对应的组件
    currentDialogComponent.value = await loadComponent(config.configComponent);

    //动态加载组件需要传递的数据
    currentDialogData.value = config;

    // 显示对话框
    configDialogVisible.value = true;
  };
</script>
