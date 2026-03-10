<template>
  <q-layout view="hHh Lpr lFf">
    <q-header elevated class="bg-primary text-positive">
      <!--顶部栏-->
      <topLayoyt></topLayoyt>

      <!--顶部菜单栏-->
      <topMenuLayout></topMenuLayout>

    </q-header>

    <!--左边侧边栏-->
    <LeftSideBarLayout></LeftSideBarLayout>

    <!--右边侧边栏-->
    <RightSidebarLayout></RightSidebarLayout>

    <!--内容区域-->
    <q-page-container>

      <!-- 页面路由显示区域 -->
     <RouterViewContainer></RouterViewContainer>

      <!-- 内容区域顶部导航标签页 -->
      <TopPageNavigationLayout></TopPageNavigationLayout>

      <!--射击浮点按钮-->
      <FloatShootButton></FloatShootButton>

    </q-page-container>
  </q-layout>
</template>

<script setup>
  import { onBeforeMount,ref } from "vue";
  import { useStore} from "vuex"
  import { getAllSysConfigisEnabledVoList } from "apis/system/config"
  import { getModuleMap } from "apis/system/module"
  import { updateAllConfigItem } from "utils/configStoreUtil"
  import { updateAllModuleItem } from "utils/moduleStoreUtil"

  //引入头部(顶部)栏
  import topLayoyt from "layouts/topLayout/index.vue";

  //引入头部(顶部)菜单栏
  import topMenuLayout from "layouts/topMenuLayout/index.vue";

  //左边侧边栏
  import LeftSideBarLayout from "layouts/leftNavigationLayout/index.vue";

  //右边侧边栏
  import RightSidebarLayout from "layouts/rightSidebarLayout/index.vue";

  //页面路由显示区域
  import RouterViewContainer from "layouts/routerPageContainer/index.vue";

  //内容区域顶部导航辅助栏
  import TopPageNavigationLayout from "layouts/topTagPageLayout/index.vue";

  //射击浮动按钮
  import FloatShootButton from "layouts/floatShootButton/index.vue";

  defineOptions({
    name: "MainLayout",
  });

  //获取vuex状态管理对象
  const store = useStore();

  //系统页面显示前获取当前所有的开启模块的系统配置参数并存入stroe中
  onBeforeMount(()=>{
    //获取当前开启模块的所有系统配置参数并存入自定义的store集中数据管理对象中
    getAllSysConfigisEnabledVoList((data)=>{updateAllConfigItem(store,data.data)})
    //获取当前所有模块的Map信息并存入自定义的store集中数据管理对象中
    getModuleMap((data)=>{updateAllModuleItem(store,data.data)})
  })

</script>
