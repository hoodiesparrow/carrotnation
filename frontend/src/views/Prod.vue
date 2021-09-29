<template>
  <div class="container max-w-750px" ref="container">
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
          <!-- <span class="pr- text-lg">총 {{ prodList.length }}건</span> -->
        </div>
      </div>
    </div>
    <div class="text-left">
      <div class="flex flex-col bg-gray-100">
        <ProdBox v-for="prod in prodList" :key="prod.pid" :product="prod" />
      </div>
    </div>
    <div v-if="isLoading" class="flex justify-center">
      <svg xml:space="preserve" viewBox="0 0 100 100" class="w-16 h-16 animate-spin" y="0" x="0" xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink">
        <g class="ldl-scale">
          <circle fill="#333" r="40" cy="50" cx="50">
          </circle>
          <g>
            <path fill="#fff" d="M50 74c-13.234 0-24-10.766-24-24h7.268c0 9.226 7.506 16.732 16.732 16.732S66.732 59.226 66.732 50 59.226 33.268 50 33.268V26c13.234 0 24 10.766 24 24S63.234 74 50 74z">
            </path>
          </g>
        </g>
      </svg>
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
    const container = ref(null)
    const show = ref(false)
    const prodInfo = ref({
      name: '',
      minPrice: 0,
      avgPrice: 0,
      maxPrice: 0,
    })
    const prodList = ref([]);
    const initialLoading = ref(true)
    const initialLoadingFailed = ref(false)
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
        switch (res.status) {
          case 200:
            totalPage.value = res.data.totalpage
            prodList.value.push(...res.data.list)
            break
          case 204:
            initialLoadingFailed.value = true
            console.log('204204')
            break
          default:
            initialLoadingFailed.value = true
            console.log('other case')
        }
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
          setTimeout(() => {
            container.value.scrollTop = container.value.scrollHeight;
          }, 100)
          console.log(container.value.scrollTop)
          console.log(container.value.scrollHeight)

          setTimeout(() => {
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
          }, 1000)
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
      container,
      prodList, 
      show,
      query,
      handleScroll,
      initialLoading,
      initialLoadingFailed,
      isLoading,
      totalPage,
      noMoreData,
      prodInfo,
    };
  },
});
</script>
