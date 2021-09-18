<template>
  <div
    class="
      my-2
      p-3
      transition
      hover:bg-purple-100 hover:text-black
    "
    @click="onClickItem"
  >
    <div class="grid grid-rows-3 grid-cols-3 gap-1 text-left">
      <div class="row-span-3">
        <img :src="product.img" style="height: 50px" />
      </div>
      <div class="col-span-2 text-2xl flex items-center">{{ product.title }}</div>
      <div class="col-span-2 text-lg font-semibold flex items-center"><p>{{ product.price }}</p></div>
      <div class="col-span-2 text-lg items-center">{{ product.createdate }}</div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
// import HomeItemList from './HomeItemList.vue'

type UrlList = {
  [key: string]: string
}

export default defineComponent({
  name: "ProdBox",
  props: {
    product: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  setup(props) {
    const router = useRouter()
    const urlList: UrlList = {
      joonnaApp: 'https://m.joongna.com/product-detail/',
      joonnaCafe: 'https://m.cafe.naver.com/ArticleRead.nhn?clubid=10050146&articleid=',
      daangn: 'https://www.daangn.com/articles/',
      thunder: 'https://m.bunjang.co.kr/products/',
    }

    const getUrl = () => {
      return urlList[props.product.market]
    }

    const urlRegex = () => {
      const url = props.product.url

      switch(props.product.market) {
        case 'daangn': {
          return /articles\/(.*)/g.exec(url)?.[1]
        }
        case 'joonnaApp': {
          return /product-detail\/(.*)/g.exec(url)?.[1]
        }
        case 'joonnaCafe': {
          return /articleid=(.*)&referrerAllArticles/g.exec(url)?.[1]
        }
        case 'thunder': {
          return /product\/(.*)\/detail/g.exec(url)?.[1]
        }
        default: 
          return
      }
    }

    const routeData = router.resolve({name: 'BridgeTest', query: {code: "someData"}})
    
    const onClickItem = () => {
      console.log(props.product)
      console.log(`${getUrl()}${urlRegex()}`)
      // window.open("https://headlessui.dev/vue/radio-group")
    }

    return {
      router,
      urlList,
      getUrl,
      urlRegex,
      onClickItem,
      routeData,
    }
  },
    
});
</script>
