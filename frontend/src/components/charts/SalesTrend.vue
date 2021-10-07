<template>
  <p class="text-center text-lg">일별 평균 가격</p>
  <div>
    <vue3-chart-js v-bind="{ ...lineChart }" ref="canvas" />
  </div>
</template>

<script>
import Vue3ChartJs from "@j-t-mcc/vue3-chartjs";
import { onMounted, ref } from "vue";

export default {
  name: "App",
  components: {
    Vue3ChartJs,
  },
  props: {
    prices: {
      type: Object,
    },
    dates: {
      type: Object,
    },
  },
  setup(props) {
    const canvas = ref(null);
    const lineChart = {
      type: "line",
      data: {
        labels: props.dates,
        datasets: [
          {
            label: "가격",
            fill: true,
            tension: 0.3,
            borderColor: "white",
            pointBackgroundColor: "white",
            borderWidth: 1,
            pointBorderColor: "gray",

            // backgroundColor: ["#1abc9c", "#f1c40f", "#2980b9", "#34495e"],
            data: props.prices,
          },
        ],
      },
    };
    const asd = ref(null);
    onMounted(() => {
      asd.value = canvas.value.chartRef;
      const gradient = asd.value.getContext("2d").createLinearGradient(0, 0, 0, 450);

      gradient.addColorStop(0, "rgba(0, 231, 255, 0.9)");
      gradient.addColorStop(0.5, "rgba(0, 231, 255, 0.25)");
      gradient.addColorStop(1, "rgba(0, 231, 255, 0)");

      lineChart.data.datasets[0].backgroundColor = gradient;
      canvas.value.update();
    });

    return {
      lineChart,
      canvas,
      asd,
    };
  },
};
</script>
