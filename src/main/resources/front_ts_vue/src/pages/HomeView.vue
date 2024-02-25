<template>
  <main class="container">
    <h2 style="">Категории</h2>
    <div v-if="categoriesStore.getCategories" class="categories row">
      <CategoriesCard
        class="col"
        v-for="categories in categoriesStore.getCategories"
        :key="categories.id"
        v-bind:categories="categories"
      />
    </div>
    <h2>Акции</h2>
    <div v-if="productStore.getProducts(pages)" class="product row">
      <ProductCard
        class="col-xxl-2 col-xl-3 col-lg-3 col-md-4 col-sm-6 mt-2 d-flex justify-content-center"
        v-for="product in productStore.getProducts(pages).content"
        :key="product.id"
        v-bind:product="product"
        v-bind:isSmallWidth="true"
        v-bind:likes="false"
      />
    </div>
  </main>
</template>

<script setup lang="ts">
import { storeModule } from "@/entities/product/api";
import { storeModuleCategories } from "@/entities/categories/api";
import { onMounted } from "vue";
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import CategoriesCard from "@/entities/categories/ui/CategoriesCard.vue";

const pages = 0;

const productStore = storeModule();
const categoriesStore = storeModuleCategories();

onMounted(async () => {
  await productStore.loadProduct(pages, 20);
  await categoriesStore.loadCategories();
});
</script>
