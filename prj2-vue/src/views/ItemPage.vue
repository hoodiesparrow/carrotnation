<template>
  <div class="container max-w-750px">
    <div class="bg-purple-700 flex justify-between py-6">
      <div class="text-2xl">
        <button class="rounded-md bg-black text-white" type="submit">햄</button>
      </div>
      <div class="text-2xl font-semibold text-white pr-1">
        애플 아이폰 12 매드맥스 탐사수
      </div>
    </div>
    <div class="py-2 border-b-2 border-gray-900 flex justify-between">
      <div class="flex-column">
        <p>평균가 123123</p>
        <p>최저가 123123</p>
      </div>
      <div class="">
        chart.js
      </div>
    </div>
    <div class="text-right">123건</div>
    <div class="flex-column">
      <!-- 여기도 모양만 살짝 잡아주기 : component화 해야함 -->
      <!-- 그리드도 써볼까;ㅣ; -->
      <div class="flex justify-between bg-purple-100 rounded mb-6
        transition 
        hover:bg-purple-700 hover:text-white">
        <div class="flex-none">사진</div>
        <div class="flex-column justify-between flex-auto pl-6 pr-4">
          <p>제목제목제목제목</p>
          <div class="flex justify-between">
            <p>가격가격가격</p>
            <p>출처출처</p>
          </div>
        </div>
      </div>
      
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useStore } from 'vuex'

export default defineComponent({
  setup() {
    const store = useStore()
  }, 
})
</script>

<style>

</style>