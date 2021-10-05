<template>
  <div @click="expand = !expand" class="px-3 py-2 cursor-pointer">
    <div v-show="!expand" class="flex justify-between">
      <span class="text-gray-700">총 {{ this.count }}건</span>
      <div class="flex">
        <span class="pr-2 text-gray-700">평균가 : {{ this.prodInfo.avgPrice }}</span>
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" class="mt-1">
            <path d="M0 7.33l2.829-2.83 9.175 9.339 9.167-9.339 2.829 2.83-11.996 12.17z"/>
          </svg>
      </div>
    </div>
    <div v-show="expand" class="flex justify-between bg-white px-3 sm:px-20 py-1 shadow-md">
      <div class="flex flex-col items-start">
        <span class="text-md border-b-2">최저가</span>
        <span class="text-md">{{ this.prodInfo.minPrice }}원</span>
      </div>
      <div class="flex flex-col items-start">
        <span class="text-md border-b-2">평균가</span>
        <span class="text-md">{{ this.prodInfo.avgPrice }}원</span>
      </div>
      <div class="flex flex-col items-start">
        <span class="text-md border-b-2">최고가</span>
        <span class="text-md">{{ this.prodInfo.maxPrice }}원</span>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  props: {
    prodInfo: {
      type: Object,
      default: null,
    },
    count: {
      type: Number,
      default: null,
    },
  },
  setup(props) {
    const expand = ref(false);

    return {
      expand,
    };
  },
};
</script>
