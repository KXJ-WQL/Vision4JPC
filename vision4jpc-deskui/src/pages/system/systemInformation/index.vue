<!-- 右侧系统消息 -->
<template>
  <div class="bg-primary" style="width: 460px">
    <div class="messagePage q-pa-sm">
      <!-- 标题部分 -->
      <div class="q-pa-sm flex items-center">
        <q-icon name="email" size="sm" />
        <span class="text-h8 text-weight-bold q-ml-sm">系统消息</span>
        <q-space />
        <!-- 占位符，使按钮靠右对齐 -->
        <q-checkbox
          color="teal"
          size="xs"
          v-if="showDeleteMany"
          label="全选"
          v-model="selectAll"
          @click="checkedAll(selectAll)"
        />
        <q-icon
          name="delete_outline"
          size="sm"
          class="q-mr-sm deleteIcon"
          @click="deleteButton"
        >
          <q-tooltip v-model="showing" v-if="!showDeleteMany">批量删除</q-tooltip>
          </q-icon
        >
        <q-btn size="sm" icon="close" flat round v-close-popup />
      </div>

      <q-scroll-area
        :thumb-style="thumbStyle"
        :bar-style="barStyle"
        style="height: 400px; width: 450px"
        class="q-pb-md"
      >
        <!-- 消息内容部分 -->
        <div class="q-pa-md">
          <!-- 单个消息 -->
          <div
            class="oneMessage q-pa-sm q-mb-sm"
            v-for="(item, index) in messageList"
            :key="item"
            @click="checkDetail(item, index)"
          >
            <q-checkbox
              v-model="selectMessageList[index]"
              color="teal"
              size="xs"
              v-if="showDeleteMany"
              @click="judgeSelectAll"
            />
            <div>
              <q-badge
                floating
                color="red"
                rounded
                class="badge"
                v-if="item.state == 1"
              />
              <div class="row justify-between">
                <div>
                  <q-icon name="notifications"></q-icon>
                  <span>{{ item.type }}</span>
                </div>
                <span class="text-grey text-caption">{{
                  item.updatetime
                }}</span>
              </div>
              <div class="messageContent">
                <span class="text-grey-7 text-caption">{{ item.content }}</span>
              </div>
            </div>
          </div>
        </div>
      </q-scroll-area>
    </div>
  </div>

  <!-- 查看详情弹出框 -->
  <q-dialog v-model="showMessageDetail">
    <div class="q-pa-md bg-primary" style="width: 400px; height: 300px">
      <!-- 标题部分 -->
      <div class="q-pa-sm flex items-center">
        <q-icon name="notifications" size="sm" />
        <span class="text-h8 text-weight-bold q-ml-sm">{{
          detialMessage.type
        }}</span>
        <q-space />
        <!-- 占位符，使按钮靠右对齐 -->
        <q-icon
          name="delete_outline"
          size="sm"
          class="q-mr-sm deleteIcon"
          @click="deleteCurrentMessage"
        ></q-icon>
        <q-btn size="sm" icon="close" flat round v-close-popup />
      </div>

      <!-- 内容部分 -->
      <div style="height: 200px">
        <span>{{ detialMessage.content }}</span>
      </div>

      <!-- 时间部分 -->
      <div class="text-grey row justify-end">
        <span>
          {{ detialMessage.updatetime }}
        </span>
      </div>
    </div>
  </q-dialog>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { successNotift } from "utils/notiftUtil";
import { systemConfirmationDialog } from "utils/dialogUtil";

// 显示消息具体内容弹出框
const showMessageDetail = ref(false);

// 消息列表
const messageList = ref([
  {
    state: 1,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 1,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 1,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 1,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 0,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 0,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 1,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 0,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
  {
    state: 1,
    type: "警告消息",
    content:
      "出现问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题问题",
    updatetime: "2024-12-2 12:33",
  },
]);

// 是否开启批量删除
const showDeleteMany = ref(false);

// 选择的消息
const selectMessageList = ref([]);

// 查看详情消息
const detialMessage = ref();

// 全选按键
const selectAll = ref(false);

// 查看该条消息详情
const checkDetail = (data, index) => {
  if (showDeleteMany.value) {
    // 点击每一项多选框反选
    selectMessageList.value[index] = !selectMessageList.value[index];
  } else {
    // 展示弹出框
    showMessageDetail.value = true;

    // 查看该条消息
    detialMessage.value = data;
  }
};

// 点击列表删除按键
const deleteButton = () => {
  if (showDeleteMany.value) {
    // 若多选框已经展示
    if (selectMessageList.value.every((item) => item == false)) {
      // 未选择再次点击，关闭多选框
      showDeleteMany.value = false;
    } else {
      //选择消息后，再次点击删除，系统对话框确认
      systemConfirmationDialog("信息删除", "sureDelete", () => {
        // 多选框隐藏
        showDeleteMany.value = false;
        // 成功提示信息
        successNotift("clearSuccessful");
      });
    }
  } else {
    // 若多选框未展示
    showDeleteMany.value = true;
  }
};

// 单个消息删除
const deleteCurrentMessage = () => {
  //选择消息后，再次点击删除，系统对话框确认
  systemConfirmationDialog("信息删除", "sureDelete", () => {
    // 提示信息
    showMessageDetail.value = false;
  });
};

//集中控制全选状态
const checkedAll = (selectAllItems) => {
  // 全选状态改变
  selectAllItems = !selectAllItems;
  // 每个消息前多选框状态更改
  selectMessageList.value = messageList.value.map((item) => !selectAllItems);
};

//单个选中判断全选按键
const judgeSelectAll = () => {
  // 判断是否选中的列表中为true的个数，当true个数等于渲染列表的长度时将全选按键设为true
  selectAll.value =
    selectMessageList.value.filter((item) => item == true).length ==
    messageList.value.length
      ? true
      : selectMessageList.value;
};

onMounted(() => {
  selectMessageList.value = messageList.value.map((item) => false);
});
</script>

<style lang="scss" scoped>
.oneMessage {
  border: 1px solid gainsboro;
  border-radius: 5px;
  position: relative;
  display: flex;
  justify-content: left;
  align-items: center;
  &:hover {
    cursor: pointer;
    background-color: gainsboro;
  }
  .badge {
    position: absolute;
    top: -3;
    right: -3;
  }
}

.deleteIcon {
  &:hover {
    cursor: pointer;
    color: red;
  }
}
.messageContent {
  width: 360px;
  /*1. 先强制一行内显示文本*/
  white-space: nowrap;
  /*2. 超出的部分隐藏*/
  overflow: hidden;
  /*3. 文字用省略号替代超出的部分*/
  text-overflow: ellipsis;
}
</style>
