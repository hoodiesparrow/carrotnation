<template>
  <TransitionRoot 
    as="template"
    :show="open"
  > 
    <div class="h-full">
      <TransitionChild
        as="template"
        enter="transition transform duration-300"
        enter-from="opacity-0"
        enter-to="opacity-100"
        leave="transition transform duration-300"
        leave-from="opacity-100"
        leave-to="opacity-0"
      >
        <div class="w-750px h-full bg-black bg-opacity-50 z-10" @click="$emit('closeSideBar')">
        </div>
      </TransitionChild>
      <TransitionChild 
        as="template"
        enter="transition transform duration-300"
        enter-from="-translate-x-full"
        enter-to="translate-x-0"
        leave="transition transform duration-300"
        leave-from="translate-x-0"
        leave-to="-translate-x-full"
      >
        <div class="w-230px bg-gray-50 h-full z-50 fixed top-0">
          <div class="close_btn">
            <a href="#">
              <img
                src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjMuOTU0IDIxLjAzbC05LjE4NC05LjA5NSA5LjA5Mi05LjE3NC0yLjgzMi0yLjgwNy05LjA5IDkuMTc5LTkuMTc2LTkuMDg4LTIuODEgMi44MSA5LjE4NiA5LjEwNS05LjA5NSA5LjE4NCAyLjgxIDIuODEgOS4xMTItOS4xOTIgOS4xOCA5LjF6Ii8+PC9zdmc+"
                @click="$emit('closeSideBar')"
              />
            </a>
          </div>
          <div>메뉴</div>
          <div>
            <select class="w-full border bg-white rounded px-3 py-2 outline-none">
              <option class="py-1">Option 1</option>
              <option class="py-1">Option 2</option>
              <option class="py-1">Option 3</option>
            </select>
            <select class="w-full border bg-white rounded px-3 py-2 outline-none">
              <option class="py-1">Option 1</option>
              <option class="py-1">Option 2</option>
              <option class="py-1">Option 3</option>
            </select>
            <select class="w-full border bg-white rounded px-3 py-2 outline-none">
              <option class="py-1">Option 1</option>
              <option class="py-1">Option 2</option>
              <option class="py-1">Option 3</option>
            </select>
          </div>
        </div>
      </TransitionChild>
    </div>
  </TransitionRoot>
</template>

<script lang="ts">
import { defineComponent, computed } from "vue";
import { TransitionRoot, TransitionChild } from '@headlessui/vue' 
// interface State {
//   openNav: boolean
// }

export default defineComponent({
  components: {
    TransitionRoot, 
    TransitionChild,
  },
  props: {
    show: {
      type: Boolean,
      default: false,
    }
  },
  setup(props) {
    const open = computed(() => 
      props.show
    )

    return { open }
  }
});
</script>