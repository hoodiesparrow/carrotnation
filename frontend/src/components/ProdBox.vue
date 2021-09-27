<template>
  <div
    class="
      border-2 border-gray-300 border-solid
      my-1
      p-3
      transition
      hover:bg-purple-100 hover:text-black
    "
    @click="redir"
  >
    <div class="grid grid-rows-3 grid-flow-col gap-4 text-left">
      <div class="row-span-3 ...">
        <img :src="product.img" style="height: 50px" />
      </div>
      <div class="col-span-2 text-2xl">{{ product.title }}</div>
      <div class="col-span-2 font-semibold">{{ product.price }}</div>
      <div class="col-span-2">{{ product.createdate }}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
// import HomeItemList from './HomeItemList.vue'

export default defineComponent({
  name: "ProdBox",
  props: ["product"],
  setup(props) {
    const redir = function () {
      location.href = "http://www.naver.com/";
    };
  },
});
</script>
