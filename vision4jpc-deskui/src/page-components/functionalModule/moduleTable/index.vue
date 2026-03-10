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
      <q-icon name="webhook" size="md" />
      <span class="text-h6 q-ml-sm">{{ $t("functionalModule") }}</span>
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
        <!-- 模块名称 -->
        <q-td key="name" :props="props">
          <q-badge outline :label="props.row.name" class="text-caption text-bold" color="teal">
            <q-icon name="webhook" />
          </q-badge>
        </q-td>

        <!-- 模块唯一标识 -->
        <q-td key="key" :props="props">
          <q-badge class="text-caption text-bold" color="accent">
            {{ props.row.key }}
          </q-badge>
        </q-td>

        <!-- 模块图标 -->
        <q-td key="icon" :props="props">
          <q-icon :name="props.row.icon" size="sm"/>
        </q-td>

        <!-- 模块说明 -->
        <q-td key="description" :props="props">
          <span class="text-caption text-bold">{{ props.row.description }}</span>
        </q-td>

        <!-- 父模块 -->
        <q-td key="parentId" :props="props">
          <q-badge outline class="text-caption text-bold" color="positive">
            {{ foundModule(props.row.parentId) ?.name ?? "顶级模块" }}
          </q-badge>
        </q-td>

        <!-- 状态 -->
        <q-td key="isEnabled" :props="props">
          <q-badge class="text-caption text-bold" color="info">
            {{ getModuleStatus(props.row.isEnabled)  }}
          </q-badge>
        </q-td>

        <!-- 操作列 -->
        <q-td key="operation" :props="props">
          <div class="q-gutter-sm row justify-center">
            <q-btn size="0.9em" icon="border_color" :label="$t('edit')" @click="alterModule(props.row)"/>
            <q-btn size="0.9em" icon="delete" :label="$t('delete')" @click="deleteModule(props.row)"/>
          </div>
        </q-td>
      </q-tr>

      <q-tr v-show="props.expand" :props="props">
        <q-td colspan="100%">
          <table class="custom-table text-caption text-weight-medium" v-if="props.row.childrenModule && props.row.childrenModule.length">
            <tbody>
            <tr v-for="child in props.row.childrenModule" :key="child.moduleId">
              <td> {{ child.name }} <q-icon name="webhook" /> </td>
              <td> {{ child.key }} </td>
              <td> <q-icon :name="child.icon" size="sm"/> </td>
              <td> {{ getDescription(child.description) }} </td>
              <td> {{ foundModule(child.parentId) ?.name ?? "topModule"  }} </td>
              <td> {{ getModuleStatus(child.isEnabled) }} </td>
              <td class="q-gutter-sm">
                <q-btn size="0.9em" icon="border_color" :label="$t('edit')" @click="alterModule(child)"/>
                <q-btn size="0.9em" icon="delete" :label="$t('delete')" @click="deleteModule(child)"/>
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
  "update:alterModuleMethod",
  "delete:deleteModuleMethod"
]);

// 声明接收父组件的数据类型
const props = defineProps({
  // 表格数据项
  rows: {
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

// 模块状态标签
const getModuleStatus = (status) => status == 1 ? "开启" : "关闭"

// 获取描述信息
const getDescription = (description) =>  (description == null || description == '') ? "无描述" : (description.length > 20 ? description.substring(0, 20) + '...' : description)

// 在模块数据中通过ID寻找父菜单对象
const foundModule = (parentId) => props.rows.find(mode => mode.moduleId == parentId);

//编辑方法
const alterModule = (row) => {
  //触发父组件修改弹出窗方法
  emits("update:alterModuleMethod", row);
};

//删除方法
const deleteModule = (row) => {
  emits("delete:deleteModuleMethod", row);
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
