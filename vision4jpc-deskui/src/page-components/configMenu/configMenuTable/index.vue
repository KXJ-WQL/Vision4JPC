<!-- 射击位列表 -->
<template>
  <q-table
    style="height: 78vh"
    class="my-sticky-virtscroll-table"
    row-key="configMenuId"
    selection="multiple"
    separator="cell"
    :rows="rows"
    :columns="columns"
    :loading="tableLoading"
    :selected-rows-label="getSelectedString"
    :filter="filter"
    v-model:selected="selected"
    v-model:pagination="pagination"
    :rows-per-page-options="[0]"
    virtual-scroll
    flat
  >
    <!-- 顶部插槽 -->
    <template v-slot:top>
      <!--table顶部标题和图标-->
      <q-icon name="control_camera" size="md" />
      <span class="text-h6 q-ml-sm">{{ $t("configMenu") }}</span>
      <q-space />
      <!--顶部搜索栏-->
      <q-input
        dense
        :label="$t('inputConfigMenuName')"
        color="blue"
        v-model="filter"
      >
        <template v-slot:before>
          <q-icon name="keyboard" />
        </template>
        <template v-slot:append>
          <q-icon name="search" />
        </template>
      </q-input>
    </template>

    <!-- 表头插槽 -->
    <template v-slot:header="props">
      <q-tr :props="props">
        <!-- 多选框 -->
        <q-th auto-width>
          <q-checkbox
            class="text-subtitle2"
            :label="$t('selectAll')"
            color="teal"
            v-model="props.selected"
          />
        </q-th>

        <!-- 动态生成列头 -->
        <q-th v-for="col in props.cols" :key="col.name" :props="props">
          <!-- 可排序的列 -->
          <q-btn
            class="text-subtitle2 text-bold"
            flat
            dense
            v-if="col.sortable"
          >
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

    <!-- 表体插槽 -->
    <template v-slot:body="props">
      <q-tr :props="props">
        <!-- 多选框 -->
        <q-td auto-width>
          <q-checkbox color="cyan" v-model="props.selected" />
        </q-td>

        <!-- 配置菜单名称 -->
        <q-td key="toolTitle" :props="props">
          <q-badge class="text-caption text-bold" color="pink-7">
            <q-icon name="control_camera" />
            {{ $t(props.row.toolTitle) }}
          </q-badge>
        </q-td>

        <!-- 组件路径 -->
        <q-td key="component" :props="props">
          <q-badge outline class="text-caption text-bold" color="cyan-9">
            {{ props.row.component }}
          </q-badge>
        </q-td>

        <!-- 配置菜单图标 -->
        <q-td key="icon" :props="props">
          <q-icon :class="['animated', props.row.animation, 'infinite']" size="sm" :name="props.row.icon" />
        </q-td>

        <!-- 徽标状态 -->
        <q-td key="isBadgeinfo" :props="props">
          <q-badge outline class="text-caption text-bold" color="orange-9">
            {{ $t(getBadgeinfo(props.row.isBadgeinfo)) }}
          </q-badge>
        </q-td>

        <!-- 所属模块 -->
        <q-td key="moduleId" :props="props">
          <q-badge outline class="text-caption text-bold" color="negative">
            {{ foundModule(props.row.moduleId) ?.name ?? ""}}
          </q-badge>
        </q-td>

        <!-- 操作列 -->
        <q-td key="operation" :props="props">
          <div class="q-gutter-sm row justify-center">
            <q-btn
              size="0.9em"
              icon="border_color"
              :label="$t('edit')"
              @click="alterConfigMenu(props.row)"
            />
            <q-btn
              size="0.9em"
              icon="delete"
              :label="$t('delete')"
              @click="deleteConfigMenu(props.row)"
            />
          </div>
        </q-td>
      </q-tr>
    </template>
  </q-table>
</template>

<script setup>
import { ref, toRaw } from "vue";

//获取自定义事件
const emits = defineEmits([
  "update:alterConfigMenuMethod",
  "delete:deleteConfigMenuMethod",
]);

// 声明接收父组件的数据类型
const props = defineProps({
  // 表格数据项
  rows: {
    type: Array,
    required: true,
  },
  //表格列头部数据格式
  columns: {
    type: Array,
    required: true,
  },
  // 模块数据项
  moduleDatas: {
    type: Array,
    required: true,
  },
  //是否再加载状态
  tableLoading: {
    type: Boolean,
    required: true,
  },
});

// 表格中复选框的选中项
const selected = ref([]);
// 表格的过滤条件
const filter = ref("");
// 分页信息（禁用分页 - 射击位其实不会有多少使用滚动即可）
const pagination = ref({ rowsPerPage: 0 });

// 选中行时底部标签文本
const getSelectedString = () => {
  return selected.value.length === 0 ? "" : `${selected.value.length} 个配置菜单 ${selected.value.length > 1 ? "选中" : ""} -- 总共 ${props.rows.length}个配置菜单`;
};

//编辑方法
const alterConfigMenu = (row) => {
  //触发父组件修改弹出窗方法
  emits("update:alterConfigMenuMethod", row);
};

//删除方法
const deleteConfigMenu = (row) => {
  /*复选框选中的元素数量大于1表示批量删除，如何为0表删除删除单条数据
   * selected.value和row传递给后端时必须是普通对象和数组，不能是proxy响应式对象数组，传输时需要做toRaw()普通对象和数组转换
   * 本系统规则：单体删除和批量在后台都使用同一个接口，在数据封装时，单条记录删除也需要将对象变成数组([toRaw(row)])
   * */
  selected.value.length > 1 ? emits("delete:deleteConfigMenuMethod", selected.value.map((item) => toRaw(item))) : emits("delete:deleteConfigMenuMethod", [toRaw(row)]);
};

// 判断徽标开启关闭的标签
const getBadgeinfo = (type) =>  type == 1 ? "open" : "close"

// 功能模块ID寻找moduleDatas指定对象
const foundModule = (moduleID) => props.moduleDatas.find(module => module.moduleId == moduleID)

</script>

<style lang="scss" scoped>
.my-sticky-virtscroll-table {
  /* 表头和底部的固定样式 */
  .q-table__top,
  .q-table__bottom,
  thead tr:first-child th,
  thead tr th {
    position: sticky;
    z-index: 1;
    background-color: var(--q-primary);
  }

  /* 表头固定位置 */
  thead tr:last-child th {
    top: 48px;
  }

  thead tr:first-child th {
    top: 0;
  }
}
</style>
