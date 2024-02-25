<template>
  <TheHeader></TheHeader>
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

<script lang="ts">
import TheHeader from "@/widgets/TheHeader.vue";

export default {
  name: "App",
  components: { TheHeader },
};
</script>
