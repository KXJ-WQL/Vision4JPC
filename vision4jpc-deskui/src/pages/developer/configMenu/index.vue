<template >
  <!-- 配置菜单管理主页面 -->
  <q-page padding class="pages column">

    <!--头部按钮-->
    <div class="col-1 row justify-end q-mt-sm q-mb-sm q-gutter-sm">
      <q-btn outline icon="add" :label="$t('addModule')" @click="addConfigMenuMethod"/>
    </div>

    <!--表格信息-->
    <div class="col-9">
      <configMenuTable
        :rows="rows"
        :columns="columns"
        :tableLoading="tableLoading"
        :moduleDatas="moduleDatas"
        @update:alterConfigMenuMethod = "alterConfigMenuMethod"
        @delete:deleteConfigMenuMethod = "deleteConfigMenuMethod"
      />
    </div>

    <!--修改和新增弹窗-->
    <q-dialog v-model="isConfigMenuDialog">
      <configMenuForms
        :forms="configMenuForm"
        :moduleDatas="moduleDatas"
        @update:editConfigMenuSubmit = "editConfigMenuSubmit"
        @add:addConfigMenuSubmit = "addConfigMenuSubmit"
      />
    </q-dialog>
  </q-page>
</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import { getSysConfigMenuList, addSysConfigMenu, updateSysConfigMenu, deleteSysConfigMenu} from "src/apis/system/configMenu";
import { getModuleList } from "apis/system/module"
import configMenuTable from "pagecomponents/configMenu/configMenuTable/index.vue"
import configMenuForms from "pagecomponents/configMenu/configMenuForm/index.vue"
import { successNotift } from "utils/notiftUtil";
import { systemConfirmationDialog } from "utils/dialogUtil";

defineOptions({ name: "FunctionalModule" });

// 配置菜单表格数据
const rows = ref([])
// 表格加载状态
const tableLoading = ref(false);
// 模块数据
const moduleDatas = ref([])
// 修改或增加弹窗
const isConfigMenuDialog = ref(false);
// 传递给子组件的from(增加时值null，修改时值为当前修改的值)
const configMenuForm = ref(null);

// 页面渲染前
onBeforeMount(() => {
  getConfigMenuData(); // 获取配置菜单信息
  getModuleLists(); // 获取模块信息
})

const columns = [
  { name: 'toolTitle', align: 'center', label: '配置菜单名称', field: 'toolTitle'},
  { name: 'component', align: 'center', label: '组件路径', field: 'component'},
  { name: 'icon', align: 'center', label: '配置菜单图标', field: 'icon' },
  { name: 'isBadgeinfo', align: 'center', label: '徽标', field: 'isBadgeinfo'},
  { name: 'moduleId', align: 'center', label: '所属模块', field: 'moduleId' },
  { name: "operation", label: "操作", align: "center" }
]

// 获取配置菜单数据方法
const getConfigMenuData = () => {
  //table显示加载
  tableLoading.value = true;
  //请求配置菜单数据
  getSysConfigMenuList((data) =>{
    rows.value = data.data; //响应式赋值
    tableLoading.value = false; //隐藏加载状态显示table数据
  })
}

// 获取模块列表数据方法
const getModuleLists = () => getModuleList((data) => moduleDatas.value = data.data);

// 显示新增弹窗方法
const addConfigMenuMethod = ()=> {
  isConfigMenuDialog.value = true;
  configMenuForm.value = null
}

// 显示修改弹窗方法
const alterConfigMenuMethod = (row) => {
  isConfigMenuDialog.value = true;
  configMenuForm.value = row
}

// 新增配置菜单提交方法
const addConfigMenuSubmit = (form)=>{
  // 调用新增方法
  addSysConfigMenu(form, (data) => {
    successNotift("addModuleSuccess"); //提示信息
    getConfigMenuData(); //重新获取模块数据
    isConfigMenuDialog.value = false; //关闭弹窗
  })
}

// 修改配置菜单提交方法
const editConfigMenuSubmit = (form)=>{
  // 调用修改方法
  updateSysConfigMenu(form, (data) => {
    successNotift("updateModuleSuccess"); //提示信息
    getConfigMenuData(); //重新获取配置菜单数据
    getModuleLists(); //重新获取模块数据
    isConfigMenuDialog.value = false; //关闭弹窗
  })
}

// 删除配置菜单方法
const deleteConfigMenuMethod = (data) => {
  //系统对话框确认
  systemConfirmationDialog("removeModule", "sureDelete", () => {
    //提取对象中的Id为一个list数组
    deleteSysConfigMenu( { data: data.map((item) => item.configMenuId) },
      (data) => {
        successNotift("removeModuleSuccess"); //提示信息
        getConfigMenuData(); //重新获取配置菜单数据
        getModuleLists(); //重新获取模块数据
        isConfigMenuDialog.value = false; //关闭弹窗
      }
    );
  });
};
</script>
