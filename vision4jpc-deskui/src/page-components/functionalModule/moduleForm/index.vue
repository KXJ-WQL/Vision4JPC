<template>
  <commCard
    :title="getTitle()"
    icon="webhook"
    width="35vw"
    height="32vh"
    @submit="onSubmit">
    <q-form @submit="onSubmit" ref="moduleForm" class="q-gutter-md">

      <div class="row q-gutter-sm">
        <div class="col">
          <!-- 模块名称 -->
          <q-input
            v-model="form.name"
            dense
            outlined
            clearable
            label-slot
            color="secondary"
            :rules="[val => !!val || '请输入模块名称']">
            <template #before>
              <span class="text-body2 text-bold">模块名称:</span>
            </template>

            <template v-slot:label>
              <div class="row items-center all-pointer-events">
                <q-icon class="q-mr-xs" color="secondary" size="sm" name="webhook"/>
                模块名称
              </div>
            </template>
          </q-input>
        </div>

        <div class="col">
          <!-- 模块唯一标识 -->
          <q-input
            v-model="form.key"
            dense
            outlined
            clearable
            label-slot
            color="secondary"
            :rules="[val => !!val || '请输入模块标识']">
            <template #before>
              <span class="text-body2 text-bold">模块标识:</span>
            </template>

            <template v-slot:label>
              <div class="row items-center all-pointer-events">
                <q-icon class="q-mr-xs" color="secondary" size="sm" name="workspace_premium"/>
                模块标识
              </div>
            </template>
          </q-input>
        </div>
      </div>

      <div class="row q-gutter-sm">
        <!-- 模块图标 -->
        <div class="col">
          <q-input
            v-model="form.icon"
            :rules="[val => !!val || '请输入选择图标']"
            label="选择图标"
            dense
            outlined
            readonly>
            <template #before>
              <span class="text-body2 text-bold">模块图标:</span>
            </template>

            <template v-slot:prepend>
              <q-icon color="secondary " :name="form.icon || 'help'" />
            </template>

            <q-menu auto-close>
              <div class="icon-panel">
                <div v-for="item in materialIcons" :key="item" class="icon-item" @click="form.icon = item">
                  <q-icon color="secondary" :name="item" size="sm" />
                  <div class="icon-name">
                    {{ item }}
                  </div>
                </div>
              </div>
            </q-menu>
          </q-input>
        </div>

        <div class="col">
          <!-- 父模块 -->
          <q-select
            v-model="form.parentId"
            :options="newModuleDatas"
            option-value="moduleId"
            :popup-content-style="{ maxHeight: '25vh' }"
            color="secondary"
            outlined
            stack-label
            dense
            emit-value
            map-options>
            <template #before>
              <span class="text-body2 text-bold">父模块:</span>
            </template>

            <template v-slot:selected-item="scope">
              <q-icon class="q-mr-xs" color="secondary" size="sm" :name="scope.opt.icon"/>
              {{ scope.opt.name }}
            </template>

            <template v-slot:option="scope">
              <q-item v-bind="scope.itemProps">
                <q-item-section avatar>
                  <q-icon :name="scope.opt.icon" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>{{ scope.opt.name }}</q-item-label>
                </q-item-section>
              </q-item>
            </template>
          </q-select>
        </div>
      </div>

      <!-- 模块说明 -->
      <q-input
        v-model="form.description"
        outlined
        dense
        clearable
        color="secondary"
        label-slot>
        <template #before>
          <span class="text-body2 text-bold">模块说明:</span>
        </template>

        <template v-slot:label>
          <div class="row items-center all-pointer-events">
            <q-icon class="q-mr-xs" color="secondary" size="sm" name="space_dashboard"/>
            模块说明
          </div>
        </template>
      </q-input>

      <div class="row q-gutter-sm">
        <div class="col">
          <!-- 模块状态 -->
          <q-select
            v-model="form.isEnabled"
            :options="enabledStatus"
            :rules="[val => !!val || '请选择模块状态']"
            color="secondary"
            outlined
            dense
            emit-value
            map-options>
            <template #before>
              <span class="text-body2 text-bold">模块状态:</span>
            </template>
          </q-select>
        </div>
        <div class="col">
          <!-- 管理权限 -->
          <q-select
            v-model="form.isAlter"
            :options="alterOption"
            :rules="[val => !!val || '请选择权限']"
            color="secondary"
            outlined
            dense
            emit-value
            map-options>
            <template #before>
              <span class="text-body2 text-bold">管理权限:</span>
            </template>
          </q-select>
        </div>
      </div>
    </q-form>
  </commCard>
</template>

<script setup>
import { reactive, ref, toRaw } from "vue";
import commCard from "basiscomponents/commCard/index.vue"
import materialIcons from "encapsulation/icons"

// 接收父组件传入的数据
const props = defineProps({
  // 表单信息
  forms: {
    type: Object,
    required: false,
  },
  // 模块数据项
  moduleDatas: {
    type: Array,
    required: true,
  },
});

// 获取自定义事件
const emits = defineEmits(["update:editModuleSubmit", "add:addModuleSubmit"]);

// 模块状态
const enabledStatus = [{label:'开启', value:'1'},{label:'关闭', value:'2'}]

// 管理权限
const alterOption = [{label:'用户模块', value:'1'},{label:'管理员模块', value:'2'}]

//标题判断(props.froms为null是添加操作，否则是修改操作)
const getTitle = () => props.forms == null ? "addModule" : "alterModule"

// 手动补充一个顶级模块
const newModuleDatas= [...props.moduleDatas, {"moduleId": 0, "parentId": 0, "name": "顶级模块", "key": "", "icon": "brightness_7"}];

// 初始化表单数据
const initializeForm = () => {
  // 默认表单值
  const defaultForm = {
    name: "",
    key: "",
    icon: "",
    description: "",
    isEnabled: "1",
    isAlter: "1",
    parentId: 0,
  };

  // 如果 props.froms 存在则覆盖默认值(nill表示新增，!null表示修改)
  return props.forms ? reactive({ ...defaultForm, ...props.forms }) : reactive(defaultForm);
};

//表单的引用对象(通过collectorForm判断表单是否全部效验通过)
const moduleForm = ref(null);

//表单数据初始化(initializeForm函数必须放在from之前，const没有变量提升)
let form = initializeForm();

// 提交表单的处理函数
const onSubmit = () => {
  moduleForm.value.validate().then((success) => {
    if (success) {
      // 执行表单提交修改或者增加逻辑(通过 toRaw 将 proxy 类型数据转换为基本对象类型)
      props.forms ? emits("update:editModuleSubmit", toRaw(form)) : emits("add:addModuleSubmit", toRaw(form));
    }
  });
};

</script>

<style scoped lang="scss">

.animation-popup {
  max-height: 300px;   /* 限制高度 */
  overflow-y: auto;    /* 超出显示滚动条 */
}

.animation-select-popup {
  max-height: 40vh;
}

.icon-panel{
  width: 28vw;
  max-height: 30vh;
  padding:10px;
  display:grid;
  grid-template-columns: repeat(3,1fr);
  gap:10px;
}

.icon-item{
  display:flex;
  flex-direction:column;
  align-items:center;
  cursor:pointer;
  padding:8px;
  border-radius:6px;
}

.icon-item:hover{
  background: $positive;
}

.icon-name{
  font-size:12px;
  margin-top:4px;
}

</style>
