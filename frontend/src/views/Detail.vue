<template>
  <div class="container max-w-750px">
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <div @click="goToBack">
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
        <span class="text-4xl font-extrabold text-white">아이폰 프로 512g</span>
      </div>
    </div>

    <div class="bg-gray-100 p-3 pb-5">
      <div class="w-full mb-px">
        <img
          src="http://t1.daumcdn.net/friends/prod/editor/dc8b3d02-a15a-4afa-a88b-989cf2a50476.jpg"
        />
      </div>
      <div class="mb-2">
        <div class="bg-white mb-px">
          <p class="text-xl font-semibold p-1 px-3 pt-5">{{ Product.title }}</p>
          <p class="text-2xl font-bold p-1 px-3">가격 : {{ Product.price }} 원</p>
          <p class="text-lg font-bold p-1 px-3 text-gray-400 pb-5">
            작성일자 : {{ Product.createdate }}
          </p>
        </div>
        <div class="bg-white mb-px">
          <p class="text-lg font-extrabold text-gray-500 p-3">상품 설명</p>
          <p class="text-base text-lg font-medium p-4">{{ Product.content }}</p>
        </div>
      </div>
      <div>
        <div class="bg-white mb-3">
          <p class="text-xl font-semibold text-gray-500 p-3">유사 게시글</p>
          <div></div>
        </div>
      </div>
    </div>

    <div class="sticky bottom-0">
      <div class="flex-column font-semibold text-center bg-purple-200 p-4" @click="redir">
        <p class="text-2xl">판매 사이트로 이동</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  name: "Detail",
  setup() {
    const router = useRouter();
    const store = useStore();

    var Product = {
      pid: "1",
      title: "당근당근당근당근당근당근당근",
      market: "daangn",
      price: "300000",
      createdate: "2021-09-09",
      img: "@/assets/image/iphone12pro.jfif",
      url: "https://www.daangn.com/articles/279930062",
      content:
        "당근당근당근당근당근당근당근 당근당근당근당근당근당근당근 당근당근당근당근당근당근당근",
    };
    return {
      Product,
    };
  },
});
</script>

<style></style>
