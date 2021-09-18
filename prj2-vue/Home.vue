<template>
  <!-- <div class="container max-w-750px flex-column items-center text-center bg-gradient-to-b from-purple-500 via-pink-400 py-20"> -->
  <div class="container max-w-750px text-center h-full">
    <SideBar
      :show="show"
      @closeSideBar="show = !show"
      @searchItem="searchItem"
      class="fixed top-0 z-40 h-full"
    />
    <div class="flex-column items-center text-white bg-purple-700 pb-16">
      <div class="pl-5 pt-5">
        <img
          src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjQgNmgtMjR2LTRoMjR2NHptMCA0aC0yNHY0aDI0di00em0wIDhoLTI0djRoMjR2LTR6Ii8+PC9zdmc+"
          @click="show = !show"
          class="cursor-pointer"
        />
      </div>
      <p class="text-6xl font-extrabold pb-12">중고거래</p>
      <p class="text-3xl">중고 거래 시세 추이 확인 서비스</p>
    </div>

  </div>
</template>

<script lang="ts">
import SideBar from "@/components/SideBar.vue";
import TempChart from "@/components/TempChart1.vue";
import { TabGroup, TabList, Tab, TabPanels, TabPanel } from "@headlessui/vue";
import { defineComponent, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
// import HomeItemList from './HomeItemList.vue'

export default defineComponent({
  name: "Home",
  components: {
    SideBar,
    // HomeItemList,
    TabGroup,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
    TempChart,
  },

  setup() {
    const router = useRouter();
    const store = useStore();
    const show = ref(false);
    let categories = ref(["판매량 순", "시세 증감율", "시세 예측"]);

    const searchItem = function () {
      router.push({
        name: "Prod",
      });
    };

    return { searchItem, show, categories };
  },
});
</script>
