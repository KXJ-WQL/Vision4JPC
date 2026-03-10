<template>
  <q-bar dense class="q-electron-drag">
    <q-icon
      size="xs"
      name="img:favicon64.ico"
      class="q-electron-drag--exception"
    />
    <div class="text-caption">
      {{ $t("applicationName") }}
    </div>
    <q-space />
    <q-btn flat icon="minimize" @click="minimize" />
    <q-btn flat :icon="maximizeIcon" @click="toggleMaximize" />
    <q-btn flat icon="close" @click="openCloseWinPopup(toolbar)"/>
  </q-bar>

  <!-- 弹出确认框，是否关闭窗口 -->
  <q-dialog v-model="toolbar" no-backdrop-dismiss>
    <q-card class="full-width">
      <q-toolbar>
        <q-icon size="xs" name="img:favicon64.ico" />
        <q-toolbar-title class="text-subtitle2 text-weight-bold">
          {{ $t("applicationName") }}
        </q-toolbar-title>
        <q-btn size="sm" icon="close" flat round v-close-popup />
      </q-toolbar>

      <q-card-section>
        {{ $t("isCloseApplication") }}
      </q-card-section>
      <q-card-actions align="right">
        <q-btn flat :label="$t('cancel')" v-close-popup />
        <q-btn flat :label="$t('confirm')" @click="closeWin" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup>
  import { ref } from "vue";
  import {
    minimize,
    toggleMaximize,
    closeWin,
    maximizeIcon,
  } from "encapsulation/windowManage";

  defineOptions({
    name: "TopLayout",
  });

  // 是否确定关闭的弹窗
  const toolbar = ref(false);

  // 打开确定关闭弹出框
  const openCloseWinPopup = () => {
    if (process.env.MODE === "electron") {
      toolbar.value = true;
    }
  };
</script>
