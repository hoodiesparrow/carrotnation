<template>
  <div>
    <a href="#">
      <img
        src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjQgNmgtMjR2LTRoMjR2NHptMCA0aC0yNHY0aDI0di00em0wIDhoLTI0djRoMjR2LTR6Ii8+PC9zdmc+"
    /></a>
  </div>
  <div class="left-side-bar">
    <div class="close_btn">
      <a href="#">
        <img
          src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgdmlld0JveD0iMCAwIDI0IDI0Ij48cGF0aCBkPSJNMjMuOTU0IDIxLjAzbC05LjE4NC05LjA5NSA5LjA5Mi05LjE3NC0yLjgzMi0yLjgwNy05LjA5IDkuMTc5LTkuMTc2LTkuMDg4LTIuODEgMi44MSA5LjE4NiA5LjEwNS05LjA5NSA5LjE4NCAyLjgxIDIuODEgOS4xMTItOS4xOTIgOS4xOCA5LjF6Ii8+PC9zdmc+"
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
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({});
</script>

<style>
.left-side-bar {
  background-color: #efefef;
  height: 100%;
  width: 180px;
  position: fixed;
  left: -50%;
  transition: left 0.3s;
  z-index: 9999;
}
</style>
