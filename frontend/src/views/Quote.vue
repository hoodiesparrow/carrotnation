<template>
  <div class="container max-w-750px">
    <div class="sticky top-0">
      <div class="flex justify-between items-center bg-purple-700 p-4">
        <svg
          class="h-8 w-8 text-white"
          viewBox="0 0 24 24"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          stroke-linecap="round"
          stroke-linejoin="round"
          @click="goToBack"
        >
          <polyline points="15 18 9 12 15 6" />
        </svg>
        <span class="text-2xl font-extrabold text-white">{{ name }}</span>
      </div>
    </div>
    <div class="bg-white" v-if="flagSalesTrend">
    <!-- <div class="bg-white"> -->
      <SalesTrend :prices="prices" :dates="dates" />
    </div>
  </div>    
</template>

<script>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import { defineAsyncComponent, ref } from 'vue'

const SalesTrend = defineAsyncComponent(() =>
  import('@/components/charts/SalesTrend.vue')
)

export default {
  components: {
    SalesTrend,
  },
  setup() {
    const route = useRoute()
    const store = useStore()

    const name = ref('')
    const prices = ref([])
    const dates = ref([])
    const flagSalesTrend = ref(false)

    store.dispatch('requestDatePrice', route.query.pid)
      .then(res => {
        name.value = res.data.dateprice[0].productName

        for (let i = res.data.dateprice.length - 1; i >= 0; i--) {
          prices.value.push(res.data.dateprice[i].price)
          dates.value.push(res.data.dateprice[i].pdate)
        }
        flagSalesTrend.value = true
      })
      .catch(err => {
        console.log(err)
      })
      .finally(_ => {
        console.log(prices.value, dates.value)
      })

    const goToBack = function () {
      history.back()
    }

    return {
      goToBack,
      name,
      prices,
      dates,
      flagSalesTrend,
    }
  }
}
</script>
