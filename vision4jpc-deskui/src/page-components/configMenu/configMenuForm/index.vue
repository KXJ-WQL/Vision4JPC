<template>
  <commCard
    :title="getTitle()"
    icon="control_camera"
    width="35vw"
    height="30vh"
    @submit="onSubmit">
    <q-form @submit="onSubmit" ref="configMenuForm" class="q-gutter-md">

      <div class="row q-gutter-sm">
        <div class="col">
          <!-- 配置菜单名称 -->
          <q-input
            v-model="form.toolTitle"
            dense
            outlined
            clearable
            label-slot
            color="secondary"
            :rules="[val => !!val || '请输入配置菜单名称']">
            <template #before>
              <span class="text-body2 text-bold">菜单名称:</span>
            </template>

            <template v-slot:label>
              <div class="row items-center all-pointer-events">
                <q-icon class="q-mr-xs" color="secondary" size="sm" name="control_camera"/>
                配置菜单名称
              </div>
            </template>
          </q-input>
        </div>

        <div class="col">
          <!-- 所属模块 -->
          <q-select
            v-model="form.moduleId"
            :options="moduleDatas"
            option-value="moduleId"
            :popup-content-style="{ maxHeight: '25vh' }"
            :rules="[val => !!val || '请选择选择模块']"
            outlined
            dense
            emit-value
            map-options
            color="secondary">
            <template #before>
              <span class="text-body2 text-bold">所属模块:</span>
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

      <div class="row q-gutter-sm">
        <!-- 菜单图标 -->
        <div class="col">
          <q-input
            v-model="form.icon"
            :rules="[val => !!val || '请选择菜单图标']"
            label="选择图标"
            dense
            outlined
            readonly>
            <template #before>
              <span class="text-body2 text-bold">菜单图标:</span>
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
          <!-- 动画 -->
          <q-select
            v-model="form.animation"
            :options="animations"
            :popup-content-style="{ height: '25vh' }"
            :rules="[val => !!val || '请选择选择动画']"
            color="secondary"
            outlined
            dense>
            <template #before>
              <span class="text-body2 text-bold">动画效果:</span>
            </template>

            <template v-slot:selected-item="scope">
              <q-icon :class="['animated', scope.opt, 'infinite']" size="sm" name="animation" />
              {{ scope.opt }}
            </template>

            <template v-slot:option="scope">
              <q-item v-bind="scope.itemProps">
                <q-item-section>
                  <q-item-label>{{ scope.opt}}</q-item-label>
                </q-item-section>
              </q-item>
            </template>
          </q-select>
        </div>
      </div>

      <!-- 组件地址 -->
      <q-input
        v-model="form.component"
        :rules="[val => !!val || '请输入组件地址']"
        outlined
        dense
        clearable
        color="secondary"
        label-slot>
        <template #before>
          <span class="text-body2 text-bold">组件地址:</span>
        </template>

        <template v-slot:label>
          <div class="row items-center all-pointer-events">
            <q-icon class="q-mr-xs" color="secondary" size="sm" name="space_dashboard"/>
            组件地址
          </div>
        </template>
      </q-input>

      <div class="row q-gutter-sm">
        <div class="col">
          <!-- 排列顺序 -->
          <q-input
            v-model="form.order"
            type="number"
            dense
            outlined
            clearable
            label-slot
            color="secondary"
            :rules="[val => !!val || '请输入顺序权重']">

            <template #before>
              <span class="text-body2 text-bold">顺序权重:</span>
            </template>

            <template v-slot:label>
              <div class="row items-center all-pointer-events">
                <q-icon class="q-mr-xs" color="secondary" size="sm" name="123"/>
                顺序权重
              </div>
            </template>
          </q-input>
        </div>

        <div class="col">
          <!-- 徽标 -->
          <q-checkbox
            v-model="form.isBadgeinfo"
            true-value = "1"
            false-value= "2"
            label="显示徽标"
            color="secondary"
            checked-icon="task_alt"
            unchecked-icon="highlight_off"
          />
        </div>
      </div>
    </q-form>
  </commCard>
</template>

<script setup>
import { reactive, ref, toRaw } from "vue";
import commCard from "basiscomponents/commCard/index.vue"
import materialIcons from "encapsulation/icons"
import animations from "encapsulation/animations"

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
  }
});

// 获取自定义事件
const emits = defineEmits(["update:editConfigMenuSubmit", "add:addConfigMenuSubmit"]);

//标题判断(props.froms为null是添加操作，否则是修改操作)
const getTitle = () => props.forms == null ? "addConfigMenu" : "alterConfigMenu"

// 初始化表单数据
const initializeForm = () => {
  // 默认表单值
  const defaultForm = {
    toolTitle: "",
    icon: "",
    animation: "",
    component: "",
    isBadgeinfo: "2",
    moduleId: "",
    order: "1"
  };

  // 如果 props.froms 存在则覆盖默认值(nill表示新增，!null表示修改)
  return props.forms ? reactive({ ...defaultForm, ...props.forms }) : reactive(defaultForm);
};

//表单的引用对象(通过collectorForm判断表单是否全部效验通过)
const configMenuForm = ref(null);

//表单数据初始化(initializeForm函数必须放在from之前，const没有变量提升)
let form = initializeForm();

// 提交表单的处理函数
const onSubmit = () => {
  configMenuForm.value.validate().then((success) => {
    if (success) {
      // 执行表单提交修改或者增加逻辑(通过 toRaw 将 proxy 类型数据转换为基本对象类型)
      props.forms ? emits("update:editConfigMenuSubmit", toRaw(form)) : emits("add:addConfigMenuSubmit", toRaw(form));
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
