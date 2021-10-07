<template>
  <Menu as="div">
    <MenuButton class="flex items-center">
      <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
        <path
          d="M8 10v4h4l-6 7-6-7h4v-4h-4l6-7 6 7h-4zm16 5h-10v2h10v-2zm0 6h-10v-2h10v2zm0-8h-10v-2h10v2zm0-4h-10v-2h10v2zm0-4h-10v-2h10v2z"
        />
      </svg>
      <span class="pl-1"> 정렬 </span>
    </MenuButton>

    <transition
      enter-active-class="transition duration-100 ease-out"
      enter-from-class="transform scale-95 opacity-0"
      enter-to-class="transform scale-100 opacity-100"
      leave-active-class="transition duration-75 ease-in"
      leave-from-class="transform scale-100 opacity-100"
      leave-to-class="transform scale-95 opacity-0"
    >
      <MenuItems class="absolute right-0 pr-2 origin-top-right bg-white shadow-lg">
        <MenuItem v-for="(sort, idx) in sortOption" :key="idx" @click="onClickSort(idx)">
          <button class="text-gray-900 flex rounded-md items-center w-full px-2 py-2 text-sm">
            <span class="text-md font-bold">{{ sort }}</span>
          </button>
        </MenuItem>
      </MenuItems>
    </transition>
  </Menu>
</template>

<script>
import { ref } from "vue";
import { useStore } from "vuex";
import { Menu, MenuButton, MenuItems, MenuItem } from "@headlessui/vue";

export default {
  components: {
    Menu,
    MenuButton,
    MenuItems,
    MenuItem,
  },
  setup(props, { emit }) {
    const store = useStore();
    const sortOption = ref(["최신 순", "오래된 순", "가격 높은 순", "가격 낮은 순"]);
    const onClickSort = function (idx) {
      store.commit("CHANGE_SORT", idx + 1);
      emit("sort");
    };

    return {
      store,
      sortOption,
      onClickSort,
    };
  },
};
</script>
