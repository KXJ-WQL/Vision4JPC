<template>
  <q-table
    style="height: 78vh"
    class="my-sticky-virtscroll-table"
    :rows="rows"
    :columns="columns"
    virtual-scroll
    row-key="name"
    separator="cell"
    v-model:pagination="pagination"
    :rows-per-page-options="[0]"
    flat
  >
    <!-- 顶部插槽 -->
    <template v-slot:top>
      <!--table顶部标题和图标-->
      <q-icon name="token" size="md" />
      <span class="text-h6 q-ml-sm">{{ $t("navigationMenu") }}</span>
    </template>

    <!--表头-->
    <template v-slot:header="props">
      <q-tr :props="props">
        <q-th auto-width />
        <!-- 动态生成列头 -->
        <q-th v-for="col in props.cols" :key="col.name" :props="props">
          <!-- 可排序的列 -->
          <q-btn class="text-subtitle2 text-bold" flat dense v-if="col.sortable">
            {{ col.label }}
          </q-btn>
          <!-- 不可排序的列 -->
          <span class="text-subtitle2 text-bold" v-else>
            {{ col.label }}
          </span>
        </q-th>
      </q-tr>
    </template>

    <!-- 加载状态插槽 -->
    <template v-slot:loading>
      <q-inner-loading showing>
        <q-spinner-gears size="4em" />
      </q-inner-loading>
    </template>

    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td auto-width>
          <q-btn size="sm" round dense @click="props.expand = !props.expand" :icon="props.expand ? 'remove' : 'add'" />
        </q-td>
        <!-- 菜单名称 -->
        <q-td key="title" :props="props">
          <q-badge outline :label="$t(props.row.title)" class="text-caption text-bold" color="teal"/>
        </q-td>

        <!-- 菜单图标 -->
        <q-td key="icon" :props="props">
          <q-icon :name="props.row.icon" :size="props.row.iconSize"/>
        </q-td>

        <!-- 路由名称 -->
        <q-td key="name" :props="props">
          <q-badge class="text-caption text-bold" color="accent">
            <q-icon name="token" />
            {{ props.row.name }}
          </q-badge>
        </q-td>

        <!-- 组件地址 -->
        <q-td key="component" :props="props">
          <span class="text-caption text-bold">{{ getComponent(props.row.component) }}</span>
        </q-td>

        <!-- 菜单类型 -->
        <q-td key="type" :props="props">
          <q-badge class="text-caption text-bold" color="info">
            {{ getNavigationType(props.row.type)  }}
          </q-badge>
        </q-td>

        <!-- 父菜单 -->
        <q-td key="parentId" :props="props">
          <q-badge outline class="text-caption text-bold" color="positive">
            {{ $t(foundNavigation(props.row.parentId) ?.title ?? "topNavigationMenu") }}
          </q-badge>
        </q-td>

        <!-- 动画效果 -->
        <q-td key="animation" :props="props">
          <q-icon :class="['animated', props.row.animation, 'infinite']" size="sm" :name="props.row.icon"/>
        </q-td>

        <!-- 分割线 -->
        <q-td key="isSeparator" :props="props">
          <q-toggle
            class="text-caption text-bold"
            v-model="props.row.isSeparator"
            checked-icon="check"
            color="green"
            unchecked-icon="clear"
            :false-value="'2'"
            :label="$t(getShowHide(props.row.isSeparator))"
            :true-value="'1'"
            @update:model-value="switchShowAndHide(toRaw(props.row))"
          />
        </q-td>

        <!-- 路由缓存 -->
        <q-td key="cacheComponentName" :props="props">
          <q-badge class="text-caption text-bold" color="pink-7">
            {{ $t(getRouteCache(props.row.cacheComponentName)) }}
          </q-badge>
        </q-td>

        <!-- 所属模块 -->
        <q-td key="moduleId" :props="props">
          <q-badge outline class="text-caption text-bold" color="warning">
            {{ foundModule(props.row.moduleId) ?.name ?? ""}}
          </q-badge>
        </q-td>

        <!-- 操作列 -->
        <q-td key="operation" :props="props">
          <div class="q-gutter-sm row justify-center">
            <q-btn size="0.9em" icon="border_color" :label="$t('edit')" @click="alterNavigationMenu(props.row)"/>
            <q-btn size="0.9em" icon="delete" :label="$t('delete')" @click="deleteNavigationMenu(props.row)"/>
          </div>
        </q-td>
      </q-tr>

      <q-tr v-show="props.expand" :props="props">
        <q-td colspan="100%">
          <table class="custom-table text-caption text-weight-medium" v-if="props.row.children && props.row.children.length">
            <tbody>
            <tr v-for="child in props.row.children" :key="child.navigationId">
              <td> {{ $t(child.title) }} </td>
              <td> <q-icon :name="child.icon" :size="child.iconSize" /></td>
              <td> <q-icon name="token" /> {{ child.name }} </td>
              <td> {{ getComponent(child.component) }}</td>
              <td> {{ getNavigationType(child.type) }}</td>
              <td> {{ $t(foundNavigation(child.parentId) ?.title ?? "topNavigationMenu")  }} </td>
              <td> <q-icon :class="['animated', child.animation, 'infinite']" size="sm" :name="child.icon" /> </td>
              <td>
                <q-toggle
                  v-model="child.isSeparator"
                  checked-icon="check"
                  color="green"
                  unchecked-icon="clear"
                  :false-value="'2'"
                  :true-value="'1'"
                  :label="$t(getShowHide(child.isSeparator))"
                  @update:model-value="switchShowAndHide(toRaw(child))"
                />
              </td>
              <td> {{ $t(getRouteCache(child.cacheComponentName)) }} </td>
              <td> {{ foundModule(child.moduleId) ?.name ?? "" }} </td>
              <td class="q-gutter-sm">
                <q-btn size="0.9em" icon="border_color" :label="$t('edit')" @click="alterNavigationMenu(child)"/>
                <q-btn size="0.9em" icon="delete" :label="$t('delete')" @click="deleteNavigationMenu(child)"/>
              </td>
            </tr>
            </tbody>
          </table>
        </q-td>
      </q-tr>
    </template>
  </q-table>
</template>

<script setup>
import { ref, toRaw } from "vue";

//获取自定义事件
const emits = defineEmits([
  "update:alterNavigationMenuMethod",
  "update:editNavigationSubmit",
  "delete:deleteNavigationMenuMethod"
]);

// 声明接收父组件的数据类型
const props = defineProps({
  // 表格数据项
  rows: {
    type: Array,
    required: true,
  },
  // 模块数据项
  moduleDatas: {
    type: Array,
    required: true,
  },
  // 表格列头部数据格式
  columns: {
    type: Array,
    required: true,
  },
  // 是否再加载状态
  loading: {
    type: Boolean,
    required: true,
  },
});

// 分页信息（禁用分页 - 采集器其实不会有多少使用滚动即可）
const pagination = ref({ rowsPerPage: 0 });

// 菜单类型的标签
const getNavigationType = (type) => type == 1 ? "系统菜单" : "普通页面"

// 组件地址的标签
const getComponent = (component) =>  (component == null || component == '') ? "无" : (component.length > 8 ? component.substring(0, 8) + '...' : component)

// 分割线的标签
const getShowHide = (isSeparator) => isSeparator == 1 ? "show" : "hide"

// 路由缓存的标签
const getRouteCache = (routeCache) => routeCache != null ? "open" : "close"

// 工具模块ID寻找moduleDatas指定对象
const foundModule = (moduleID) => props.moduleDatas.find(module => module.moduleId == moduleID)

// 在菜单表格数据中通过ID寻找父菜单对象
const foundNavigation = (parentId) => props.rows.find(nav => nav.navigationId == parentId);

// 分割线显示-隐藏切换时触发方法
const switchShowAndHide = (row) => {
  //触发父组件修改方法
  emits("update:editNavigationSubmit", row);
};

//编辑方法
const alterNavigationMenu = (row) => {
  //触发父组件修改弹出窗方法
  emits("update:alterNavigationMenuMethod", row);
};

//删除方法
const deleteNavigationMenu = (row) => {
  emits("delete:deleteNavigationMenuMethod", row);
}

</script>

<style scoped lang="scss">
.my-sticky-virtscroll-table {
  // 表头和底部的固定样式
  .q-table__top,
  .q-table__bottom,
  thead tr:first-child th,
  thead tr th {
    position: sticky;
    z-index: 1;
    background-color: var(--q-primary);
  }

  // 表头固定位置
  thead tr:last-child th {
    top: 48px;
  }

  thead tr:first-child th {
    top: 0;
  }
}

.custom-table {
  width: 100%;
  border-collapse: separate;
  border-spacing: 0 12px; // 行间距，卡片间距
  text-align: center;
  font-family: "Segoe UI", Roboto, "Helvetica Neue", sans-serif;
  // 单元格卡片化
  td {
    padding: 12px 16px;
    border-radius: 8px; // 圆角卡片
    border: 1px solid rgba(0, 0, 0, 0.08); // 浅灰边框
    transition: transform 0.2s ease, box-shadow 0.2s ease;
  }

  // 悬浮浮动效果
  tr:hover {
    td {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    }
  }

  // 去掉最后一行边线
  tr:last-child {
    td {
      border-bottom: none;
    }
  }

  // 嵌套子表格也应用卡片风格
  table {
    width: 100%;
    border-spacing: 0 8px;

    td {
      padding: 8px 12px;
      border-radius: 6px;
      border: 1px solid rgba(0, 0, 0, 0.06);
      transition: transform 0.2s ease, box-shadow 0.2s ease;
    }

    tr:hover {
      td {
        transform: translateY(-1px);
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.06);
      }
    }

    tr:last-child {
      td {
        border-bottom: none;
      }
    }
  }
}
</style>
