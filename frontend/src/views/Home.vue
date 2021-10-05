<template>
  <!-- <div class="container max-w-750px flex-column items-center text-center bg-gradient-to-b from-purple-500 via-pink-400 py-20"> -->
  <!-- <div class="container max-w-750px text-center min-h-screen bg-gradient-to-r from-blue-400 to-indigo-500"> -->
  <div
    class="
      container
      max-w-750px
      text-center
      min-h-screen
      h-full
      bg-gradient-to-r
      from-blue-100
      to-pink-100
    "
  >
    <div class="relative bg-purple-400">
      <div
        class="
          px-4
          pt-16
          mx-auto
          sm:max-w-xl
          md:max-w-full
          lg:max-w-screen-xl
          md:px-24
          lg:px-8 lg:pt-20
        "
      >
        <div class="relative max-w-2xl sm:mx-auto sm:max-w-xl md:max-w-2xl sm:text-center">
          <h2
            class="
              mb-6
              font-sans
              text-3xl
              font-bold
              tracking-tight
              text-white
              sm:text-4xl sm:leading-none
            "
          >
            당근나라
            <span class="relative inline-block px-2">
              <div class="absolute inset-0 transform -skew-x-12 bg-green-400"></div>
              <span class="relative text-green-900">번개시세</span>
            </span>
          </h2>
          <p class="text-base text-indigo-100 md:text-lg">
            모바일 디바이스 중고거래를 한눈에
          </p>
        </div>
      </div>
    </div>
    <div class="">
      <svg class="text-purple-400" viewBox="0 -1 1160 163">
        <path
          fill="currentColor"
          d="M -164 13 L -104 39.7 C -44 66 76 120 196 141 C 316 162 436 152 556 119.7 C 676 88 796 34 916 13 C 1036 -8 1156 2 1216 7.7 L 1276 13 V -394 H -164 Z"
        ></path>
      </svg>
    </div>

    <div class="flex flex-col justify-between">
      <div class="w-full">
        <HomeSelectButton />
      </div>
      <HomeFooter />
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, defineAsyncComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
// import HomeItemList from './HomeItemList.vue'
// const HomeChart = defineAsyncComponent(() => import("@/components/TempChart1.vue"));
const HomeSelectButton = defineAsyncComponent(
  () => import("@/components/Home/HomeSelectButton.vue")
);
const HomeFooter = defineAsyncComponent(
  () => import("@/components/Home/HomeFooter.vue")
);

export default defineComponent({
  name: "Home",
  components: {
    HomeSelectButton,
    HomeFooter,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

    const onClickItem = function () {
      router.push({
        name: "Prod",
      });
    };

    return {
      onClickItem,
    };
  },
});
</script>
