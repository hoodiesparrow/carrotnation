<template>
  <!-- <div class="container max-w-750px flex-column items-center text-center bg-gradient-to-b from-purple-500 via-pink-400 py-20"> -->
  <div class="container max-w-750px text-center items-center h-full back">
    <div>
      <div class="w-full h-1/3 roof rounded-b-xl">
        <p class="text-white text-4xl py-10 font-bold">당근나라 번개시세</p>
        <p class="text-white text-xl pb-11 font-semibold">중고거래 시세조회를 한번에</p>
      </div>
    </div>

    <div class="flex justify-center py-10">
      <div class="w-full pb-3 bg-white shadow-2xl rounded">
        <p class="font-bold text-2xl py-5">휴대폰 시세 검색</p>
        <hr />
        <div>
          <HomeSelectButton />
        </div>
      </div>
    </div>

    <div class="flex justify-center py-5">
      <div class="w-full pb-3 bg-white shadow-2xl rounded">
        <div class="w-full max-w-md px-2 py-16 sm:px-0">
          <TabGroup>
            <TabList class="flex p-1 space-x-1 bg-blue-900/20 rounded-xl">
              <Tab
                v-for="category in Object.keys(categories)"
                as="template"
                :key="category"
                v-slot="{ selected }"
              >
                <button
                  :class="[
                    'w-full py-2.5 text-sm leading-5 font-medium text-blue-700 rounded-lg',
                    'focus:outline-none focus:ring-2 ring-offset-2 ring-offset-blue-400 ring-white ring-opacity-60',
                    selected
                      ? 'bg-white shadow'
                      : 'text-blue-100 hover:bg-white/[0.12] hover:text-white',
                  ]"
                >
                  {{ category }}
                </button>
              </Tab>
            </TabList>

            <TabPanels class="mt-2">
              <TabPanel
                v-for="(posts, idx) in Object.values(categories)"
                :key="idx"
                :class="[
                  'bg-white rounded-xl p-3',
                  'focus:outline-none focus:ring-2 ring-offset-2 ring-offset-blue-400 ring-white ring-opacity-60',
                ]"
              >
                <ul>
                  <li
                    v-for="post in posts"
                    key="post.id"
                    class="relative p-3 rounded-md hover:bg-coolGray-100"
                  >
                    <h3 class="text-sm font-medium leading-5">
                      {{ post.title }}
                    </h3>

                    <ul class="flex mt-1 space-x-1 text-xs font-normal leading-4 text-coolGray-500">
                      <li>{{ post.date }}</li>
                      <li>&middot;</li>
                      <li>{{ post.commentCount }} comments</li>
                      <li>&middot;</li>
                      <li>{{ post.shareCount }} shares</li>
                    </ul>

                    <a
                      href="#"
                      :class="[
                        'absolute inset-0 rounded-md',
                        'focus:z-10 focus:outline-none focus:ring-2 ring-blue-400',
                      ]"
                    />
                  </li>
                </ul>
              </TabPanel>
            </TabPanels>
          </TabGroup>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, defineAsyncComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { TabGroup, TabList, Tab, TabPanels, TabPanel } from "@headlessui/vue";
// import HomeItemList from './HomeItemList.vue'
const HomeChart = defineAsyncComponent(() => import("@/components/TempChart1.vue"));
const HomeSelectButton = defineAsyncComponent(
  () => import("@/components/Home/HomeSelectButton.vue")
);

export default defineComponent({
  name: "Home",
  components: {
    HomeChart,
    HomeSelectButton,
    TabGroup,
    TabList,
    Tab,
    TabPanels,
    TabPanel,
  },

  setup() {
    const router = useRouter();
    const store = useStore();

    var clickedTab = 1;
    const onClickItem = function () {
      router.push({
        name: "Prod",
      });
    };

    let categories = ref({
      Recent: [
        {
          id: 1,
          title: "Does drinking coffee make you smarter?",
          date: "5h ago",
          commentCount: 5,
          shareCount: 2,
        },
        {
          id: 2,
          title: "So you've bought coffee... now what?",
          date: "2h ago",
          commentCount: 3,
          shareCount: 2,
        },
      ],
      Popular: [
        {
          id: 1,
          title: "Is tech making coffee better or worse?",
          date: "Jan 7",
          commentCount: 29,
          shareCount: 16,
        },
        {
          id: 2,
          title: "The most innovative things happening in coffee",
          date: "Mar 19",
          commentCount: 24,
          shareCount: 12,
        },
      ],
      Trending: [
        {
          id: 1,
          title: "Ask Me Anything: 10 answers to your questions about coffee",
          date: "2d ago",
          commentCount: 9,
          shareCount: 5,
        },
        {
          id: 2,
          title: "The worst advice we've ever heard about coffee",
          date: "4d ago",
          commentCount: 1,
          shareCount: 2,
        },
      ],
    });

    return {
      onClickItem,
      categories,
      clickedTab,
    };
  },
});
</script>

<style scoped>
asd {
  color: #e3d8ff;
}
.roof {
  background: #673ab7; /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #512da8, #673ab7); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #512da8,
    #673ab7
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.back {
  background-color: #ececec;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='52' height='52' viewBox='0 0 52 52'%3E%3Cpath fill='%23ffffff' fill-opacity='0.23' d='M0 17.83V0h17.83a3 3 0 0 1-5.66 2H5.9A5 5 0 0 1 2 5.9v6.27a3 3 0 0 1-2 5.66zm0 18.34a3 3 0 0 1 2 5.66v6.27A5 5 0 0 1 5.9 52h6.27a3 3 0 0 1 5.66 0H0V36.17zM36.17 52a3 3 0 0 1 5.66 0h6.27a5 5 0 0 1 3.9-3.9v-6.27a3 3 0 0 1 0-5.66V52H36.17zM0 31.93v-9.78a5 5 0 0 1 3.8.72l4.43-4.43a3 3 0 1 1 1.42 1.41L5.2 24.28a5 5 0 0 1 0 5.52l4.44 4.43a3 3 0 1 1-1.42 1.42L3.8 31.2a5 5 0 0 1-3.8.72zm52-14.1a3 3 0 0 1 0-5.66V5.9A5 5 0 0 1 48.1 2h-6.27a3 3 0 0 1-5.66-2H52v17.83zm0 14.1a4.97 4.97 0 0 1-1.72-.72l-4.43 4.44a3 3 0 1 1-1.41-1.42l4.43-4.43a5 5 0 0 1 0-5.52l-4.43-4.43a3 3 0 1 1 1.41-1.41l4.43 4.43c.53-.35 1.12-.6 1.72-.72v9.78zM22.15 0h9.78a5 5 0 0 1-.72 3.8l4.44 4.43a3 3 0 1 1-1.42 1.42L29.8 5.2a5 5 0 0 1-5.52 0l-4.43 4.44a3 3 0 1 1-1.41-1.42l4.43-4.43a5 5 0 0 1-.72-3.8zm0 52c.13-.6.37-1.19.72-1.72l-4.43-4.43a3 3 0 1 1 1.41-1.41l4.43 4.43a5 5 0 0 1 5.52 0l4.43-4.43a3 3 0 1 1 1.42 1.41l-4.44 4.43c.36.53.6 1.12.72 1.72h-9.78zm9.75-24a5 5 0 0 1-3.9 3.9v6.27a3 3 0 1 1-2 0V31.9a5 5 0 0 1-3.9-3.9h-6.27a3 3 0 1 1 0-2h6.27a5 5 0 0 1 3.9-3.9v-6.27a3 3 0 1 1 2 0v6.27a5 5 0 0 1 3.9 3.9h6.27a3 3 0 1 1 0 2H31.9z'%3E%3C/path%3E%3C/svg%3E");
}
</style>
