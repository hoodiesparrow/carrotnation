<script>
import { defineComponent } from "vue";
import { Line } from "vue3-chart-v2";

export default defineComponent({
  extends: Line,
  props: {
    data: {
      type: Array,
      required: true,
    },
    label: {
      type: Array,
      required: true,
    },
    chartOptions: {
      type: Object,
      required: false,
    },
  },
  data(props) {
    return {
      chartData: {
        datasets: [
          {
            data: props.data,
          },
        ],
        labels: props.label,
      },
    };
  },
  mounted() {
    this.renderChart(this.chartData, this.chartOptions);
  },
});
</script>
