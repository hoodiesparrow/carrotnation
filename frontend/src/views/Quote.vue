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
    <div class="bg-white" v-if="flagSalesByPrice">
    <!-- <div class="bg-white"> -->
      <SalesByPrice :prices="byPriceCounts" :dates="byPriceIntervals" />
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import { defineAsyncComponent, ref, reactive, onBeforeUnmount } from 'vue'

const SalesTrend = defineAsyncComponent(() =>
  import('@/components/charts/SalesTrend.vue')
)
const SalesByPrice = defineAsyncComponent(() =>
  import('@/components/charts/SalesByPrice.vue')
)
const stepArr = ['1Step', '2Step', '3Step', '4Step', '5Step']

export default {
  components: {
    SalesTrend,
    SalesByPrice,
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
        console.log(prices.value) 
        console.log(dates.value) 
      })

    const goToBack = function () {
      history.back()
    }

    // byPrice
    const date = reactive(new Date(Date.now() - (60 * 60 * 1000)))
    const cycle = ref('')
    cycle.value = `${String(date.getFullYear()).slice(2)}${String(date.getMonth() + 1).padStart(2, '0')}${String(date.getDate()).padStart(2, '0')}${String(date.getHours()).padStart(2, '0')}`


    const bypriceQuery = ref({
      pid: route.query.pid,
      cycle: cycle.value
    })

    const byPriceCounts = ref([])
    const byPriceIntervals = ref([])
    const flagSalesByPrice = ref(false)
    store.dispatch('requestByPrice', bypriceQuery.value)
      .then(res => {
        for ( let i = 0; i < 5; i++) {
          byPriceCounts.value.push(res.data[stepArr[i]].count)
          byPriceIntervals.value.push(`${res.data[stepArr[i]].min}~${res.data[stepArr[i]].max}`)
        }
        console.log(byPriceCounts.value)
        console.log(byPriceIntervals.value)
        flagSalesByPrice.value = true
      })
    
    onBeforeUnmount(() => {
      flagSalesTrend.value = false
      flagSalesByPrice.value = false
      date.value = []
      prices.value = []
      byPriceCounts.value = []
      byPriceIntervals.value = []
    })

    return {
      flagSalesByPrice,
      byPriceCounts,
      byPriceIntervals,
      bypriceQuery,
      date,
      cycle,
      goToBack,
      name,
      prices,
      dates,
      flagSalesTrend,
    }
  }
}
</script>
