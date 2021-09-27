<template>
  <!-- <div class="container max-w-750px flex-column items-center text-center bg-gradient-to-b from-purple-500 via-pink-400 py-20"> -->
  <div class="container max-w-750px bg-gradient-to-b from-gray-100 text-center h-full">
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <div>
          <img
            src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjQgNmgtMjR2LTRoMjR2NHptMCA0aC0yNHY0aDI0di00em0wIDhoLTI0djRoMjR2LTR6Ii8+PC9zdmc+"
            @click="show = !show"
            class="cursor-pointer"
          />
        </div>
        <span class="text-4xl font-extrabold text-white"></span>
      </div>
    </div>
  <div class="px-4 py-4 mx-auto sm:max-w-xl md:max-w-full lg:max-w-screen-xl md:px-24 lg:px-8 lg:py-4">
    <div class="p-8 rounded shadow-2xl sm:p-16 bg-mattBlack">
      <div class="flex flex-col lg:flex-row">
        <div class="mb-6 lg:mb-0 lg:w-1/2 lg:pr-5">
          <h2 class="font-sans text-3xl font-bold tracking-tight text-white sm:text-4xl sm:leading-none">
            치킨너겟
          </h2>
        </div>
        <div class="lg:w-1/2">
          <p class="mb-4 text-base text-gray-50">
            나랏말싸미 듕귁에달아 
          </p>
          <a href="/" aria-label="" class="inline-flex items-center font-semibold transition-colors duration-200 text-pointPurple hover:text-deep-purple-800">자세히 보기</a>
        </div>
      </div>
    </div>
  </div>
    <div class="px-10">
      <div class="w-full">
        <div
          class="flex justify-center items-center bg-white rounded-lg h-28 my-5"
          @click="onClickItem"
        >
          <!-- <homeItemList /> -->
          <p class="text-xl">중고 거래 찾아보기</p>
        </div>
      <span>시세데스</span>
      <TempChart />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import TempChart from "@/components/TempChart1.vue";
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import get from 'lodash/get'
// import HomeItemList from './HomeItemList.vue'

export default defineComponent({
  name: "Home",
  components: {
    TempChart,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

    // console.log(store.getters['getCategoryData']["type"]["phone"]["company"]["iphone"]["series"]["12"])
    console.log(get(store.getters['getCategoryData'], ['type', 'phone', 'company', 'iphone']))
    const categories = ref(["판매량 순", "시세 증감율", "시세 예측"]);
    const onClickItem = function () {
      router.push({
        name: "Prod",
      });
    };

    return { 
      onClickItem,
      categories,
    };
  },
});
</script>

<style scoped>
asd {
  color: #e3d8ff
}
</style>