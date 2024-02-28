<template>
  <TheHeader v-bind:isLogin="userStore.isLogin" />
  <router-view />
</template>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Roboto Light", sans-serif;
}
</style>

<script setup lang="ts">
import TheHeader from "@/widgets/TheHeader.vue";
import { userStoreModule } from "@/entities/user/api/index.js";

const userStore = userStoreModule();
</script>
