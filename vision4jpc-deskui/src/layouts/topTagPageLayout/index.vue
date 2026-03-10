<!-- 上方打开标签页 -->
<template>
  <q-page-sticky expand position="top">
    <!-- 定义一个q-tabs组件，用于显示标签页 -->
    <q-tabs
      v-model="activeTab"
      align="left"
      class="full-width wql-tag-bg-color wql-tag-text-color shadow-5"
      inline-label
      dense
      no-caps
    >
      <!--固定主页标签页(不可删除)-->
      <q-tab name="HomePage" icon="home" class="q-pl-xs q-pr-xs" />

      <!-- 循环显示每个标签页 -->
      <q-tab
        v-for="tab in tabs"
        :key="tab.name"
        :name="tab.name"
        :icon="tab.meta.icon"
        :label="$t(tab.meta.title)"
        class="q-pl-xs q-pr-xs"
      >
        <!-- 关闭标签页按钮 -->
        <q-btn
          class="q-ml-sm"
          icon="close"
          size="sm"
          @click.stop="handleRemoveTab(tab.name)"
          flat
          round
        />

        <!-- 弹出 -->
        <q-menu context-menu :touch-position="false" >
          <q-list dense style="min-width: 100px;font-size: small;">
            <!-- 关闭当前标签页 -->
            <q-item
              clickable
              v-close-popup
              @click.stop="handleRemoveTab(tab.name)"

            >
              <q-item-section >{{ $t("closeTheCurrentTab") }}</q-item-section>
            </q-item>

            <!-- 关闭其他标签页 -->
            <q-item
              clickable
              v-close-popup
              @click="handleRemoveOtherTab(tab.name)"
            >
              <q-item-section>{{ $t("closeOtherTabs") }}</q-item-section>
            </q-item>

            <!-- 关闭所有标签页 -->
            <q-item clickable v-close-popup @click="handleRemoveAllTab">
              <q-item-section>{{ $t("closeAllTabs") }}</q-item-section>
            </q-item>
            <q-separator />
          </q-list>
        </q-menu>

        <q-separator vertical inset />
        <!-- 垂直分隔符 -->
      </q-tab>
    </q-tabs>
  </q-page-sticky>
</template>

<script setup>
import { computed, watch, ref } from "vue";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { mapGetters, mapActions } from "vuex";
import { handleRouteChange } from "utils/routerUtil";

defineOptions({
  name: "TopPageNavigationLayout", // 定义组件名称
});

//右键弹出绑定
const showMenu = ref(false);

//获取vuex Store状态管理对象
const store = useStore();

// 获取本地路由实例
const router = useRouter();

// 使用mapGetters映射Vuex中的getters
const { getTabs, getActiveTab } = mapGetters("tagPageModule", [
  "getTabs",
  "getActiveTab",
]);

// 使用mapActions映射Vuex中的actions
const { setActiveTab, removeTab } = mapActions("tagPageModule", [
  "setActiveTab",
  "removeTab",
]);

// 使用计算属性获取所有的tab标签页列表信息
const tabs = computed(() => getTabs.call({ $store: store }));

// 使用计算属性来获取和设置当前激活的标签页
const activeTab = computed({
  get: () => getActiveTab.call({ $store: store }), // 获取当前激活的标签页
  set: (value) => setActiveTab.call({ $store: store }, value), // 设置激活的标签页
});

// 监视activeTab值是否发生变化
watch(activeTab, (newActiveTab, oldActiveTab) => {
  //路由跳转处理
  handleRouteChange(router, newActiveTab);
});

// 定义删除标签页的方法
const handleRemoveTab = (tabName) => {
  // 调用Vuex action 删除指定的标签页
  removeTab.call({ $store: store }, tabName);
};

// 删除其他标签的方法
const handleRemoveOtherTab = (tabName) => {
  tabs.value
    .filter((tabs) => tabs.name != tabName)
    .forEach((item) => {
      // 调用Vuex action 删除指定的标签页
      removeTab.call({ $store: store }, item.name);
    });
};

// 关闭所有标签页
const handleRemoveAllTab = () => {
  tabs.value.forEach((item) => removeTab.call({ $store: store }, item.name));
};
</script>
