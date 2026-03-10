<template>
  <!--缓存路由组件
    注：在Vue3中，<router-view> 是一个组件，它不再直接渲染嵌套的 <keep-alive>组件，而是通过插槽将渲染的组件传递，使用 v-slot 提供的 Component 插槽属性。
    -->
  <router-view v-slot="{ Component }">
    <KeepAlive :include="cacheComponentNameList">
      <component :is="Component"/>
    </KeepAlive>
  </router-view>
</template>

<script setup>

  import {mapGetters, useStore} from "vuex"
  import {computed} from "vue";

  defineOptions({
    name: "RouterPageContainer",
  });

  // 获取本地路由实例
  const store = useStore();

  // 使用mapGetters映射Vuex中的getters
  const { getCacheComponentName } = mapGetters('tagPageModule', ['getCacheComponentName']);

  //计算属性响应式获取缓存组件列表(当标签页被删除默认清空缓存)
  const cacheComponentNameList = computed(() =>getCacheComponentName.call({ $store: store }));

</script>
