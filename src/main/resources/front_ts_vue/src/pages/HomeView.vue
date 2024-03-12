<template>
  <main class="container">
    <h2 style="">Категории</h2>
    <div v-if="!dataLoading" class="categories row">
      <CategoriesCard
        class="col-12 col-md-5"
        v-for="categories in categoriesData"
        :key="categories.id"
        v-bind:categories="categories"
      />
    </div>
    <h2>Акции</h2>
    <div v-if="!dataLoading" class="product row justify-content-center">
      <ProductCard
        class="m-2"
        v-for="product in productData.content"
        :key="product.id"
        v-bind:product="product"
        v-bind:typeCard="false"
        v-bind:likes="userStore.isLikedProduct(product.id)"
        v-on:addToFavorite="userStore.likeProduct(product)"
        v-on:toBasket="userStore.addToBasket(product)"
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
import { Categories } from "@/entities/categories/model/Categories";
import { userStoreModule } from "@/entities/user/api/index.js";
import ProductCard from "@/entities/product/ui/ProductCard.vue";

const dataLoading = ref(true);
const productData = ref<PageableType<Product>>();
const categoriesData = ref<Categories[]>();
const userStore = userStoreModule();

onMounted(async () => {
  productData.value = await ProductActions.loadProduct(0, 20);
  categoriesData.value = await CategoriesAction.loadCategories();
  dataLoading.value = false;
  return { productData, categoriesData };
});
</script>
