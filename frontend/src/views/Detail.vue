<template>
  <div v-if="product" class="container max-w-750px">
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <div @click="goToBack()">
          <svg
            class="h-8 w-8 text-white"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          >
            <polyline points="15 18 9 12 15 6" />
          </svg>
        </div>
        <span class="text-4xl font-extrabold text-white">{{ productName }}</span>
      </div>
    </div>

    <div class="bg-gray-100 p-3 pb-5">
      <div class="w-full mb-px">
        <img :src="product.articleDeatil.img" class="w-full" />
      </div>
      <div class="mb-2">
        <div class="bg-white mb-px">
          <div class="pt-3">
            <p
              v-if="product.articleDeatil.market == 'daangn'"
              class="text-lg font-bold p-1 px-3"
              style="color: #e78111"
            >
              당근마켓
            </p>
            <p
              v-else-if="product.articleDeatil.market == 'joonnaApp'"
              class="text-lg font-bold p-1 px-3"
              style="color: #ff6c2d"
            >
              중고나라
            </p>
            <p
              v-if="product.articleDeatil.market == 'thunder'"
              class="text-lg font-bold p-1 px-3"
              style="color: #d80c18"
            >
              번개장터
            </p>
          </div>
          <p class="text-xl font-semibold p-1 px-3">{{ product.articleDeatil.title }}</p>
          <p class="text-2xl font-bold p-1 px-3">
            가격 : {{ product.articleDeatil.price.toLocaleString() }}원
          </p>
          <div class="pb-5">
            <p class="text-lg font-bold p-1 px-3 text-gray-400">
              작성일자: {{ product.articleDeatil.createDate.substring(0, 10) }}
            </p>
            <p class="text-lg font-bold px-3 text-gray-400" v-if="product.articleDeatil.location">
              위치 : {{ product.articleDeatil.location }}
            </p>
          </div>
        </div>
        <div class="bg-white mb-px pt-5">
          <p class="text-lg font-extrabold text-gray-500 p-3">상품 설명</p>
          <p class="text-base text-lg font-medium p-4">{{ product.articleDeatil.content }}</p>
        </div>
      </div>
      <div>
        <div class="bg-white mb-3 p-5">
          <p class="text-xl font-semibold text-gray-500 pb-3">유사 게시글</p>
          <div v-if="product.similerProduct.length > 0">
            <div style="white-space: nowrap; overflow: auto">
              <SmallCard
                v-for="prod in product.similerProduct"
                :key="prod.id"
                :product="prod"
                :productName="productName"
              />
            </div>
          </div>
          <div v-else class="text-center">
            <p class="text-xl text-gray-400">유사한 게시글을 찾지 못했습니다.</p>
          </div>
        </div>
      </div>
    </div>

    <div class="sticky bottom-0">
      <a :href="product.articleDeatil.link">
        <div class="flex-column font-semibold text-center bg-purple-200 p-4" @click="redir">
          <p class="text-2xl">판매 사이트로 이동</p>
        </div>
      </a>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, computed } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useStore } from "vuex";
import SmallCard from "@/components/SmallCard.vue";

export default defineComponent({
  name: "Detail",
  components: {
    SmallCard,
  },
  setup() {
    const router = useRouter();
    const store = useStore();
    const route = useRoute();

    const product = ref(null);
    const productName = route.query.productName;
    store
      .dispatch("requestProductDetail", { id: route.query.id, market: route.query.market })
      .then((res) => {
        product.value = res.data;
        console.log(product.value);
      })
      .catch((err) => {
        console.log(err.data);
      })
      .finally(() => {
        console.log(product.value);
      });

    const goToBack = () => {
      history.back();
    };

    return {
      product,
      productName,
      route,
      goToBack,
      SmallCard,
    };
  },
});
</script>

<style></style>
