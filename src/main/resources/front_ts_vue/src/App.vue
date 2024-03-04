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
import { onMounted } from "vue";

const userStore = userStoreModule();
onMounted(async () => {
  const login = localStorage.getItem("login");
  console.log(login);
  return login != null ? await userStore.loadUserByLogin(login) : null;
});
</script>
