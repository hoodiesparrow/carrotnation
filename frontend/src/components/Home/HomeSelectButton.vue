<template>
  <div class="w-full p-2 py-8">
    <div class="w-full grid grid-cols-12 gap-4">
      <div class="col-span-2 flex justify-start pt-8">
        <svg
          version="1.1"
          id="Capa_1"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
          x="0px"
          y="0px"
          viewBox="0 0 309.143 309.143"
          style="enable-background: new 0 0 309.143 309.143"
          xml:space="preserve"
          class="w-12 h-12 cursor-pointer"
          @click="onClickBackward"
          v-if="!isTreeRoot"
        >
          <path
            style="fill: #231f20"
            d="M112.855,154.571L240.481,26.946c2.929-2.929,2.929-7.678,0-10.606L226.339,2.197
          C224.933,0.79,223.025,0,221.036,0c-1.989,0-3.897,0.79-5.303,2.197L68.661,149.268c-2.929,2.929-2.929,7.678,0,10.606
          l147.071,147.071c1.406,1.407,3.314,2.197,5.303,2.197c1.989,0,3.897-0.79,5.303-2.197l14.142-14.143
          c2.929-2.929,2.929-7.678,0-10.606L112.855,154.571z"
          />
        </svg>
      </div>

      <RadioGroup v-model="tree1Content" class="overflow-hidden col-span-8 px-1 pb-3">
        <transition
          enter-active-class="transition transform duration-300"
          :enter-from-class="enterFrom"
          :enter-to-class="enterTo"
          leave-active-class="transition transform duration-300"
          :leave-from-class="leaveFrom"
          :leave-to-class="leaveTo"
          @after-leave="tranFlag2 = true"
        >
          <div v-if="tranFlag1">
            <span class="text-xl font-bold">{{ treeTag }}</span>
            <RadioGroupLabel class="sr-only">{{ treeTag }}를 선택하세요.</RadioGroupLabel>
            <div class="space-y-2 py-5">
              <RadioGroupOption
                as="template"
                v-for="(option, idx) in tree1Content"
                :key="idx"
                :value="option"
                v-slot="{ active, checked }"
              >
                <div
                  :class="[
                    active
                      ? 'ring-2 ring-offset-2 ring-offset-blue-300 ring-white ring-opacity-60'
                      : '',
                    checked ? 'bg-blue-900 bg-opacity-75 text-white ' : 'bg-white ',
                  ]"
                  class="
                    relative
                    flex
                    px-4
                    md:px-8
                    py-4
                    rounded-lg
                    shadow-md
                    cursor-pointer
                    focus:outline-none
                  "
                  @click="onClickT1(idx)"
                >
                  <div class="flex items-center justify-between w-full">
                    <div class="flex items-center">
                      <div class="text-sm">
                        <RadioGroupLabel
                          as="p"
                          :class="checked ? 'text-white' : 'text-gray-900'"
                          class="font-medium"
                        >
                        </RadioGroupLabel>
                        <RadioGroupDescription
                          as="span"
                          :class="checked ? 'text-blue-100' : 'text-indigo-900'"
                          class="inline"
                        >
                          <span class="sm:text-llg md:text-xl font-bold">{{ option }}</span>
                        </RadioGroupDescription>
                      </div>
                    </div>
                    <div v-show="checked" class="flex-shrink-0 text-white">
                      <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none">
                        <circle cx="12" cy="12" r="12" fill="#fff" fill-opacity="0.2" />
                        <path
                          d="M7 13l3 3 7-7"
                          stroke="#fff"
                          stroke-width="1.5"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        />
                      </svg>
                    </div>
                  </div>
                </div>
              </RadioGroupOption>
            </div>
          </div>
        </transition>
        <transition
          enter-active-class="transition transform duration-300"
          :enter-from-class="enterFrom"
          :enter-to-class="enterTo"
          leave-active-class="transition transform duration-300"
          :leave-from-class="leaveFrom"
          :leave-to-class="leaveTo"
          @after-leave="tranFlag1 = true"
        >
          <div v-if="tranFlag2">
            <span class="text-xl font-bold">{{ treeTag }}</span>
            <RadioGroupLabel class="sr-only">{{ treeTag }}을 선택하세요.</RadioGroupLabel>
            <div class="space-y-2 py-5">
              <RadioGroupOption
                as="template"
                v-for="(option, idx) in tree2Content"
                :key="idx"
                :value="option"
                v-slot="{ active, checked }"
              >
                <div
                  :class="[
                    active
                      ? 'ring-2 ring-offset-2 ring-offset-blue-300 ring-white ring-opacity-60'
                      : '',
                    checked ? 'bg-blue-900 bg-opacity-75 text-white ' : 'bg-white ',
                  ]"
                  class="
                    relative
                    flex
                    px-4
                    md:px-8
                    py-4
                    rounded-lg
                    shadow-md
                    cursor-pointer
                    focus:outline-none
                  "
                  @click="onClickT2(idx)"
                >
                  <div class="flex items-center justify-between w-full">
                    <div class="flex items-center">
                      <div class="text-sm">
                        <RadioGroupLabel
                          as="p"
                          :class="checked ? 'text-white' : 'text-gray-900'"
                          class="font-medium"
                        >
                        </RadioGroupLabel>
                        <RadioGroupDescription
                          as="span"
                          :class="checked ? 'text-blue-100' : 'text-indigo-900'"
                          class="inline"
                        >
                          <span class="sm:text-llg md:text-xl font-bold">{{ option }}</span>
                        </RadioGroupDescription>
                      </div>
                    </div>
                    <div v-show="checked" class="flex-shrink-0 text-white">
                      <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none">
                        <circle cx="12" cy="12" r="12" fill="#fff" fill-opacity="0.2" />
                        <path
                          d="M7 13l3 3 7-7"
                          stroke="#fff"
                          stroke-width="1.5"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                        />
                      </svg>
                    </div>
                  </div>
                </div>
              </RadioGroupOption>
            </div>
          </div>
        </transition>
      </RadioGroup>
    </div>
  </div>
</template>

<script>
import get from "lodash/get";
import { useRouter } from "vue-router";
import { computed, reactive, ref } from "vue";
import { useStore } from "vuex";
import {
  // TransitionRoot,
  RadioGroup,
  RadioGroupLabel,
  RadioGroupDescription,
  RadioGroupOption,
} from "@headlessui/vue";

const plans = [
  {
    name: "Startup",
    ram: "12GB",
    cpus: "6 CPUs",
    disk: "160 GB SSD disk",
  },
  {
    name: "Business",
    ram: "16GB",
    cpus: "8 CPUs",
    disk: "512 GB SSD disk",
  },
  {
    name: "Enterprise",
    ram: "32GB",
    cpus: "12 CPUs",
    disk: "1024 GB SSD disk",
  },
];

export default {
  components: {
    // TransitionRoot,
    // TransitionChild,
    RadioGroup,
    RadioGroupLabel,
    RadioGroupDescription,
    RadioGroupOption,
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    const selected = ref(plans[0]);
    const currentTran = ref(1); // 이게 필요한가..
    const tranFlag1 = ref(true);
    const tranFlag2 = ref(false);

    const enterFrom = ref("translate-x-full");
    const enterTo = ref("translate-x-0");
    const leaveFrom = ref("translate-x-0");
    const leaveTo = ref("-translate-x-full");

    const tree1Depth = ref(["휴대폰"]);
    const tree1 = ref(get(store.getters["getCategoryData"], tree1Depth.value));
    const tree1Content = computed(() => Object.keys(tree1.value).slice(1));
    // const tree1Content = ref(Object.keys(tree1.value).slice(1))

    const tree2Depth = ref(["휴대폰"]);
    const tree2 = ref([]);
    const tree2Content = computed(() => Object.keys(tree2.value).slice(1));
    const isTreeRoot = computed(() => {
      return tree1Depth.value.length === 1 ? true : false;
    });

    const treeTag = computed(() => {
      // 얘도 분리해야 도중에 안바뀜..but 빨리 지나가서 보이지는 않네 ㅋㅋ
      return tranFlag1.value
        ? tree1.value[Object.keys(tree1.value)[0]]
        : tree2.value[Object.keys(tree2.value)[0]];
    });
    // 내일 할것
    // 1. 데이터 집어넣기
    // 2. depth 눌러서 뒤로가기 하면 3번째 그룹을 만드는 것이 아니라
    // 기존의 1, 2번의 클래스를 객체로 잡아둔 뒤 뒤로가기 누르면 객체를 수정하고, 다시 객체를 원복
    // 그러면 원복하는 로직은 어떻게 하지... timeout? 아니면 바로 수정해 버리면 어떻게 되는지 확인
    // 일단은 당장 떠오르는 방법은 없다. 아니면 @click에 그 객체를 수정하는 코드를 달아보리기
    // 버튼부터 끝내놓고 api 관련 작업을 하던 뭘 하던 해보자
    // depth 버튼 만드는것도 문제네... 아마도 .splice 사용하면 될 것 같긴 해 ㅇㅇ
    // v-for에서 key를 임의idx로 잡아놓고 @click에서 인자로 넘기면 될듯

    const onClickBackward = () => {
      if (tree1Depth.value.length !== 1) {
        tree1Depth.value.pop();
        tree2Depth.value.pop();
        // 1번 트리를 사용중인 경우
        enterFrom.value = "-translate-x-full";
        enterTo.value = "translate-x-0";
        leaveFrom.value = "translate-x-0";
        leaveTo.value = "translate-x-full";

        if (tranFlag1.value === true) {
          tree2.value = get(store.getters["getCategoryData"], tree2Depth.value);
          tranFlag1.value = false;
        } else {
          tree1.value = get(store.getters["getCategoryData"], tree1Depth.value);
          tranFlag2.value = false;
        }
      }
    };

    const onClickT1 = (idx) => {
      enterFrom.value = "translate-x-full";
      enterTo.value = "translate-x-0";
      leaveFrom.value = "translate-x-0";
      leaveTo.value = "-translate-x-full";
      tree1Depth.value.push(tree1Content.value[idx]);
      tree2Depth.value.push(tree1Content.value[idx]);
      tree2.value = get(store.getters["getCategoryData"], tree2Depth.value);
      // tag가 없으면? >>> router push
      if (Object.keys(tree2.value).indexOf("tag") === -1) {
        router.push({
          name: "Prod",
          query: {
            pid: tree2.value.pid,
          },
        });
      } else {
        tranFlag1.value = false;
      }
    };
    const onClickT2 = (idx) => {
      enterFrom.value = "translate-x-full";
      enterTo.value = "translate-x-0";
      leaveFrom.value = "translate-x-0";
      leaveTo.value = "-translate-x-full";
      tree1Depth.value.push(tree2Content.value[idx]);
      tree2Depth.value.push(tree2Content.value[idx]);
      tree1.value = get(store.getters["getCategoryData"], tree1Depth.value);
      // tag가 없으면? >>> router push
      if (Object.keys(tree1.value).indexOf("tag") === -1) {
        router.push({
          name: "Prod",
          query: {
            pid: tree1.value.pid,
          },
        });
      } else {
        tranFlag2.value = false;
      }
    };

    return {
      selected,
      plans,
      tranFlag1,
      tranFlag2,
      onClickBackward,
      onClickT1,
      onClickT2,
      currentTran,
      tree1,
      tree1Depth,
      tree1Content,
      tree2,
      tree2Depth,
      tree2Content,
      isTreeRoot,
      treeTag,
      router,
      enterFrom,
      enterTo,
      leaveFrom,
      leaveTo,
    };
  },
};
</script>
