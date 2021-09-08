<template>
  <!-- <div class="container max-w-750px flex-column items-center text-center bg-gradient-to-b from-purple-500 via-pink-400 py-20"> -->
  <div class="container max-w-750px text-center h-full">
    <div class="flex-column items-center text-white bg-purple-700 py-16">
      <p class="text-6xl font-extrabold pb-12">중고거래</p>
      <p class="text-3xl">Lorem ipsum dolor sit amet </p>
    </div>
    <div class="px-10">
      <div class="flex justify-center items-center bg-purple-100 rounded-lg h-28 my-5" @click="onClickItem">
        <!-- <homeItemList /> -->
        <p class="text-xl">아이폰 보기</p>
      </div>
      <div class="flex justify-center items-center bg-purple-100 rounded-lg h-28 my-5">
        <p class="text-xl">tbd</p>
      </div>
      <div class="flex justify-center items-center bg-purple-100 rounded-lg h-28 my-5">
        <p class="text-xl">tbd</p>
      </div>
      <div class="flex justify-center items-center bg-purple-100 rounded-lg h-28 my-5">
        <p class="text-xl">tbd</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex' 
// import HomeItemList from './HomeItemList.vue'

export default defineComponent({
  name: "Home",
  components: {
    // HomeItemList,
  },

  setup () {
    const router = useRouter()
    const store = useStore()
  
    const onClickItem = function () {
      router.push({
        name: 'Item'
      })
    }
    
    return { onClickItem }
  }
});
</script>