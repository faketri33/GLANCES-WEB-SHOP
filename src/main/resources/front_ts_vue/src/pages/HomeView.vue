<template>
  <main class="container">
    <h2 style="">Категории</h2>
    <div v-if="!dataLoading" class="categories row">
      <CategoriesCard
        class="col"
        v-for="categories in categoriesData"
        :key="categories.id"
        v-bind:categories="categories"
      />
    </div>
    <h2>Акции</h2>
    <div v-if="!dataLoading" class="product row">
      <ProductCard
        class="col-xxl-2 col-xl-3 col-lg-3 col-md-4 col-sm-6 mt-2 d-flex justify-content-center"
        v-for="product in productData.content"
        :key="product.id"
        v-bind:product="product"
        v-bind:isSmallWidth="true"
        v-bind:likes="false"
      />
    </div>
  </main>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import CategoriesCard from "@/entities/categories/ui/CategoriesCard.vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { CategoriesAction } from "@/entities/categories/api/model/actions";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Categories from "@/entities/categories/model/Categories";

const dataLoading = ref(true);
const productData = ref<PageableType<Product>>();
const categoriesData = ref<Categories[]>();

onMounted(async () => {
  productData.value = await ProductActions.loadProduct(0, 20);
  categoriesData.value = await CategoriesAction.loadCategories();
  dataLoading.value = false;
  console.log("hello");
  return { productData, categoriesData };
});
</script>
