<template>
  <!-- <div class="container max-w-750px flex-column items-center text-center bg-gradient-to-b from-purple-500 via-pink-400 py-20"> -->
  <div class="container max-w-750px text-center h-full">
    <div class="relative bg-purple-400">
      <div class="absolute inset-x-0 bottom-0">
        <svg class="absolute inset-x-0 bottom-0 text-white" viewBox="0 0 1160 163">
          <path
            fill="currentColor"
            d="M-164 13L-104 39.7C-44 66 76 120 196 141C316 162 436 152 556 119.7C676 88 796 34 916 13C1036 -8 1156 2 1216 7.7L1276 13V162.5H1216C1156 162.5 1036 162.5 916 162.5C796 162.5 676 162.5 556 162.5C436 162.5 316 162.5 196 162.5C76 162.5 -44 162.5 -104 162.5H-164V13Z"
          ></path>
        </svg>
      </div>
      <div class="px-4 py-16 mx-auto sm:max-w-xl md:max-w-full lg:max-w-screen-xl md:px-24 lg:px-8 lg:py-20">
        <div class="relative max-w-2xl sm:mx-auto sm:max-w-xl md:max-w-2xl sm:text-center">
          <h2 class="mb-6 font-sans text-3xl font-bold tracking-tight text-white sm:text-4xl sm:leading-none">
            당근나라
            <span class="relative inline-block px-2">
              <div class="absolute inset-0 transform -skew-x-12 bg-green-400"></div>
              <span class="relative text-green-900">번개시세</span>
            </span>
          </h2>
          <p class="mb-6 text-base text-indigo-100 md:text-lg">
            여기에 뭐 css 예제 뒤져서 예쁜걸 넣어도 되고 음,,
          </p>
        </div>
      </div>
    </div>

    <div class="px-10">
      <div class="w-full">
        <div
          class="flex justify-center items-center bg-white rounded-lg h-28 my-5"
          @click="onClickItem"
        >
          <!-- <homeItemList /> -->
          <p class="text-2xl">중고 거래 찾아보기</p>
        </div>
      <span class="text-lg">나중에 시세 예측도 한다면 historical (price + prediction)을 같이 보여주면 좋을듯</span>
      <br>
      <span class="text-lg">아니면 서버에 저장된 거래글의 수 이런것도 괜찮지않을까</span>
      <HomeChart />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, defineAsyncComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import get from 'lodash/get'
// import HomeItemList from './HomeItemList.vue'
const HomeChart = defineAsyncComponent(() =>
  import('@/components/TempChart1.vue')
)

export default defineComponent({
  name: "Home",
  components: {
    HomeChart,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

    // console.log(store.getters['getCategoryData']["type"]["phone"]["company"]["iphone"]["series"]["12"])
    console.log(get(store.getters['getCategoryData'], ['type', 'phone', 'company', 'iphone']))
    const categories = ref(["판매량 순", "시세 증감율", "시세 예측"]);
    const onClickItem = function () {
      router.push({
        name: "Prod",
      });
    };

    return { 
      onClickItem,
      categories,
    };
  },
});
</script>

<style scoped>
asd {
  color: #e3d8ff
}
</style>