<template>
  <div class="row items-center non-selectable">
    <q-btn-group outline>
      <!--设置菜单-->
      <mainMenu
        :mainMenuName="$t('setting')"
        iconName="settings"
        menuShowAnimation="jump-down"
        menuHideAnimation="jump-up"
        iconAnimation="rotateIn"
      >
        <template #dropdownMenu>
          <!--配色下拉菜单-->
          <dropdownMenu :menuOptionsTitle="$t('theme')" menuOptionsIcon="palette">
            <template #submenuList>
              <topRadioList
                :items="themes"
                :selected="selectedTheme"
                @onSelect="selectTheme"
              ></topRadioList>
            </template>
          </dropdownMenu>
          <q-separator />
          <!--语言下拉菜单-->
          <dropdownMenu :menuOptionsTitle="$t('language')" menuOptionsIcon="g_translate">
            <template #submenuList>
              <topRadioList
                :items="languages"
                :selected="selectedLanguage"
                @onSelect="selectLanguage"
              ></topRadioList>
            </template>
          </dropdownMenu>
          <q-separator />

<!--          <q-item :clickable="true" v-close-popup @click="quit">-->
<!--            <q-item-section></q-item-section>-->
<!--          </q-item>-->
        </template>
      </mainMenu>

      <!--功能模块菜单-->
      <mainMenu
        @menuClick="functionalModuleMethod(true)"
        :mainMenuName="$t('ability')"
        iconName="webhook"
        iconAnimation="rotateIn"
      ></mainMenu>

      <!--功能选项菜单弹出框-->
      <q-dialog v-model="isFunctionalModule" no-backdrop-dismiss>
        <moduleCheckbox></moduleCheckbox>
      </q-dialog>


      <!--关于菜单-->
      <mainMenu
        @menuClick="aboutMethod(true)"
        :mainMenuName="$t('about')"
        iconName="help"
        iconAnimation="rotateIn"
      ></mainMenu>


    <!--关于菜单弹出框-->
    <q-dialog v-model="isAbout">
      <aboutCard
        @update:isAbout="aboutMethod($event)"/>
    </q-dialog>

    </q-btn-group>
  </div>
</template>

<script setup>
  import { ref } from "vue";
  import topRadioList from "layoutcomponents/radioList/index.vue";
  import dropdownMenu from "layoutcomponents/dropdownMenu/index.vue";
  import mainMenu from "layoutcomponents/mainMenu/index.vue";
  import aboutCard from "layoutcomponents/aboutCard/index.vue";
  import moduleCheckbox from "layoutcomponents/moduleCheckbox/index.vue";

  import { languages, createI18nLanguageHelper } from "config/languages";
  import { themes, switchThemesColors } from "config/themes";

  defineOptions({
    name: "TopMenuLayout",
  });

  const { switchLanguage, locale } = createI18nLanguageHelper();
  const selectedTheme = ref("light");
  const selectedLanguage = locale;
  const isAbout = ref(false)
  const isFunctionalModule = ref(false)

  // 处理主题配色切换逻辑
  const selectTheme = (value) => {
    selectedTheme.value = value;
    switchThemesColors(value);
  };

  // 处理语言切换逻辑
  const selectLanguage = (value) => {
   selectedLanguage.value = value;
   switchLanguage(value);
  };

  //关于菜单方法
  const aboutMethod = (isshow) => {
    isAbout.value = isshow;
  };

  //功能模块菜单方法
  const functionalModuleMethod = (isshow) => {
    isFunctionalModule.value = isshow
  };

  const quit = () => {};
</script>
