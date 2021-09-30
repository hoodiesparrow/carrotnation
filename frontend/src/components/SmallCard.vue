<template>
  <div
    class="bg-white rounded-lg md:w-1/4 shadow-md inline-block m-1 mx-2"
    @click="goToDetail"
    style=""
  >
    <img :src="product.img" class="w-full h-48" />

    <p class="font-bold text-gray-800 text-right py-1 pr-2">
      {{ product.price.toLocaleString() }}원
    </p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter, useRoute } from "vue-router";

export default defineComponent({
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
    const route = useRoute();

    const goToDetail = () => {
      console.log(props.productName);
      router.push({
        name: "Detail",
        query: {
          id: props.product.id,
          market: props.product.market,
          productName: props.productName,
        },
      });
    };

    return {
      router,
      goToDetail,
    };
  },
});
</script>
