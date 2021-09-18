<template>
  <div class="container max-w-750px">
    <SideBar :show="show" @closeSideBar="show = false" class="fixed top-0 z-40 h-full" />
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <div>
          <img
            src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjQgNmgtMjR2LTRoMjR2NHptMCA0aC0yNHY0aDI0di00em0wIDhoLTI0djRoMjR2LTR6Ii8+PC9zdmc+"
            @click="show = !show"
            class="cursor-pointer"
          />
        </div>
        <span class="text-4xl font-extrabold text-white">아이폰 프로 512g</span>
      </div>
      <div class="grid grid-rows-2 bg-white py-2 border-b-2 border-gray-300">
        <div class="col-span-2 text-right">
          <span class="col-span-2 pr-4">평균 가격 : N 원</span>
          <span class="pr-4">총 {{ ProdList.length }}건</span>
        </div>
      </div>
    </div>
    <div class="text-left">
      <div class="flex flex-col">
        <ProdBox 
          v-for="prod in ProdList" 
          :key="prod.pid" 
          :product="prod"
        />

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
// import InfiniteLoading from "infinite-loading-vue3-ts";
import ProdBox from "@/components/ProdItem.vue";
import SideBar from "@/components/SideBar.vue";

export default defineComponent({
  name: "Home",
  components: {
    ProdBox,
    // InfiniteLoading,
    SideBar,
  },

  setup() {
    const router = useRouter();
    const store = useStore();
    const show = ref(false)
    // const infiniteHandler = function ($state) {};

    var items = ref([]);
    var ProdList = ref([
      {
        pid: "1",
        title: "당근당근당근당근당근당근당근당근당근당근당근당근",
        market: "daangn",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
        url: "https://www.daangn.com/articles/279930062"
      },
      {
        pid: "1",
        title: "중고나라앱앱앱앱중고나라앱앱앱앱중고나라앱앱앱앱",
        market: "joonnaApp",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
        url: "https://m.joongna.com/product-detail/27889203"
      },
      {
        pid: "1",
        title: "중고나라카페카페중고나라카페카페중고나라카페카페중고나라카페카페",
        market: "joonnaCafe",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
        url: "clubid=10050146&menuid=424&boardtype=C&page=12&articleid=866763290&referrerAllArticles=false"
      },
      {
        pid: "1",
        title: "번개장터번개장터번개장터번개장터",
        market: "thunder",
        price: "300000",
        createdate: "2021-09-09",
        img: "@/assets/image/iphone12pro.jfif",
        url: "https://api.bunjang.co.kr/api/1/product/163817849/detail_info.json?version=4"
      },
    ]);

    // for (var i: number = 0; i < 5; i++) {
    //   items[i] = ProdList[i];
    // }

    return { 
      ProdList, 
      items,
      show,    
    };
  },
});
</script>

<style></style>

        // <infinite-loading @infinite="infiniteHandler">
        //   <template #spinner> // loading here </template>
        //   <template #no-more> // text no more </template>
        //   <template #no-results> // text no results </template>
        // </infinite-loading>

