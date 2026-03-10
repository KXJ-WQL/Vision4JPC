<template>
  <q-card flat bordered>
    <q-card-section horizontal>
      <q-card-section class="q-pt-xs">
        <div class="text-overline">
          <q-icon name="help" size="xs"></q-icon>
          {{$t('about')}}
        </div>

        <div class="text-h6 q-mt-sm q-mb-xs">
          {{$t('applicationName')}}
          <q-badge class="bg-positive text-primary text-subtitle2" align="middle">V1.0.0-Beta</q-badge>
        </div>

        <div class="text-caption text-grey text-weight-regular">
          {{$t('description')}}
        </div>

        <div class="q-mt-lg text-overline text-weight-medium">
          {{$t('copyright')}}
        </div>
      </q-card-section>

      <q-card-section class="col-4 flex flex-center">
        <q-img
          class="rounded-borders"
          src="~/assets/icons/favicon.png"
        />
      </q-card-section>
    </q-card-section>

    <q-separator />

    <q-card-actions align="right" class="q-pa-none">
      <q-btn no-caps flat @click="clockDialog">
        {{$t('close')}}
      </q-btn>
    </q-card-actions>
  </q-card>
</template>

<script setup>

  defineOptions({
    name: "aboutCard",
  })

  // 定义自定义事件,用于关闭简介弹出框
  const emits = defineEmits(["update:isAbout"]);

  //关闭弹出框
  const clockDialog = () => {
    emits("update:isAbout", false)
  }

</script>

