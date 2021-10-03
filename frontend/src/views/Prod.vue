<template>
  <div class="container max-w-750px" ref="container">
    <div class="sticky top-0 transition duration-300 border-gray-300 z-40" :class="{'shadow-xl': !atTopOfPage, 'border-b-2': atTopOfPage}">
      <!-- <div class="flex justify-between items-center bg-gradient-to-r from-purple-400 to-purple-700 p-4"> -->
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <svg
          class="h-8 w-8 text-white cursor-pointer"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
          @click="goToBack()"
        >
          <polyline points="15 18 9 12 15 6" />
        </svg>
        <span class="text-2xl font-extrabold text-white">{{ prodInfo.name }}</span>
        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
          @click="goToQuote" class="cursor-pointer mx-2"
        >
          <path fill="#FFFFFF" d="M14.172 7.396l1.413-1.39 1.414 1.414-1.414 1.39-1.413-1.414zm2.828 16.604h6v-13h-6v13zm-.559-18.834l1.414 1.414 1.435-1.41-1.415-1.415-1.434 1.411zm.591-3.951l4.771 4.771 1.197-5.986-5.968 1.215zm-8.032 22.785h6v-9h-6v9zm-8 0h6v-6h-6v6zm13.729-14.349l-1.414-1.414-1.45 1.425-3-3.002-7.841 7.797 1.41 1.418 6.427-6.39 2.991 2.993 2.877-2.827z"/>
        </svg>
      </div>
      <div class="bg-white p-3 flex justify-between text-gray-800">
        <div class="flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
            <path d="M12 0c-4.198 0-8 3.403-8 7.602 0 4.198 3.469 9.21 8 16.398 4.531-7.188 8-12.2 8-16.398 0-4.199-3.801-7.602-8-7.602zm0 11c-1.657 0-3-1.343-3-3s1.343-3 3-3 3 1.343 3 3-1.343 3-3 3z"/>
          </svg>
          <span>내 주변</span>
        </div>
        <div class="flex">
          <ProdMarketButton @market="market" class="pr-6" />
          <ProdSortButton @sort="sort" />
        </div>
      </div>
    </div>

    <div class="text-left">
      <div v-if="!errorFlag" class="flex flex-col bg-gray-100">
        <ProdPriceInfo :prodInfo="prodInfo" />
        <ProdBox v-for="(prod, idx) in prodList" :key="idx" :product="prod" :productName="prodInfo.name" />
        <div v-if="initialLoading" class="flex justify-center">
          <svg xml:space="preserve" viewBox="0 0 100 100" class="w-64 h-full h-64 animate-spin" y="0" x="0" xmlns="http://www.w3.org/2000/svg" version="1.1" xmlns:xlink="http://www.w3.org/1999/xlink">
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
        <div v-if="noMoreData" class="text-center text-gray-600 bg-purple-200">
          End of the List
          <!-- <p class="border-t-2 border-gray-300 w-full text-center text-md">리스트의 마지막입니다.</p> -->
        </div>
      </div>

      <div v-if="initialLoadingFailed" class="h-screen bg-gray-100 flex flex-col items-center pt-32">
        <svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 24 24">
          <path d="M16.143 2l5.857 5.858v8.284l-5.857 5.858h-8.286l-5.857-5.858v-8.284l5.857-5.858h8.286zm.828-2h-9.942l-7.029 7.029v9.941l7.029 7.03h9.941l7.03-7.029v-9.942l-7.029-7.029zm-6.471 6h3l-1 8h-1l-1-8zm1.5 12.25c-.69 0-1.25-.56-1.25-1.25s.56-1.25 1.25-1.25 1.25.56 1.25 1.25-.56 1.25-1.25 1.25z"/>
        </svg>
        <span class="text-lg mt-4">요청이 실패하였습니다.</span>
        <button class="mt-10 p-1 bg-white border border-black" @click="initialLoader">다시 시도</button>
      </div>
      <div v-if="noData" class="h-screen bg-gray-100 flex flex-col items-center pt-32">
        <svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 24 24">
          <path d="M16.142 2l5.858 5.858v8.284l-5.858 5.858h-8.284l-5.858-5.858v-8.284l5.858-5.858h8.284zm.829-2h-9.942l-7.029 7.029v9.941l7.029 7.03h9.941l7.03-7.029v-9.942l-7.029-7.029zm-8.482 16.992l3.518-3.568 3.554 3.521 1.431-1.43-3.566-3.523 3.535-3.568-1.431-1.432-3.539 3.583-3.581-3.457-1.418 1.418 3.585 3.473-3.507 3.566 1.419 1.417z"/>
        </svg>
        <span class="text-lg mt-4">조건을 만족하는 게시글이 존재하지 않습니다.</span>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { defineComponent, reactive, ref, onMounted, onUnmounted, computed, watch } from "vue";
import ProdBox from "@/components/ProdItem.vue";
import ProdPriceInfo from "@/components/Prod/ProdPriceInfo.vue";
import ProdSortButton from "@/components/Prod/ProdSortButton.vue"
import ProdMarketButton from "@/components/Prod/ProdMarketButton.vue"

export default defineComponent({
  name: "Home",
  components: {
    ProdBox,
    ProdPriceInfo,
    ProdSortButton,
    ProdMarketButton,
  },

  setup() {
    const route = useRoute();
    const router = useRouter();
    const store = useStore();
    const container = ref(null)
    const show = ref(false)
    const atTopOfPage = ref(true)
    const prodInfo = ref({
      name: "",
      minPrice: 0,
      avgPrice: 0,
      maxPrice: 0,
      count: 0,
    });
    const prodList = ref([]);
    const initialLoading = ref(true)
    const initialLoadingFailed = ref(false)
    const isLoading = ref(false)
    const noData = ref(false)
    const noMoreData = ref(false)
    const totalPage = ref(0)
    const query = ref({
      pid: route.query.pid,
      page: 0,
    })
    const errorFlag = computed(() => {
      return noData.value || initialLoadingFailed.value
    })
    
    const goToBack = () => {
      router.push({
        name: 'Home'
      })
    };

    const goToQuote = () => {
      router.push({
        name: "Quote",
        query: {
          pid: query.value.pid,
        },
      });
    };

    const initialLoader = function () {
      console.log('@initialLoader', query.value)
      const infoQuery = { 
        pid: query.value.pid,
        market: query.value.market === undefined ? 0 : query.value.market
      }
      console.log(infoQuery)
      store.dispatch('requestProductInfo', infoQuery)
        .then(res => {
          prodInfo.value.name = res.data.product.name
          prodInfo.value.minPrice = res.data.product.minPrice.toLocaleString()
          prodInfo.value.avgPrice = res.data.product.avgPrice.toLocaleString()
          prodInfo.value.maxPrice = res.data.product.maxPrice.toLocaleString()
          prodInfo.value.count = res.data.searchcount
        })
      // 초기화
      initialLoading.value = true
      initialLoadingFailed.value = false
      prodList.value = []

      store.dispatch('requestProductList', query.value)
        .then((res) => {
          switch (res.status) {
            case 200:
              noData.value = false
              totalPage.value = res.data.totalpage
              if (totalPage.value <= 1) {
                noMoreData.value = true
              }
              prodList.value.push(...res.data.list)
              initialLoading.value = false;
              console.log(res.data)
              break
            case 204:
              noData.value = true
              initialLoading.value = false;
              break

            default:
              console.log('other case')
          }
        })
        .catch((err) => {
          initialLoadingFailed.value = true
          console.log(err);
        })
    }
    initialLoader()



    // infinite scroll
    const handleScroll = () => {
      // 그림자 추가하기
      if (window.pageYOffset>0) {
        if (atTopOfPage.value) {
          atTopOfPage.value = false
        }
      } else {
        if (!atTopOfPage.value) {
          atTopOfPage.value = true
        } 
      }

      if (!noMoreData.value) {
        if (
          window.innerHeight + window.scrollY >= document.body.offsetHeight &&
          !isLoading.value &&
          !initialLoading.value &&
          !initialLoadingFailed.value
        ) {
          if (query.value.page <= totalPage.value - 2) {
            console.log('additional loading seq.')
            isLoading.value = true
            setTimeout(() => {
              window.scrollTo(0, container.value.scrollHeight)
            }, 20)
  
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
            noMoreData.value = true;
          }
        }
      }
    };

    onMounted(() => {
      window.addEventListener("scroll", handleScroll);
    });
    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll)
    })
    
    const sort = function () {
      query.value = { 
        ...query.value, 
        page: 0, 
        sort: store.getters['getSort']
      }
      initialLoader()
    }
    
    const market = function () {
      const { market: temp, ...rest } = query.value
      if (store.getters['getMarket'] >= 1) {
        query.value = {
          ...rest,
          page: 0,
          market: store.getters['getMarket']
        }
      } else {
        query.value = {
          ...rest,
          page: 0,
        }
      console.log(query.value)
      }
      initialLoader()
    }
    return { 
      errorFlag,
      noData,
      initialLoader,
      sort,
      market,
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
      atTopOfPage,
      goToQuote,
      goToBack,
    };
  },
});
</script>
