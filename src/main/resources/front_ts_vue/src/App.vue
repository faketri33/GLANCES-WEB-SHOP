<template>
  <TheHeader v-bind:isLogin="userStore.isLogin" />
  <RouterView />
</template>

<style lang="scss">
* {
  padding: 0;
  box-sizing: border-box;
  font-family: "Roboto Light", sans-serif;
}
</style>

<script setup lang="ts">
import TheHeader from "@/widgets/TheHeader.vue";
import { userStoreModule } from "@/entities/user/api/index.js";
import { onMounted } from "vue";

const userStore = userStoreModule();
onMounted(async () => {
  const login = localStorage.getItem("login");
  console.log(login);
  return login ? await userStore.loadUser() : null;
});
</script>
