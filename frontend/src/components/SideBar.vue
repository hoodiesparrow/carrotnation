<template>
  <TransitionRoot
    :show="open"
    enter="transition transform duration-300"
    enter-from="-translate-x-48"
    enter-to="translate-x-0"
    leave="transition transform duration-300"
    leave-from="translate-x-0"
    leave-to="-translate-x-48"
  >
    <div class="w-full h-full bg-black bg-opacity-50">
      <div class="bg-gray-50 h-full w-180px z-50">
        <div class="close_btn">
          <a href="#">
            <img
              src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjMuOTU0IDIxLjAzbC05LjE4NC05LjA5NSA5LjA5Mi05LjE3NC0yLjgzMi0yLjgwNy05LjA5IDkuMTc5LTkuMTc2LTkuMDg4LTIuODEgMi44MSA5LjE4NiA5LjEwNS05LjA5NSA5LjE4NCAyLjgxIDIuODEgOS4xMTItOS4xOTIgOS4xOCA5LjF6Ii8+PC9zdmc+"
              @click="$emit('closeSideBar')"
            />
          </a>
        </div>
      </div>

      <div>
        <SalesTrend v-bind:chartData="chartData" />
      </div>
    </div>
  </TransitionRoot>
</template>

<script lang="ts">
import { defineComponent, computed, defineAsyncComponent, ref } from "vue";
import { TransitionRoot, TransitionChild } from "@headlessui/vue";
const SalesTrend = defineAsyncComponent(() => import("@/components/charts/SalesTrend.vue"));
// interface State {
//   openNav: boolean
// }

export default defineComponent({
  components: {
    TransitionRoot,
    SalesTrend,
    // TransitionChild,
  },
  props: {
    show: {
      type: Boolean,
      default: false,
    },
    dateprice: {
      type: Array,
    },
  },
  setup(props) {
    const open = computed(() => props.show);
    let datelist = [];
    let pricelist = [];

    return { open };
  },
});
</script>
