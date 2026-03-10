<template>
  <!--左侧抽屉-->
  <q-drawer
    :width="drawerStyles.leftDrawWidth"
    :breakpoint="drawerStyles.leftDrawBreakPoint"
    class="bg-primary shadow-7"
    show-if-above
    bordered
  >
    <!--滚动容器区域-->
    <q-scroll-area :content-style="{ height: '100%' }" class="fit">
      <!--路由列表主div容器-->
      <div :class="drawerStyles.containerClass">
        <!--主路由列表主div容器-->
        <div :class="[drawerStyles.mainRouteListClass]">
          <!--主路由列表-->
          <q-list class="full-height">
            <!--遍历主路由导航信息-->
            <template v-for="(menuRoute, index) in menuRoutesList" :key="index">
              <RouteLinkList
                :menuRoute="menuRoute"
                :activeLabel="activeItem"
                @update:activeLabel="switchMainRouter"
              />
              <!--是否存在分割线-->
              <q-separator
                :key="'sep' + index"
                v-if="shouldShowSeparator(menuRoute.meta.isSeparator)"
              />
            </template>
          </q-list>
        </div>

        <!--子路由列表div容器-->
        <div
          :class="drawerStyles.subRouteListClass"
          v-if="drawerStyles.subRouteVisible"
        >
          <transition
            appear
            enter-active-class="animated lightSpeedInRight"
            leave-active-class="animated lightSpeedOutRight"
          >
            <q-list>
              <!--遍历子路由导航信息-->
              <template
                v-for="(menuRoute, index) in subMenuRoutesList"
                :key="index"
              >
                <RouteSubLinkList
                  :menuRoute="menuRoute"
                  :activeLabel="activesubItem"
                  @update:activeLabel="switchSubRouter"
                />
                <q-separator
                  :key="'sep' + index"
                  v-if="shouldShowSeparator(menuRoute.meta.isSeparator)"
                />
              </template>
            </q-list>
          </transition>
        </div>
      </div>
    </q-scroll-area>
  </q-drawer>
</template>

<script setup>
import { ref, reactive, computed, onMounted, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { inject } from "vue";

import RouteLinkList from "layoutcomponents/routeLinkList/index.vue";
import RouteSubLinkList from "layoutcomponents/routeSubLinkList/index.vue";
import {
  filterTypeCommonPages,
  findChildrenByName,
  updateDrawerStyles,
  activeHomeTag,
  activeAndAddTab,
  clearAllTab,
} from "encapsulation/navigationListLayout";

defineOptions({
  name: "LeftSideBarLayout",
});

//获取本地路由对象
const router = useRouter();

//获取vuex状态管理对象
const store = useStore();

//获取bus全局事件总线
const bus = inject("bus");

//获取过滤后的主路由(layout)菜单列表信息
const menuRoutesList = ref([]);

//响应式主路由下子路由列表信息
let subMenuRoutesList = ref([]);

//当前默认激活的主路由导航栏
const activeItem = ref("HomePage");

//当前点击激活的子路由导航栏
const activesubItem = ref("");

//应用启动页面挂载前
onBeforeMount(() => {
  //获取导航栏菜单路由信息
  fetchMenuRoutesList();

  //绑定update-navigation全局事件(刷新导航栏菜单)
  bus.on("update-navigation", () => {fetchUpdateNavigationMenu();});
});

//应用挂载成功时默认激活主页
onMounted(() => {
  //默认激活首页
  activeHomeTag(store);
});

// 响应式变量集合，用于动态调整样式
const drawerStyles = reactive({
  leftDrawWidth: 50, //抽屉的宽度
  leftDrawBreakPoint: 70, //屏幕宽度的断点值
  containerClass: "", //路由容器class
  mainRouteListClass: "", //主路由列表class
  subRouteListClass: "", //子路由列表class
  subRouteVisible: false, //是否显示子路由容器列表
});

//主路由导航栏切换方法
const switchMainRouter = (event) => {

  //激活选择导航栏
  activeItem.value = event;

  //寻找该导航栏对应的子路由信息
  subMenuRoutesList.value = findChildrenByName(
    menuRoutesList.value,
    activeItem.value
  );

  //假如主路由导航没有子路由添加或激活标签页
  subMenuRoutesList.value == null
    ? activeAndAddTab(
        store,
        event,
        menuRoutesList.value.find((tabs) => tabs.name === event)
      )
    : null;

  //更新样式
  updateDrawerStyles(drawerStyles, subMenuRoutesList.value);

  //取消激活的子导航栏
  activesubItem.value = "";
};

//子路由导航栏切换方法
const switchSubRouter = (subRouteList) => {

  //激活子路由导航栏
  activesubItem.value = subRouteList.name;

  //添加或激活标签页
  activeAndAddTab(store, subRouteList.name, subRouteList);

};

//获取菜单路由信息
const fetchMenuRoutesList = () => {
  menuRoutesList.value = filterTypeCommonPages(
    router.getRoutes().find((route) => route.name === "layout")?.children || []
  );
};

//菜单与子菜单项是否显示分割线(状态值 ("1" 表示显示，"2" 表示不显示))
const shouldShowSeparator = (isSeparator) => {
  return isSeparator === "1";
};

//功能模块修改，更新导航栏页面
const fetchUpdateNavigationMenu = () => {

  //清空所有tag标签页
  clearAllTab(store);

  //子导航栏隐藏
  updateDrawerStyles(drawerStyles, false);

  //定位到首页
  activeItem.value = "homePage";

  //重新获取导航栏数据进行渲染
  fetchMenuRoutesList();

  //默认激活首页
  activeHomeTag(store);
};
</script>
