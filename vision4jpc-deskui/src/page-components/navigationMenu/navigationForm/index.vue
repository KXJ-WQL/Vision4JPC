<template>
  <commCard
    :title="getTitle()"
    icon="token"
    width="36vw"
    height="47vh"
    @submit="onSubmit">
    <q-form @submit="onSubmit" ref="navigationForm" class="q-gutter-md">

      <div class="row q-gutter-sm">
        <div class="col">
          <!-- 菜单名称 -->
          <q-input
            v-model="form.title"
            dense
            outlined
            clearable
            label-slot
            color="secondary"
            :rules="[val => !!val || '请输入菜单名称']">
            <template #before>
              <span class="text-body2 text-bold">菜单名称:</span>
            </template>

            <template v-slot:label>
              <div class="row items-center all-pointer-events">
                <q-icon class="q-mr-xs" color="secondary" size="sm" name="token"/>
                菜单名称
              </div>
            </template>
          </q-input>
        </div>

        <div class="col">
          <!-- 路由名称 -->
          <q-input
            v-model="form.name"
            dense
            outlined
            clearable
            label-slot
            color="secondary"
            :rules="[val => !!val || '请输入路由名称']">
            <template #before>
              <span class="text-body2 text-bold">路由名称:</span>
            </template>

            <template v-slot:label>
              <div class="row items-center all-pointer-events">
                <q-icon class="q-mr-xs" color="secondary" size="sm" name="donut_large"/>
                路由名称
              </div>
            </template>
          </q-input>
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
          <!-- 图标大小 -->
          <q-select
            v-model="form.iconSize"
            dense
            outlined
            color="secondary"
            :options="['xs','sm','md','lg','xl']">
            <template #before>
              <span class="text-body2 text-bold">图标大小:</span>
            </template>
          </q-select>
        </div>
      </div>

      <!-- 组件地址 -->
      <q-input
        v-model="form.component"
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
            组件地址(顶级菜单无需输入)
          </div>
        </template>
      </q-input>

      <div class="row q-gutter-sm">
        <div class="col">
          <!-- 父菜单 -->
          <q-select
            v-model="form.parentId"
            :options="newMenuData"
            option-value="navigationId"
            :popup-content-style="{ maxHeight: '25vh' }"
            color="secondary"
            outlined
            stack-label
            dense
            emit-value
            map-options>
            <template #before>
              <span class="text-body2 text-bold">父菜单:</span>
            </template>

            <template v-slot:selected-item="scope">
              <q-icon class="q-mr-xs" color="secondary" size="sm" :name="scope.opt.icon"/>
              {{ $t(scope.opt.title)}}
            </template>

            <template v-slot:option="scope">
              <q-item v-bind="scope.itemProps">
                <q-item-section avatar>
                  <q-icon :name="scope.opt.icon" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>{{ $t(scope.opt.title) }}</q-item-label>
                </q-item-section>
              </q-item>
            </template>
          </q-select>
        </div>

        <div class="col">
          <!-- 菜单类型 -->
          <q-select
            v-model="form.type"
            :options="navigationtype"
            :rules="[val => !!val || '请选择菜单类型']"
            color="secondary"
            outlined
            dense
            emit-value
            map-options>
            <template #before>
              <span class="text-body2 text-bold">菜单类型:</span>
            </template>
          </q-select>
        </div>
      </div>

      <div class="row q-gutter-sm">
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
          <div class="row justify-center">
            <!-- 分割线 -->
            <q-checkbox
              v-model="form.isSeparator"
              true-value = "1"
              false-value= "2"
              label="显示分割线"
              color="secondary"
              checked-icon="task_alt"
              unchecked-icon="highlight_off"/>

            <!-- 路由缓存 -->
            <q-checkbox
              v-model="form.cacheComponentName"
              :true-value="form.name"
              :false-value="null"
              color="secondary"
              label="开启路由缓存"
              checked-icon="task_alt"
              unchecked-icon="highlight_off"/>
          </div>
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
  },
  // 导航菜单数据选项
  menuData :{
    type: Array,
    required: true,
  }
});

// 获取自定义事件
const emits = defineEmits(["update:editNavigationSubmit", "add:addNavigationSubmit"]);

// 菜单类型
const navigationtype = [{label:'系统菜单', value:'1'},{label:'普通页面', value:'2'}]

//标题判断(props.froms为null是添加操作，否则是修改操作)
const getTitle = () => props.forms == null ? "addNavigationMenu" : "alterNavigationMenu"

// 手动补充一个顶级菜单
const newMenuData = [...props.menuData, {"navigationId": 0, "parentId": 0, "title": "topNavigationMenu", "icon": "brightness_7"}];

// 初始化表单数据
const initializeForm = () => {
  // 默认表单值
  const defaultForm = {
    title: "",
    name: "",
    icon: "",
    component: "",
    type: "1",
    iconSize: "sm",
    moduleId: "",
    cacheComponentName: null,
    animation: "",
    parentId: 0,
    order: 1,
    path: "",
    isSeparator: "2"
  };

  // 如果 props.froms 存在则覆盖默认值(nill表示新增，!null表示修改)
  return props.forms ? reactive({ ...defaultForm, ...props.forms }) : reactive(defaultForm);
};

//表单的引用对象(通过collectorForm判断表单是否全部效验通过)
const navigationForm = ref(null);

//表单数据初始化(initializeForm函数必须放在from之前，const没有变量提升)
let form = initializeForm();

// 提交表单的处理函数
const onSubmit = () => {
  navigationForm.value.validate().then((success) => {
    if (success) {
      // 执行表单提交修改或者增加逻辑(通过 toRaw 将 proxy 类型数据转换为基本对象类型)
      props.forms ? emits("update:editNavigationSubmit", toRaw(form)) : emits("add:addNavigationSubmit", toRaw(form));
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
