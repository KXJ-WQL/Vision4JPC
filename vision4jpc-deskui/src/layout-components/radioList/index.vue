 <template>
  <q-list>
    <!--q-item点击边缘不会触发radio，点击radio不会触发handleSelect，
      1.所以即设置了@click点击事件(注:手动设置selectedItem.value会触发watch)
      2.也监视了selectedItem值-->
    <q-item
      dense
      v-for="(item, index) in items"
      :key="index"
      clickable
      @click="handleSelect(item.value)"
      v-close-popup
    >
      <q-radio
        size="xs"
        v-model="selectedItem"
        :val="item.value"
        :label="$t(item.value)"
        :color="item.color"
        v-close-popup
      />
    </q-item>
  </q-list>
</template>

<script setup>
import { toRefs, ref, watch } from "vue";

defineOptions({name: "TopRadioList",});

//声明接收父组件的数据类型
const props = defineProps({
  //数据项
  items: {
    type: Array,
    require: true,
  },
  //选中的数据项
  selected: {
    type: String,
    require: true,
  },
});

//获取自定义事件
const onselect = defineEmits(["onSelect"]);

//提取prope中的selected响应式对象
const { selected } = toRefs(props);
const selectedItem = ref(selected.value);

//监视selectedItem
watch(selectedItem, (newval) => {
  //触发自定义事件
  onselect("onSelect", newval);
});

//回调方法
const handleSelect = (newval) => {
  //更新值(会触发监听方法)
  selectedItem.value = newval;
};
</script>
