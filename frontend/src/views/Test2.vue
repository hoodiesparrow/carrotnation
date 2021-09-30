<template>
  <div id="app">
    <button @click="scrollToBottom">scroll to bottom</button>
    <div id="container" style="height: 200px; overflow-y: scroll">
      <p v-for="n in 100" :key="n">{{n}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  methods: {
    scrollToBottom() {
      const container = this.$el.querySelector("#container");
      container.scrollTop = container.scrollHeight;
    }
  }
};
</script>