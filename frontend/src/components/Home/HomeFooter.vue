<template>
  <div class="max-w-750px w-full absolute bottom-0 overflow-hidden">
    <transition
      enter-active-class="transition transform duration-300"
      enter-from-class="translate-y-full"
      enter-to-class="translate-y-0"
      leave-active-class="transition transform duration-300"
      leave-from-class="translate-y-0"
      leave-to-class="translate-y-full"
      @after-leave="showDiv = true"
    >
      <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" v-if="showButton" class="max-w-750px w-full flex justify-center my-5" @click="showButton = false">
        <path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm-2.033 16.01c.564-1.789 1.632-3.932 1.821-4.474.273-.787-.211-1.136-1.74.209l-.34-.64c1.744-1.897 5.335-2.326 4.113.613-.763 1.835-1.309 3.074-1.621 4.03-.455 1.393.694.828 1.819-.211.153.25.203.331.356.619-2.498 2.378-5.271 2.588-4.408-.146zm4.742-8.169c-.532.453-1.32.443-1.761-.022-.441-.465-.367-1.208.164-1.661.532-.453 1.32-.442 1.761.022.439.466.367 1.209-.164 1.661z"/>
      </svg>
    </transition>
    <transition
      enter-active-class="transition transform duration-300"
      enter-from-class="translate-y-full"
      enter-to-class="translate-y-0"
      leave-active-class="transition transform duration-300"
      leave-from-class="translate-y-0"
      leave-to-class="translate-y-full"
      @after-leave="showButton = true"
    >
      <div v-if="showDiv" class="bg-gray-900 bg-opacity-10 backdrop-blur" @click="showDiv = false">
        당근나라 번개시세 개발자 정보
        <br>
        이용약관 | 모든 상품들에 대해서 당근나라 번개시세는 거래 당사자가 아니며 거래에 관여하지 않고 어떠한 의무와 책무도 부담하지 않습니다.
      </div>
    </transition>
  </div>
</template>

<script>
import { ref } from 'vue'

export default {
  name: 'HomeFooter',
  setup() {
    const showButton = ref(true)
    const showDiv = ref(false)

    return { 
      showButton,
      showDiv,
     }
  }
}
</script>