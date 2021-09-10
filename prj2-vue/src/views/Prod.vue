<template>
  <div class="container max-w-750px">
    <div class="sticky top-0">
      <div class="flex-column items-center bg-purple-700 py-4">
        <span>
          <SideBar />
        </span>
        <span class="text-4xl font-extrabold text-white">아이폰 프로 512g</span>
      </div>
      <div class="grid grid-rows-2 bg-white py-2 border-b-2 border-gray-300">
        <div class="col-span-2 text-right">평균 가격 : N 원</div>
        <div class="col-span-2 text-left">
          <p>총 {{ ProdList.length }}건</p>
        </div>
      </div>
    </div>
    <div class="text-left">
      <div class="flex flex-col">
        <ProdBox v-for="prod in ProdList" v-bind:key="prod.pid" :product="prod" />
        <infinite-loading @infinite="infiniteHandler">
          <template #spinner> // loading here </template>
          <template #no-more> // text no more </template>
          <template #no-results> // text no results </template>
        </infinite-loading>
      </div>
    </div>
    <div class="sticky bottom-0">
      <div class="flex-column items-center bg-yellow-200 p-4">
        <p class="text-2xl">품목 시세 분석</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import InfiniteLoading from "infinite-loading-vue3-ts";
import ProdBox from "@/components/ProdBox.vue";
import SideBar from "@/components/SideBar.vue";

export default defineComponent({
  name: "Home",
  components: {
    ProdBox,
    InfiniteLoading,
    SideBar,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

    // const infiniteHandler = function ($state) {};

    var items = ref([]);
    var ProdList = ref([
      {
        pid: "1",
        title: "아이폰 팝니다잉",
        price: "300000원",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "2",
        title: "아이폰 프로 팝니다잉",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "3",
        title: "아이폰 노란거 팝니다잉",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "4",
        title: "아이폰 PRO 팝니다잉",
        price: "300000",
        createdate: "2015",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "1",
        title: "아이폰 팝니다잉",
        price: "300000원",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "2",
        title: "아이폰 프로 팝니다잉",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "3",
        title: "아이폰 노란거 팝니다잉",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
      },
      {
        pid: "4",
        title: "아이폰 PRO 팝니다잉",
        price: "300000",
        createdate: "2015",
        img: "@/assets/image/iphone12pro.jfif",
      },
    ]);

    // for (var i: number = 0; i < 5; i++) {
    //   items[i] = ProdList[i];
    // }

    return { ProdList, items };
  },
});
</script>

<style></style>
