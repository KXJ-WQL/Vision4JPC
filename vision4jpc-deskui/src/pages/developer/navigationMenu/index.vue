<template>
  <!-- 路由导航管理主页面 -->
  <q-page padding class="pages column">

    <!--头部按钮-->
    <div class="col-1 row justify-end q-mt-sm q-mb-sm q-gutter-sm">
      <q-btn outline icon="add" :label="$t('addNavigationMenu')" @click="addNavigationMenuMethod"/>
    </div>

    <!--表格信息-->
    <div class="col-9">
      <navigationTable
        :rows="rows"
        :columns="columns"
        :loading="tableLoading"
        :moduleDatas="moduleDatas"
        @update:alterNavigationMenuMethod = "alterNavigationMenuMethod"
        @update:editNavigationSubmit = "editNavigationSubmit"
        @delete:deleteNavigationMenuMethod = "deleteNavigationMenuMethod"
      />
    </div>

    <!--修改和新增弹窗-->
    <q-dialog v-model="isNavigationMenuDialog">
      <navigationForm
        :menuData="rows"
        :forms="navigationMenuFrom"
        :moduleDatas="moduleDatas"
        @update:editNavigationSubmit = "editNavigationSubmit"
        @add:addNavigationSubmit = "addNavigationSubmit"
      />
    </q-dialog>
  </q-page>
</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import { getNavigationList, addNavigation, updateNavigation, deleteNavigation } from "apis/system/navigation"
import { getModuleList } from "apis/system/module"
import navigationTable from "pagecomponents/navigationMenu/navigationTable/index.vue"
import navigationForm from "pagecomponents/navigationMenu/navigationForm/index.vue"
import { successNotift } from "utils/notiftUtil";
import { systemConfirmationDialog } from "utils/dialogUtil";

defineOptions({ name: "NavigationMenu" });

//导航栏表格数据
const rows = ref([])
//模块数据
const moduleDatas = ref([])
//表格加载状态
const tableLoading = ref(false);
//修改或增加弹窗
const isNavigationMenuDialog = ref(false);
//传递给子组件的from(增加时值null，修改时值为当前修改的值)
const navigationMenuFrom = ref(null);

// 页面渲染前
onBeforeMount(() => {
  getNavigationLists(); // 获取导航菜单信息
  getModuleLists(); // 获取模块信息
})

const columns = [
  { name: 'title', align: 'center', label: '菜单名称', field: 'title'},
  { name: 'icon', align: 'center', label: '菜单图标', field: 'icon'},
  { name: 'name', align: 'center', label: '路由名称', field: 'name' },
  { name: 'component', align: 'center', label: '组件地址', field: 'component'},
  { name: 'type', align: 'center', label: '菜单类型', field: 'type'},
  { name: 'parentId', align: 'center', label: '父菜单', field: 'parentId' },
  { name: 'animation', align: 'center',  label: '动画效果', field: 'animation'},
  { name: 'isSeparator', label: '分割线', field: 'isSeparator', align: "center"},
  { name: 'cacheComponentName', label: '路由缓存', field: 'cacheComponentName', align: "center"},
  { name: 'moduleId', label: '所属模块', field: 'moduleId', align: "center"},
  { name: "operation", label: "操作", align: "center" }
]

//获取导航菜单数据方法
const getNavigationLists = () => {
  //table显示加载
  tableLoading.value = true;
  //请求导航栏数据列表
  getNavigationList((data) => {
    rows.value = data.data; //响应式赋值
    tableLoading.value = false; //隐藏加载状态显示table数据
  });
};

//获取模块列表数据方法
const getModuleLists = () => getModuleList((data) => moduleDatas.value = data.data);

// 显示新增弹窗方法
const addNavigationMenuMethod = ()=> {
  isNavigationMenuDialog.value = true;
  navigationMenuFrom.value = null
}

// 显示修改弹窗方法
const alterNavigationMenuMethod = (row) => {
  isNavigationMenuDialog.value = true;
  navigationMenuFrom.value = row
}

// 新增导航菜单提交方法
const addNavigationSubmit = (form)=>{
  // 调用新增方法
  addNavigation(form, (data) => {
    successNotift("addNavigationSuccess"); //提示信息
    getModuleLists(); //重新获取模块数据
    getNavigationLists(); //重新获取菜单数据
    isNavigationMenuDialog.value = false; //关闭弹窗
  })
}

// 修改导航菜单提交方法
const editNavigationSubmit = (form)=>{
  // 调用修改方法
  updateNavigation(form, (data) => {
    successNotift("updateNavigationSuccess"); //提示信息
    getModuleLists(); //重新获取模块数据
    getNavigationLists(); //重新获取菜单数据
    isNavigationMenuDialog.value = false; //关闭弹窗
  })
}

//删除方法
const deleteNavigationMenuMethod = (data) => {
  //系统对话框确认
  systemConfirmationDialog("removeNavigationMenu", "sureDelete", () => {
    //提取对象中的Id为一个list数组
    deleteNavigation({ data: [data.navigationId] },
      (data) => {
        successNotift("removeNavigationSuccess"); //提示信息
        getModuleLists(); //重新获取模块数据
        getNavigationLists(); //重新获取菜单数据
        isNavigationMenuDialog.value = false; //关闭弹窗
      }
    );
  });
};
</script>
