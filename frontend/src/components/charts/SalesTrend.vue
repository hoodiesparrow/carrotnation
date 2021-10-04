<script>
import { defineComponent } from "vue";
import { Line } from "vue3-chart-v2";

export default defineComponent({
  name: "SalesTrend",
  extends: Line,
  props: {
    prices: {
      type: Object
    },
    dates: {
      type: Object
    },
  },
  mounted() {
    // Overwriting base render method with actual data.
    const canvas = this.$refs.canvas;

    const gradient = canvas.getContext("2d").createLinearGradient(0, 0, 0, 450);

    gradient.addColorStop(0, "rgba(255, 0,0, 0.5)");
    gradient.addColorStop(0.5, "rgba(255, 0, 0, 0.25)");
    gradient.addColorStop(1, "rgba(255, 0, 0, 0)");

    const gradient2 = canvas.getContext("2d").createLinearGradient(0, 0, 0, 450);

    gradient2.addColorStop(0, "rgba(0, 231, 255, 0.9)");
    gradient2.addColorStop(0.5, "rgba(0, 231, 255, 0.25)");
    gradient2.addColorStop(1, "rgba(0, 231, 255, 0)");

    this.renderChart({
      labels: this.dates,
      datasets: [
        {
          label: "시세",
          borderColor: "#FC2525",
          pointBackgroundColor: "white",
          borderWidth: 1,
          pointBorderColor: "white",
          tension: 0.3,
          backgroundColor: gradient,
          data: this.prices,
        },
      ],
    });
  },
});
</script>
