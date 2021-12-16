<template>
  <p class="text-center text-lg">가격 구간 별 게시글 수</p>
  <div>
    <vue3-chart-js v-bind="{ ...barChart }" ref="canvas" />
  </div>
</template>

<script>
import Vue3ChartJs from "@j-t-mcc/vue3-chartjs";
import { onMounted, ref } from 'vue'

export default {
  name: "App",
  components: {
    Vue3ChartJs,
  },
  props: {
    prices: {
      type: Object
    },
    dates: {
      type: Object
    },
  },
  setup(props) {
    const canvas = ref(null)
    const barChart = {
      type: "bar",
      data: {
        labels: props.dates,
        datasets: [
          {
            label: '게시글 수',
            // backgroundColor: ["#1abc9c", "#f1c40f", "#2980b9", "#34495e"],
            data: props.prices,
          },
        ],
      },
    };
    const asd = ref(null)
    onMounted(() => {
      asd.value = canvas.value.chartRef
      const gradient = asd.value.getContext('2d').createLinearGradient(0, 0, 0, 450)
      console.log(gradient)
      
      gradient.addColorStop(0, "rgba(255, 0,0, 0.5)");
      gradient.addColorStop(0.5, "rgba(255, 0, 0, 0.25)");
      gradient.addColorStop(1, "rgba(255, 0, 0, 0)");

      barChart.data.datasets[0].backgroundColor = gradient
      canvas.value.update()
    })

    return {
      barChart,
      canvas,
      asd,
    };
  },
};
</script>
