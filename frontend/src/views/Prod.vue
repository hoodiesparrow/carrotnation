<template>
  <div class="container max-w-750px  bg-gray-100" ref="container">
    <SideBar :show="show" @closeSideBar="show=false" class="fixed top-0 z-40 h-full" />
    <div class="sticky top-0 transition duration-300 border-gray-300 z-40" :class="{'shadow-xl': !atTopOfPage, 'border-b-2': atTopOfPage}">
      <!-- <div class="flex justify-between items-center bg-gradient-to-r from-purple-400 to-purple-700 p-4"> -->
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
      <div class="bg-white p-3 flex justify-between text-gray-800">
        <div class="flex items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
            <path d="M12 0c-4.198 0-8 3.403-8 7.602 0 4.198 3.469 9.21 8 16.398 4.531-7.188 8-12.2 8-16.398 0-4.199-3.801-7.602-8-7.602zm0 11c-1.657 0-3-1.343-3-3s1.343-3 3-3 3 1.343 3 3-1.343 3-3 3z"/>
          </svg>
          <span>내 주변</span>
        </div>
        <div class="flex">
          <div class="flex items-center pr-6">
            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
              <path d="M19 2c1.654 0 3 1.346 3 3v14c0 1.654-1.346 3-3 3h-14c-1.654 0-3-1.346-3-3v-14c0-1.654 1.346-3 3-3h14zm5 3c0-2.761-2.238-5-5-5h-14c-2.762 0-5 2.239-5 5v14c0 2.761 2.238 5 5 5h14c2.762 0 5-2.239 5-5v-14zm-13 12h-2v3h-2v-3h-2v-3h6v3zm-2-13h-2v8h2v-8zm10 5h-6v3h2v8h2v-8h2v-3zm-2-5h-2v3h2v-3z"/>
            </svg>
            <span class="pl-1">
              마켓
            </span>
          </div>
          <ProdSortButton />
        </div>
      </div>
    </div>
    <div class="text-left">
      <div class="flex flex-col">
        <ProdPriceInfo :prodInfo="prodInfo" />
        <ProdBox v-for="prod in prodList" :key="prod.pid" :product="prod" />
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
        <div v-if="initialLoadingFailed">
          로딩에 실패하였습니다.
        </div>
        <div v-if="noMoreData">
          <p class="border-t-2 border-gray-300 w-full text-center">리스트의 마지막입니다.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { useRoute, useRouter } from "vue-router";
import { defineComponent, reactive, ref, onMounted, onUnmounted, computed, watch } from "vue";
import SideBar from "@/components/SideBar.vue";
import ProdBox from "@/components/ProdItem.vue";
import ProdPriceInfo from "@/components/Prod/ProdPriceInfo.vue";
import ProdSortButton from "@/components/Prod/ProdSortButton.vue"

export default defineComponent({
  name: "Home",
  components: {
    ProdBox,
    SideBar,
    ProdPriceInfo,
    ProdSortButton,
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
        prodInfo.value.count = res.data.searchcount
        console.log(res)
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
        console.log(err);
      })
      .finally(() => {
        initialLoading.value = false;
      });

    

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
    };

    onMounted(() => {
      window.addEventListener("scroll", handleScroll);
    });
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
      atTopOfPage,
    };
  },
});
</script>
