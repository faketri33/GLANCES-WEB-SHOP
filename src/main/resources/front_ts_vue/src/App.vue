<template>
  <TheHeader></TheHeader>
  <router-view />
</template>

<style lang="scss">
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
<script lang="ts">
import TheHeader from "@/widgets/TheHeader.vue";

export default {
  components: { TheHeader },
};
</script>
