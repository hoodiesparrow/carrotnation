<template>
  <div class="container max-w-750px">
    <SideBar :show="show" @closeSideBar="show=false" class="fixed top-0 z-40 h-full" />
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <div>
          <img
            src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjQgNmgtMjR2LTRoMjR2NHptMCA0aC0yNHY0aDI0di00em0wIDhoLTI0djRoMjR2LTR6Ii8+PC9zdmc+"
            @click="show = !show"
            class="cursor-pointer"
          />
        </div>
        <span class="text-4xl font-extrabold text-white">{{ prodInfo.name }}</span>
      </div>
      <div class="grid grid-rows-2 bg-white py-2 border-b-2 border-gray-300">
        <div class="col-span-2 text-right">
          <span class="col-span-2 pr-4 text-lg">최저가 : {{ prodInfo.minPrice }}</span>
          <span class="col-span-2 pr-4 text-lg">평균가 : {{ prodInfo.avgPrice }}</span>
          <span class="col-span-2 pr-4 text-lg">최고가 : {{ prodInfo.maxPrice }}</span>
          <span class="pr- text-lg">총 {{ prodList.length }}건</span>
        </div>
      </div>
    </div>
    <div class="text-left">
      <div class="flex flex-col">
        <ProdBox 
          v-for="prod in prodList" 
          :key="prod.pid" 
          :product="prod"
        />

      </div>
    </div>
    <div v-if="isLoading">
      <button type="button" class="bg-white" disabled>
        <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-pink-600" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
              <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
        </svg>
        Processing
      </button>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { defineComponent, reactive, ref, onMounted, onUnmounted, computed } from "vue";
import SideBar from "@/components/SideBar.vue";
import ProdBox from "@/components/ProdItem.vue";

export default defineComponent({
  name: "Home",
  components: {
    ProdBox,
    SideBar,
  },

  setup() {
    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    const show = ref(false)
    const prodInfo = ref({
      name: '',
      minPrice: 0,
      avgPrice: 0,
      maxPrice: 0,
    })
    const prodList = ref([]);
    const initialLoading = ref(true)
    const isLoading = ref(false)
    const noMoreData = ref(false)
    const totalPage = ref(0)
    const query = ref({
      pid: route.query.pid,
      page: 0,
    })
    store.dispatch('requestProductInfo', query.value.pid)
      .then(res => {
        prodInfo.value.name = res.data.product.name
        prodInfo.value.minPrice = res.data.product.minPrice.toLocaleString()
        prodInfo.value.avgPrice = res.data.product.avgPrice.toLocaleString()
        prodInfo.value.maxPrice = res.data.product.maxPrice.toLocaleString()
      })


    store.dispatch('requestProductList', query.value)
      .then((res) => {
        totalPage.value = res.data.totalpage
        prodList.value.push(...res.data.list)
      })
      .catch((err) => {
        console.log(err)
      })
      .finally(() => {
        initialLoading.value = false
      })

    // infinite scroll
    const handleScroll = () => {
      if (((window.innerHeight + window.scrollY) >= document.body.offsetHeight) && !isLoading.value && !initialLoading.value) {
        if (query.value.page <= totalPage.value - 1) {
          console.log('additional loading seq.')
          isLoading.value = true
          query.value.page += 1
          store.dispatch('requestProductList', query.value)
            .then(res => {
              totalPage.value = res.data.totalpage
              prodList.value.push(...res.data.list)
            })
            .catch(err => {
              console.log(err)
            })
            .finally(() => {
              isLoading.value = false
            })
        } else {
          noMoreData.value = true
        }
      }
    }

    onMounted(() => {
      window.addEventListener('scroll', handleScroll)
    })
    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll)
    })
    
    return { 
      prodList, 
      show,
      query,
      handleScroll,
      initialLoading,
      isLoading,
      totalPage,
      noMoreData,
      prodInfo,
    };
  },
});
</script>