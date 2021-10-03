<template>
  <div class="container max-w-750px">
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <div @click="goToBack()">
          <svg
            class="h-8 w-8 text-white"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          >
            <polyline points="15 18 9 12 15 6" />
          </svg>
        </div>
        <span class="text-4xl font-extrabold text-white">{{ productName }}</span>
      </div>
    </div>
    <div class="flex flex-col bg-gray-100">
      <SalesTrend :data="trendprice" :label="trendlabel" :chartOptions="chartOptions" />
    </div>
    쨘
  </div>
</template>

<script>
import { defineComponent, reactive, ref, onMounted, onUnmounted, computed } from "vue";
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { TabGroup, TabList, Tab, TabPanels, TabPanel } from "@headlessui/vue";
import SalesTrend from "@/components/charts/SalesTrend.vue";
//import SalesPerItem from "@/components/charts/SalesPerItem.vue";

export default defineComponent({
  component: {
    SalesTrend,
    //SalesPerItem,
    TabGroup,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
  },
  setup() {
    let categories = ref(["가격당 판매량", "판매가 추이"]);
    const route = useRoute();
    const store = useStore();
    const productName = ref();
    const query = ref({
      pid: route.query.pid,
    });

    let trendprice = [];
    let trendlabel = [];
    let chartOptions = {
      responsive: false,
    };

    const goToBack = () => {
      history.back();
    };

    store
      .dispatch("requestDatePrice", query.value.pid)
      .then((res) => {
        var dateprice = res.data.dataprice;
        productName.value = dateprice[0].productName;

        for (var i = 0; i < dateprice.length; i++) {
          trendprice[i] = dateprice[i].price;
          trendlabel[i] = dateprice[i].pdate;
        }
      })
      .catch((err) => {
        console.log(err);
      });
    return {
      goToBack,
      categories,
      trendprice,
      trendlabel,
      chartOptions,
    };
  },
});
</script>
