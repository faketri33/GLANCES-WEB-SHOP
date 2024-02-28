<template>
  <div class="container root row">
    <div class="left-slide-bar col">
      <CharacteristicsList v-bind:characteristics="characteristics" />
    </div>
    <div class="content col">
      <div class="list">
        <ProductCard
          v-for="product in pages.content"
          v-bind:product="product"
          v-bind:likes="false"
          :key="product.id"
        ></ProductCard>
      </div>
      <button
        class="btn btn-dark"
        v-for="index in pages.totalPages"
        :key="index"
      >
        {{ index }}
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Characteristics from "@/entities/characteristics/model/Characteristics";
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import CharacteristicsList from "@/entities/characteristics/ui/CharacteristicsList.vue";
import { defineProps } from "vue";

defineProps<{
  pages: PageableType<Product>;
  characteristics: Array<Characteristics>;
}>();
</script>

<style scoped></style>
