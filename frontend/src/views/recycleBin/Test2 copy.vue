<template>
  <div class="w-full px-4 py-16">
    <button @click="tranFlag1=true">1t</button>
    <button @click="tranFlag1=false">1f</button>
    <button @click="tranFlag2=true">2t</button>
    <button @click="tranFlag2=false">2f</button>
    {{ tranFlag1 }}
    {{ tranFlag2 }}
    <div class="w-full max-w-md mx-auto">
      <RadioGroup v-model="selected" class="overflow-hidden">
        <TransitionRoot
          :show="tranFlag0"
          enter="transition transform duration-75"
          enter-from="translate-x-full"
          enter-to="translate-x-0"
          leave="transition transform duration-75"
          leave-from="translate-x-0"
          leave-to="-translate-x-full"
          @after-leave="tranFlag1=true"
        >
        <transition
          enter-active-class=""
          enter-from-class=""
          enter-to-class=""
          leave-active-class=""
          leave-from-class=""
          leave-to-class=""
        >
          <RadioGroupLabel class="sr-only">Server size</RadioGroupLabel>
          <div class="space-y-2">
            <RadioGroupOption
              as="template"
              v-for="plan in plans"
              :key="plan.name"
              :value="plan"
              v-slot="{ active, checked }"
              @click="tranFlag0=false"
            >
              <div
                :class="[
                  active
                    ? 'ring-2 ring-offset-2 ring-offset-blue-300 ring-white ring-opacity-60'
                    : '',
                  checked ? 'bg-blue-900 bg-opacity-75 text-white ' : 'bg-white ',
                ]"
                class="relative flex px-5 py-4 rounded-lg shadow-md cursor-pointer focus:outline-none"
              >
                <div class="flex items-center justify-between w-full">
                  <div class="flex items-center">
                    <div class="text-sm">
                      <RadioGroupLabel
                        as="p"
                        :class="checked ? 'text-white' : 'text-gray-900'"
                        class="font-medium"
                      >
                        0번
                      </RadioGroupLabel>
                      <RadioGroupDescription
                        as="span"
                        :class="checked ? 'text-blue-100' : 'text-gray-500'"
                        class="inline"
                      >
                        <span> {{ plan.ram }}/{{ plan.cpus }}</span>
                        <span aria-hidden="true"> &middot; </span>
                        <span>{{ plan.disk }}</span>
                      </RadioGroupDescription>
                    </div>
                  </div>
                  <div v-show="checked" class="flex-shrink-0 text-white">
                    <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none">
                      <circle
                        cx="12"
                        cy="12"
                        r="12"
                        fill="#fff"
                        fill-opacity="0.2"
                      />
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
        </TransitionRoot>
        <TransitionRoot
          :show="tranFlag1"
          enter="transition transform duration-75"
          enter-from="translate-x-full"
          enter-to="translate-x-0"
          leave="transition transform duration-75"
          leave-from="translate-x-0"
          leave-to="-translate-x-full"
          @after-leave="tranFlag2=true"
        >
          <RadioGroupLabel class="sr-only">Server size</RadioGroupLabel>
          <div class="space-y-2">
            <RadioGroupOption
              as="template"
              v-for="plan in plans"
              :key="plan.name"
              :value="plan"
              v-slot="{ active, checked }"
            >
              <div
                :class="[
                  active
                    ? 'ring-2 ring-offset-2 ring-offset-blue-300 ring-white ring-opacity-60'
                    : '',
                  checked ? 'bg-blue-900 bg-opacity-75 text-white ' : 'bg-white ',
                ]"
                class="relative flex px-5 py-4 rounded-lg shadow-md cursor-pointer focus:outline-none"
                @click="onClickT1"
              >
                <div class="flex items-center justify-between w-full">
                  <div class="flex items-center">
                    <div class="text-sm">
                      <RadioGroupLabel
                        as="p"
                        :class="checked ? 'text-white' : 'text-gray-900'"
                        class="font-medium"
                      >
                        1번
                      </RadioGroupLabel>
                      <RadioGroupDescription
                        as="span"
                        :class="checked ? 'text-blue-100' : 'text-gray-500'"
                        class="inline"
                      >
                        <span> {{ plan.ram }}/{{ plan.cpus }}</span>
                        <span aria-hidden="true"> &middot; </span>
                        <span>{{ plan.disk }}</span>
                      </RadioGroupDescription>
                    </div>
                  </div>
                  <div v-show="checked" class="flex-shrink-0 text-white">
                    <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none">
                      <circle
                        cx="12"
                        cy="12"
                        r="12"
                        fill="#fff"
                        fill-opacity="0.2"
                      />
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
        </TransitionRoot>
        <TransitionRoot
          :show="tranFlag2"
          enter="transition transform duration-300"
          enter-from="translate-x-full"
          enter-to="translate-x-0"
          leave="transition transform duration-300"
          leave-from="translate-x-0"
          leave-to="-translate-x-full"
          @after-leave="tranFlag1=true"
        >
          <RadioGroupLabel class="sr-only">Server size</RadioGroupLabel>
          <div class="space-y-2">
            <RadioGroupOption
              as="template"
              v-for="plan in plans"
              :key="plan.name"
              :value="plan"
              v-slot="{ active, checked }"
            >
              <div
                :class="[
                  active
                    ? 'ring-2 ring-offset-2 ring-offset-blue-300 ring-white ring-opacity-60'
                    : '',
                  checked ? 'bg-blue-900 bg-opacity-75 text-white ' : 'bg-white ',
                ]"
                class="relative flex px-5 py-4 rounded-lg shadow-md cursor-pointer focus:outline-none"
                @click="onClickT2"
              >
                <div class="flex items-center justify-between w-full">
                  <div class="flex items-center">
                    <div class="text-sm">
                      <RadioGroupLabel
                        as="p"
                        :class="checked ? 'text-white' : 'text-gray-900'"
                        class="font-medium"
                      >
                        2번
                      </RadioGroupLabel>
                      <RadioGroupDescription
                        as="span"
                        :class="checked ? 'text-blue-100' : 'text-gray-500'"
                        class="inline"
                      >
                        <span> {{ plan.ram }}/{{ plan.cpus }}</span>
                        <span aria-hidden="true"> &middot; </span>
                        <span>{{ plan.disk }}</span>
                      </RadioGroupDescription>
                    </div>
                  </div>
                  <div v-show="checked" class="flex-shrink-0 text-white">
                    <svg class="w-6 h-6" viewBox="0 0 24 24" fill="none">
                      <circle
                        cx="12"
                        cy="12"
                        r="12"
                        fill="#fff"
                        fill-opacity="0.2"
                      />
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
        </TransitionRoot>
      </RadioGroup>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from 'vue'
import {
  TransitionRoot,
  RadioGroup,
  RadioGroupLabel,
  RadioGroupDescription,
  RadioGroupOption,
} from '@headlessui/vue'

const plans = [
  {
    name: 'Startup',
    ram: '12GB',
    cpus: '6 CPUs',
    disk: '160 GB SSD disk',
  },
  {
    name: 'Business',
    ram: '16GB',
    cpus: '8 CPUs',
    disk: '512 GB SSD disk',
  },
  {
    name: 'Enterprise',
    ram: '32GB',
    cpus: '12 CPUs',
    disk: '1024 GB SSD disk',
  },
]

export default {
  components: {
    TransitionRoot,
    // TransitionChild,
    RadioGroup,
    RadioGroupLabel,
    RadioGroupDescription,
    RadioGroupOption,
  },

  setup() {
    const selected = ref(plans[0])
    const tranFlag0 = ref(true)
    const tranFlag1 = ref(false)
    const tranFlag2 = ref(false)

    const onClickT1 = () => {
      tranFlag1.value = false
    }
    const onClickT2 = () => {
      tranFlag2.value = false
    }

    return { 
      selected, 
      plans,
      tranFlag0,
      tranFlag1,
      tranFlag2,
      onClickT1,
      onClickT2,
    }
  },
}
</script>