<template>
  <q-card style="width: 35vw">
    <!-- 标题部分 -->
    <div class="q-pa-sm flex items-center">
      <q-icon name="webhook" size="sm" />
      <span class="text-h8 text-weight-bold q-ml-sm">{{ $t("functionOptions") }}</span>
      <q-space /> <!-- 占位符，使按钮靠右对齐 -->
      <q-btn size="sm" icon="close" flat round v-close-popup />
    </div>

    <q-separator />

    <!-- 模块列表部分 -->
    <q-card-section style="max-height: 50vh; height: 50vh" class="scroll">
      <q-list style="width: 30vw">

        <!-- 遍历所有模块，生成ModuleItem组件 -->
        <template v-for="module in modules" :key="module.moduleId">
          <ModuleItem
            :modules="module"
            :is-child="false"
            @updateModuleValue="updateModuleValue"
          />

          <!-- 如果当前模块已启用且有子模块，则遍历展示子模块 -->
          <div v-if="isModuleEnabled(module)" class="q-ml-lg">
            <ModuleItem
              v-for="childModule in module.childrenModule"
              :key="childModule.moduleId"
              :is-child="true"
              :modules="childModule"
              @updateModuleValue="updateModuleValue"
            />
          </div>
        </template>
      </q-list>
    </q-card-section>

    <q-separator />

    <!-- 操作按钮-->
    <q-card-actions align="right">
      <q-btn flat icon="cancel" :label="$t('close')" v-close-popup />
    </q-card-actions>

    <!-- 加载效果 -->
    <q-inner-loading :showing="loading">
      <q-spinner-gears size="3em" />
    </q-inner-loading>
  </q-card>
</template>

<script setup>
  import { ref, reactive, onBeforeMount, toRaw, inject } from 'vue';
  import { useStore} from "vuex"
  import ModuleItem from 'layoutcomponents/moduleItem/index.vue';
  import { getModuleTreeList, updateModuleTreeList } from 'apis/system/module';
  import { getNavigationTree } from 'apis/system/navigation';
  import { getAllSysConfigisEnabledVoList } from "apis/system/config"
  import { getModuleMap } from "apis/system/module"
  import { getUpdateModuleRoutes } from 'router/routes';
  import { updateAllConfigItem } from "utils/configStoreUtil"
  import { updateAllModuleItem } from "utils/moduleStoreUtil"

  //获取bus全局事件总线
  const bus = inject("bus");

  // 组件名称定义
  defineOptions({
    name: 'ModuleCheckbox',
  });

  // 获取vuex状态管理对象
  const store = useStore();
  // 系统功能模块数据
  let modules;
  // 是否开启加载效果变量
  const loading = ref(true);

  // 挂载前钩子，在组件挂载到DOM之前执行
  onBeforeMount(() => {
    // 发送GET请求，获取模块列表数据
    getModuleTreeList((data) => {
        modules = reactive(data.data); // 将返回的数据转为响应式，赋值给modules变量
        loading.value = false; // 数据加载完成后，将loading状态设为false，隐藏加载效果
      })
  });

  //判断模块是否启用(用于是否显示子模块列表)
  const isModuleEnabled = (module) => {
    return module.isEnabled === '1';
  };

  // 更新模块选中状态的处理函数
  const updateModuleValue = (isChecked, moduleValue) => {
    //必须使用toRaw函数将响应式数据对象转换为普通对象json格式(否则不能与后端Dao对象进行映射)
    updateModuleTreeList(toRaw(moduleValue),(data)=>{
      //当模块树列表更新完成后，调用更新系统页面的函数
      updtaeNavigationListPage();
      //重新获取模块的配置选项信息更新到store中
      updtaeStoreConfigItem();
      //重新获取功能模块信息更新到store中
      updtaeStoreModuleItem()
    })
  };

  // 获取后台路由菜单并更新系统页面的处理函数
  const updtaeNavigationListPage = () => {
    // 获取导航树数据（即后台路由菜单）
    getNavigationTree((data) => {
      // 根据获取的导航树数据更新模块路由
      getUpdateModuleRoutes(data.data);
      // 通过事件总线通知左侧导航栏组件更新导航菜单
      bus.emit("update-navigation");
    });
  };

  // 模块更新程序获取模块的配置选项信息更新到store中
  const updtaeStoreConfigItem = () => {
    //获取当前开启模块的所有系统配置参数并存入自定义的store集中数据管理对象中
    getAllSysConfigisEnabledVoList((data)=>{updateAllConfigItem(store,data.data)})
  }

  // 模块更新程序获取功能模块的信息更新到store中
  const updtaeStoreModuleItem = () => {
    //获取当前功能模块信息并存入自定义的store集中数据管理对象中
    getModuleMap((data)=>{updateAllModuleItem(store,data.data)})
  }

</script>
