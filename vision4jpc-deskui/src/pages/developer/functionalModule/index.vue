<template >
  <!-- 功能模块管理主页面 -->
  <q-page padding class="pages column">

    <!--头部按钮-->
    <div class="col-1 row justify-end q-mt-sm q-mb-sm q-gutter-sm">
      <q-btn outline icon="add" :label="$t('addModule')" @click="addModuleMethod"/>
    </div>

    <!--表格信息-->
    <div class="col-9">
      <moduleTable
        :rows="rows"
        :columns="columns"
        :loading="tableLoading"
        @update:alterModuleMethod = "alterModuleMethod"
        @delete:deleteModuleMethod = "deleteModuleMethod"
      />
    </div>

    <!--修改和新增弹窗-->
    <q-dialog v-model="isFunctionalModuleDialog">
      <moduleForm
        :forms="functionalModuleForm"
        :moduleDatas="rows"
        @update:editModuleSubmit = "editModuleSubmit"
        @add:addModuleSubmit = "addModuleSubmit"
      />
    </q-dialog>
  </q-page>

</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import { getModuleTreeList, updateModuleTreeList, addModule, deleteModule} from "src/apis/system/module";
import moduleTable from "pagecomponents/functionalModule/moduleTable/index.vue"
import moduleForm from "pagecomponents/functionalModule/moduleForm/index.vue"

import { successNotift } from "utils/notiftUtil";
import { systemConfirmationDialog } from "utils/dialogUtil";

defineOptions({ name: "FunctionalModule" });

// 导航栏表格数据
const rows = ref([])
// 表格加载状态
const tableLoading = ref(false);
// 修改或增加弹窗
const isFunctionalModuleDialog = ref(false);
// 传递给子组件的from(增加时值null，修改时值为当前修改的值)
const functionalModuleForm = ref(null);

// 页面渲染前
onBeforeMount(() => {
  getFunctionalModuleData();
})

const columns = [
  { name: 'name', align: 'center', label: '模块名称', field: 'name'},
  { name: 'key', align: 'center', label: '模块标识', field: 'key'},
  { name: 'icon', align: 'center', label: '模块图标', field: 'icon' },
  { name: 'description', align: 'center', label: '模块描述', field: 'description'},
  { name: 'parentId', align: 'center', label: '父模块', field: 'parentId'},
  { name: 'isEnabled', align: 'center', label: '状态', field: 'isEnabled' },
  { name: "operation", label: "操作", align: "center" }
]

//获取功能模块数据方法
const getFunctionalModuleData = () => {
  //table显示加载
  tableLoading.value = true;
  //请求模块数据
  getModuleTreeList((data) =>{
    rows.value = data.data; //响应式赋值
    tableLoading.value = false; //隐藏加载状态显示table数据
  })
}

// 显示新增弹窗方法
const addModuleMethod = ()=> {
  isFunctionalModuleDialog.value = true;
  functionalModuleForm.value = null
}

// 显示修改弹窗方法
const alterModuleMethod = (row) => {
  isFunctionalModuleDialog.value = true;
  functionalModuleForm.value = row
}

// 新增功能模块提交方法
const addModuleSubmit = (form)=>{
  // 调用新增方法
  addModule(form, (data) => {
    successNotift("addModuleSuccess"); //提示信息
    getFunctionalModuleData(); //重新获取模块数据
    isFunctionalModuleDialog.value = false; //关闭弹窗
  })
}

// 修改功能模块提交方法
const editModuleSubmit = (form)=>{
  // 调用修改方法
  updateModuleTreeList(form, (data) => {
    successNotift("updateModuleSuccess"); //提示信息
    getFunctionalModuleData(); //重新获取模块数据
    isFunctionalModuleDialog.value = false; //关闭弹窗
  })
}

//删除功能模块方法
const deleteModuleMethod = (data) => {
  //系统对话框确认
  systemConfirmationDialog("removeModule", "sureDelete", () => {
    //提取对象中的imagecollectorId为一个list数组
    deleteModule({ data: [data.moduleId] },
      (data) => {
        successNotift("removeModuleSuccess"); //提示信息
        getFunctionalModuleData(); //重新获取模块数据
        isFunctionalModuleDialog.value = false; //关闭弹窗
      }
    );
  });
};
</script>
