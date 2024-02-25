<template>
  <div class="container">
    <ThePageable
      v-bind:pages="product.getProducts(0)"
      v-bind:characteristics="characteristics.getCharacteristics"
    ></ThePageable>
  </div>
</template>

<script setup lang="ts">
import { storeModule } from "@/entities/product/api";
import { storeCharacteristicsModule } from "@/entities/characteristics/api";
import ThePageable from "@/widgets/pageable/ThePageable.vue";

const product = storeModule();
const characteristics = storeCharacteristicsModule();
</script>

<style scoped></style>
