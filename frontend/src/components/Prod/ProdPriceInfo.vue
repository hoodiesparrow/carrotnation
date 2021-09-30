<template>
  <div @click="expand=!expand" class="px-3 py-2 cursor-pointer">
    <div v-show="!expand" class="flex justify-between">
      <span class="text-gray-700">총 {{ this.prodInfo.count }}건</span>
      <div class="flex">
        <span class="pr-2 text-gray-700">평균가 : {{ this.prodInfo.avgPrice }}</span>
        <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
          viewBox="0 0 512.011 512.011" style="enable-background:new 0 0 512.011 512.011;" xml:space="preserve" class="w-4 y-4">
            <path d="M505.755,123.592c-8.341-8.341-21.824-8.341-30.165,0L256.005,343.176L36.421,123.592c-8.341-8.341-21.824-8.341-30.165,0
              s-8.341,21.824,0,30.165l234.667,234.667c4.16,4.16,9.621,6.251,15.083,6.251c5.462,0,10.923-2.091,15.083-6.251l234.667-234.667
              C514.096,145.416,514.096,131.933,505.755,123.592z"/>
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
import { ref } from 'vue'

export default {
  props: {
    prodInfo: {
      type: Object,
      default: null
    }
  },
  setup(props) {
    const expand = ref(false)

    return {
      expand,
    }
  },
}
</script>