<template>
  <div @click="goToDetail()">
    <img :src="simprod.img" />
    <p>{{ simprod.price.toLocaleString() }}</p>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

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
    const goToDetail = () => {
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
      goToDetail,
    };
  },
});
</script>
