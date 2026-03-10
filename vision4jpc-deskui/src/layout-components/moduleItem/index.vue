<template>
  <q-item tag="label" v-ripple :disable="isDisabled">
    <!-- 左侧的图标部分 -->
    <q-item-section avatar>
      <q-icon :name="modules.icon" />
    </q-item-section>

    <!-- 中间的模块名称和描述部分 -->
    <q-item-section>
      <q-item-label>{{ modules.name }}</q-item-label>
      <q-item-label caption>{{ modules.description }}</q-item-label>
    </q-item-section>

    <!-- 右侧的复选框部分 -->
    <q-item-section side>
      <q-checkbox
        :disable="isDisabled"
        v-model="modules.isEnabled"
        :val="modules.moduleId"
        true-value="1"
        false-value="2"
        checked-icon="task_alt"
        unchecked-icon="highlight_off"
        :color="isChild ? 'orange' : 'teal'"
        @update:model-value="handleUpdate"
      />
    </q-item-section>
  </q-item>
</template>

<script setup>
  import { defineProps, defineEmits, toRefs, computed } from 'vue';

  // 组件名称定义
  defineOptions({
    name: "ModuleItem",
  });

  // 接收父组件传递的属性
  const props = defineProps({
    modules: Object, // 模块数据
    isChild: Boolean
  });

  // 将props解构为响应式变量
  const { modules, isChild } = toRefs(props);

  // 定义组件的自定义事件
  const emits = defineEmits(['updateModuleValue']);

  // 计算属性判断模块是否被禁用
  const isDisabled = computed(() => {
    return modules.value.isAlter == 2;
  });

  // 处理模块更新的函数
  const handleUpdate = (newValue) => {
    // 触发updateModuleValue事件，将新值和模块数据传递给父组件
    emits('updateModuleValue', newValue, modules.value);
  };
</script>
