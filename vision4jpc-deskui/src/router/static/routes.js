const staticRoutes = [
  {
    //根路由
    path: "/",
    //路由名称
    name: "layout",
    //导入主布局容器组件
    component: () => import("layouts/mainLayout/index.vue"),
    //直接子路由
    children: [
      //首页路由
      {
        path: "", //默认跳转到首页(该系统不需要权限所以不需要登录)
        name: "HomePage", //路由名称
        component: () => import("pages/HomePage.vue"), //动态导入相应组件
        //路由元信息
        meta: {
          icon: "cottage", //导航图标名称
          title: "homePage", //路由导航名称(这个名称和I18国际化翻译对应)
          iconSize: "sm", //导航图标大小
          isSeparator: true, //路由导航是否存在分割线
          animation: "swing", //动画效果(使用了Animate.css动画库 -- 指定动画名称即可)
          cacheComponentName: "HomePage", //标签页缓存组件名
        },
      },
    ],
  },

  //异常路由
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/errorPage/ErrorNotFound.vue"),
  },
];

export default staticRoutes;
