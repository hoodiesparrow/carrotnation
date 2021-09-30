<template>
  <div v-if="product" class="container max-w-750px">
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
  </div>
  <div class="bg-gray-100 p-3 pb-5"></div>
</template>

<script>
import { defineComponent } from "@vue/composition-api";
import SalesTrend from "@/components/charts/SalesTrend.vue";
import SalesPerItem from "@/components/charts/SalesPerItem.vue";

export default defineComponent({
  component: {
    SalesTrend,
    SalesPerItem,
  },
  setup() {
    const goToBack = () => {
      history.back();
    };

    return {
      goToBack,
    };
  },
});
</script>
