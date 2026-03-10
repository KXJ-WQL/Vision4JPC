<template>
  <q-card :style="{ maxWidth: width ,width: width }">

    <!-- 标题 -->
    <div class="q-pa-sm flex items-center">
      <q-icon :name="icon" size="sm" />
      <span class="text-h8 text-weight-bold q-ml-sm">
        {{ $t(title) }}
      </span>

      <q-space />

      <q-btn size="sm" icon="close" flat round v-close-popup />
    </div>

    <q-separator />

    <!-- 内容 -->
    <q-card-section class="scroll" :style="{maxHeight: height,height: height}">
      <!-- 默认插槽 -->
      <slot />
    </q-card-section>

    <q-separator />

    <!-- 操作按钮 -->
    <q-card-actions align="right">
      <q-btn
        flat
        icon="cancel"
        :label="$t(closeText)"
        v-close-popup
      />

      <q-btn
        flat
        icon="download_done"
        :label="$t(submitText)"
        @click="onSubmit"
      />
    </q-card-actions>

  </q-card>
</template>

<script setup>
import { defineOptions } from "vue"

defineOptions({
  name: "CommCard"
})

const emit = defineEmits(["submit"])

const props = defineProps({

  // 标题
  title: {
    type: String,
    required: true
  },

  // 宽度
  width: {
    type: String,
    default: "27vw"
  },

  // 内容高度
  height: {
    type: String,
    default: "37vh"
  },

  // 图标
  icon: {
    type: String,
    default: "token"
  },

  // 提交按钮文本
  submitText: {
    type: String,
    default: "submit"
  },

  // 关闭按钮文本
  closeText: {
    type: String,
    default: "close"
  }

})

function onSubmit() {
  emit("submit")
}
</script>
