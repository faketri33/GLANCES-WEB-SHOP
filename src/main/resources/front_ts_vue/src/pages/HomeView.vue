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
    <div v-if="!dataLoading" class="product row justify-content-center">
      <ProductCardColumn
        class="col-xxl-2 col-xl-3 col-lg-3 col-md-4 col-sm-6 m-2 d-flex justify-content-center"
        v-for="product in productData.content"
        :key="product.id"
        v-bind:product="product"
        v-bind:isSmallWidth="true"
        v-bind:likes="isLiked(product.id)"
        v-on:addToFavorite="addToFavorite"
      />
    </div>
  </main>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import CategoriesCard from "@/entities/categories/ui/CategoriesCard.vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { CategoriesAction } from "@/entities/categories/api/model/actions";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Categories from "@/entities/categories/model/Categories";
import ProductCardColumn from "@/entities/product/ui/ProductCardColumn.vue";
import { userStoreModule } from "@/entities/user/api/index.js";

const dataLoading = ref(true);
const productData = ref<PageableType<Product>>();
const categoriesData = ref<Categories[]>();
const userStore = userStoreModule();

const isLiked = (id: number): boolean => {
  return userStore.getUser?.favoriteProduct?.some((r) => r.id === id);
};

const addToFavorite = (product: Product, operation: boolean) =>
  operation
    ? userStore.likeProduct(product)
    : userStore.dislikeProduct(product);

onMounted(async () => {
  productData.value = await ProductActions.loadProduct(0, 20);
  categoriesData.value = await CategoriesAction.loadCategories();
  dataLoading.value = false;
  return { productData, categoriesData };
});
</script>
