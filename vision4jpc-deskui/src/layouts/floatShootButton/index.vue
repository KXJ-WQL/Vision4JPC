<template>
  <!-- 实弹射击固定浮动按钮位置 -->
  <q-page-sticky v-if="shootModule" position="bottom-right" :offset="shootFabPos">
    <q-fab
      :direction="shootControlShowModule ?'left' :'up'"
      padding="md"
      :color="shootingModeColorShow()"
      square
      glossy
    >
      <!-- 浮动按钮 展开/未展开时的标签 -->
      <template v-slot:label="{ opened }">
        <div :class="{ 'example-fab-animate--hover': opened !== true }">
          <span>  {{ shootingModeShow() }} </span>
        </div>
      </template>

      <!-- 浮动按钮展开时的图标 -->
      <template v-slot:active-icon="{ opened }">
        <q-icon
          :class="{ 'example-fab-animate': opened === true }"
          size="sm"
          name="open_with"
        />
      </template>

      <!-- 浮动按钮未展开时的图标 -->
      <template v-slot:icon="{ opened }">
        <q-icon
          :class="{ 'example-fab-animate--hover': opened !== true }"
          size="sm"
          name="open_with"
        />
      </template>

      <!-- 模式选择的按钮 -->
      <q-fab-action
        :label="$t('experienceMode')"
        color="red-8"
        @click="shootExperienceMode"
        icon="filter_tilt_shift"
      />
      <q-fab-action
        :label="$t('trainingMode')"
        color="orange-8"
        @click="shootTrainingMode"
        icon="location_searching"
      />
      <q-fab-action
        :label="$t('competitionMode')"
        color="green-8"
        @click="shootCompetitionMode"
        icon="my_location"
      />
    </q-fab>
  </q-page-sticky>

  <!-- 射击统控固定浮动按钮位置 -->
  <q-page-sticky v-if="shootControlShowModule" position="bottom-right" :offset="shootControlFabPos">
    <q-fab
      :direction="shootModule ?'left' :'up'"
      padding="md"
      color="blue-9"
      square
      glossy
    >
      <!-- 浮动按钮 展开/未展开时的标签 -->
      <template v-slot:label="{ opened }">
        <div :class="{ 'example-fab-animate--hover': opened !== true }">
          <span> {{ $t("unifiedShootingControl") }} </span>
        </div>
      </template>

      <!-- 浮动按钮展开时的图标 -->
      <template v-slot:active-icon="{ opened }">
        <q-icon
          :class="{ 'example-fab-animate': opened === true }"
          size="sm"
          name="brightness_5"
        />
      </template>

      <!-- 浮动按钮未展开时的图标 -->
      <template v-slot:icon="{ opened }">
        <q-icon
          :class="{ 'example-fab-animate--hover': opened !== true }"
          size="sm"
          name="brightness_5"
        />
      </template>

      <!-- 射击统控子选择的按钮 -->
      <q-fab-action
        :label="$t('unifiedDisplayAndControl')"
        color="purple-6"
        @click="shootDisplayControl"
        icon="brightness_7"
      />
    </q-fab>
  </q-page-sticky>

<!--  &lt;!&ndash; 引入shootTrainingMode射击训练模式组件 &ndash;&gt;-->
<!--  <ShootTrainingDialog :shootingMode="shootingMode" v-model="trainingModeDialog" />-->

<!--  &lt;!&ndash; 引入ShootCompetitionDialog射击比赛模式组件 &ndash;&gt;-->
<!--  <ShootCompetitionDialog :shootingMode="shootingMode" v-model="competitionModeDialog" />-->

<!--  &lt;!&ndash; 引入ShootExperienceDialog射击体验模式组件 &ndash;&gt;-->
<!--  <ShootExperienceDialog v-model="experienceModeDialog" />-->

<!--  &lt;!&ndash; 引入ShootDisplayControl射击统一显示控制组件 &ndash;&gt;-->
<!--  <ShootDisplayControl v-model="shootDisplayControlDialog" />-->
</template>

<script setup>
import {ref, watch} from "vue";
// import ShootTrainingDialog from "pages/shoot/shootTrainingMode/index.vue";
// import ShootCompetitionDialog from "pages/shoot/shootCompetitionMode/index.vue";
// import ShootExperienceDialog from "pages/shoot/shootExperienceMode/index.vue";
// import ShootDisplayControl from "pages/shootEdgeHostControl/shootDisplayControl/index.vue";
import { getValueByKey } from "utils/configStoreUtil";
import { getIsEnabled } from "utils/moduleStoreUtil"
import { useStore} from "vuex"

defineOptions({
  name: "FloatShootButton",
});

// 获取 Vuex 状态管理对象
const store = useStore();

// 实弹射击浮动按钮初始位置
const shootFabPos = ref([20, 20]);

// 射击统控浮动按钮初始位置
const shootControlFabPos = ref([20, 80]);

// 报靶功能模块是否开启
const shootModule =  ref(getIsEnabled(store, "ShootModule"))

// 独立报靶显示端模块是否开启
const shootControlShowModule =  ref(getIsEnabled(store, "ShootControlShowModule"))

// 当前射击模式(实弹射击模式 - 激光模拟射击模式)
const shootingMode =  ref(getValueByKey(store, "shootingMode"))

// 监听moduleStore中的独立报靶显示端模块值变化动态渲染模式
watch(() => getIsEnabled(store, "ShootControlShowModule"), newShootControlShowModule => shootControlShowModule.value = newShootControlShowModule);

// 监听moduleStore中的报靶功能模块值变化动态渲染模式
watch(() => getIsEnabled(store, "ShootModule"), newShootModule => shootModule.value = newShootModule);

// 监听configStore中的值变化动态渲染模式
watch(() => getValueByKey(store, "shootingMode"), newShootingMode => shootingMode.value = newShootingMode);

// 射击模式名称动态显示
const shootingModeShow = ()=> shootingMode.value == 1 ? "实弹射击" : "模拟射击";

// 射击模式不同动态渲染颜色
const shootingModeColorShow = ()=> shootingMode.value == 1 ? "teal-8" : "pink-8";

//训练模式弹窗组件
const trainingModeDialog = ref(false);

//比赛模式弹窗组件
const competitionModeDialog = ref(false);

// 体验模式弹窗组件
const experienceModeDialog = ref(false);

// 射击统一显示控制
const shootDisplayControlDialog = ref(false)

// 训练模式
const shootTrainingMode = () => {
  trainingModeDialog.value = true;
};

// 射击比赛模式
const shootCompetitionMode = () => {
  competitionModeDialog.value = true;
};

// 射击体验模式
const shootExperienceMode = () => {
  experienceModeDialog.value = true;
};

// 射击统一显示控制
const shootDisplayControl = ()=>{
  shootDisplayControlDialog.value = true
}
</script>
