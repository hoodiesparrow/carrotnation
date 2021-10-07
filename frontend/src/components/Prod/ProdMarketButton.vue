<template>
  <Menu as="div">
    <MenuButton class="flex items-center">
      <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24">
        <path
          d="M19 2c1.654 0 3 1.346 3 3v14c0 1.654-1.346 3-3 3h-14c-1.654 0-3-1.346-3-3v-14c0-1.654 1.346-3 3-3h14zm5 3c0-2.761-2.238-5-5-5h-14c-2.762 0-5 2.239-5 5v14c0 2.761 2.238 5 5 5h14c2.762 0 5-2.239 5-5v-14zm-13 12h-2v3h-2v-3h-2v-3h6v3zm-2-13h-2v8h2v-8zm10 5h-6v3h2v8h2v-8h2v-3zm-2-5h-2v3h2v-3z"
        />
      </svg>
      <span class="pl-1"> 마켓 </span>
    </MenuButton>

    <transition
      enter-active-class="transition duration-100 ease-out"
      enter-from-class="transform scale-95 opacity-0"
      enter-to-class="transform scale-100 opacity-100"
      leave-active-class="transition duration-75 ease-in"
      leave-from-class="transform scale-100 opacity-100"
      leave-to-class="transform scale-95 opacity-0"
    >
      <MenuItems class="absolute pr-2 origin-top-right bg-white shadow-lg">
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
    const sortOption = ref(["전체", "당근마켓", "번개장터", "중고나라"]);
    const onClickSort = function (idx) {
      store.commit("CHANGE_MARKET", idx);
      emit("market");
    };

    return {
      store,
      sortOption,
      onClickSort,
    };
  },
};
</script>
