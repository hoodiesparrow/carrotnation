<template>
  <div class="relative pt-1">
    <div class="flex mb-2 items-center justify-between">
      <div>
        <span class="text-xl font-semibold inline-block py-1 px-2 uppercase rounded-full text-indigo-600 bg-indigo-200">
          {{ loadingBar.status }}
        </span>
      </div>
      <div class="text-right">
        <span class="text-xs font-semibold inline-block text-indigo-600">
          {{ }}
        </span>
      </div>
    </div>
    <div class="overflow-hidden h-2 mb-4 text-xs flex rounded bg-indigo-200">
      <div :style="{ width: loadingBar.progress + '%'}" class="shadow-none flex flex-col text-center whitespace-nowrap text-white justify-center bg-indigo-500"></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
  props:{
    product: {
      type: Object,
    }
  },
  setup(props) {
    const loadingBar = ref({
      status: '거래 게시물로 리디렉션 중입니다.',
      progress: 30,
    })

    return {
      loadingBar
    }
  },
})
</script>
