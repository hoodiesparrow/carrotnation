<template>
  <div
    class="mb-3 mr-3 ml-3 p-3 transition hover:bg-purple-50 hover:text-black bg-white shadow-md"
    @click="onClickItem"
  >
    <div class="grid grid-rows-4 grid-cols-3 place-content-cente max-w-dm h-48">
      <div class="row-span-4">
        <img :src="product.img" class="w-full h-full object-center pr-2 h-5/6" />
      </div>
      <div class="col-span-2 text-sl font-semibold flex items-center pl-1 text-gray-800">{{ getAppName() }}</div>
      <div
        class="col-span-2 text-xl flex items-center pl-1 pt-2"
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
      <div class="col-span-2 text-xl font-semibold flex items-center pl-1 text-gray-900">
        <p>{{ product.price.toLocaleString() }}원</p>
      </div>
      <div class="col-span-2 text-sm items-center pl-1 text-gray-500">
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
      console.log(props.productName);
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
