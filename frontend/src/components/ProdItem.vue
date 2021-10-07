<template>
  <div
    class="mb-3 mr-3 ml-3 p-3 transition hover:bg-purple-50 hover:text-black bg-white shadow-md"
    @click="onClickItem"
  >
    <div class="grid grid-rows-5 grid-cols-3 place-content-cente max-w-dm h-48">
      <div class="row-span-5 mr-3">
        <img :src="product.img" class="w-full h-full object-center h-5/6 rounded-2xl" />
      </div>
      <div class="flex">
        <div v-if="getAppName() == '당근마켓'" class="w-7">
          <img class="mt-2.5" src="@/assets/image/daangn.png" />
        </div>
        <div v-if="getAppName() == '번개장터'" class="w-7">
          <img class="mt-2.5" src="@/assets/image/thunder.png" />
        </div>
        <div v-if="getAppName() == '중고나라'" class="w-7">
          <img class="mt-2.5" src="@/assets/image/joongna.png" />
        </div>
        <div class="col-span-2 text-sl font-semibold items-center pl-1 mt-3">
          {{ getAppName() }}
        </div>
      </div>

      <div
        class="col-span-2 text-xl flex items-center pl-1 mt-2"
        style="
          text-overflow: ellipsis;
          white-space: nowrap;
          overflow: hidden;
          width: 90%;
          display: block;
          text-gray-900
        "
      >
        {{ product.title }}
      </div>
      <div class="col-span-2 text-xl font-semibold flex pl-1 mt-2 text-gray-900">
        <p>{{ product.price.toLocaleString() }}원</p>
      </div>
      <div class="col-span-2 flex text-sm text-gray-500 mt-3" v-if="product.location">
        <div class="mt-1 mr-1">
          <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24">
            <path
              d="M12 0c-4.198 0-8 3.403-8 7.602 0 4.198 3.469 9.21 8 16.398 4.531-7.188 8-12.2 8-16.398 0-4.199-3.801-7.602-8-7.602zm0 11c-1.657 0-3-1.343-3-3s1.343-3 3-3 3 1.343 3 3-1.343 3-3 3z"
            />
          </svg>
        </div>
        <div>{{ product.location }}</div>
      </div>
      <div v-else></div>
      <div class="col-span-2 text-sm items-center pl-1 text-gray-500 mt-1">
        {{ product.createDate.substring(2, 10) }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
// import HomeItemList from './HomeItemList.vue'

type UrlList = {
  [key: string]: string;
};

export default defineComponent({
  name: "ProdBox",
  props: {
    product: {
      type: Object,
      default: () => {
        return {};
      },
    },
    productName: {
      type: String,
    },
  },
  setup(props) {
    const router = useRouter();
    const urlList: UrlList = {
      joonnaApp: "https://m.joongna.com/product-detail/",
      joonnaCafe: "https://m.cafe.naver.com/ArticleRead.nhn?clubid=10050146&articleid=",
      daangn: "https://www.daangn.com/articles/",
      thunder: "https://m.bunjang.co.kr/products/",
    };

    const getUrl = () => {
      return urlList[props.product.market];
    };

    const getAppName = () => {
      switch (props.product.market) {
        case "daangn":
          return "당근마켓";
        case "joonnaApp":
          return "중고나라";
        case "joonnaCafe":
          return "중고나라";
        case "thunder":
          return "번개장터";

        default:
          return "?";
      }
    };

    const urlRegex = () => {
      const url = props.product.url;

      switch (props.product.market) {
        case "daangn": {
          return /articles\/(.*)/g.exec(url)?.[1];
        }
        case "joonnaApp": {
          return /product-detail\/(.*)/g.exec(url)?.[1];
        }
        case "joonnaCafe": {
          return /articleid=(.*)&referrerAllArticles/g.exec(url)?.[1];
        }
        case "thunder": {
          return /product\/(.*)\/detail/g.exec(url)?.[1];
        }
        default:
          return;
      }
    };

    // const routeData = router.resolve({ name: "BridgeTest", query: { code: "someData" } });

    const onClickItem = () => {
      router.push({
        name: "Detail",
        query: {
          id: props.product.id,
          productName: props.productName,
        },
      });
    };

    return {
      router,
      urlList,
      getUrl,
      urlRegex,
      onClickItem,
      getAppName,
    };
  },
});
</script>
