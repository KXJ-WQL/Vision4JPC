<template>
  <router-view />
</template>

<script setup>
import { onMounted } from "vue";


defineOptions({
  name: "App",
});

//系统挂载成功关闭加载页
onMounted(() => {
  //关闭系统加载
  document.getElementById("wuhao_loader")
    ? document.getElementById("wuhao_loader").remove()
    : null;
});
</script>
