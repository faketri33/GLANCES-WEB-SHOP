<template>
  <div class="container">
    <h1 class="mt-5 mb-5">Каталог</h1>
    <div class="row">
      <div class="col-md-12 col-lg-4">
        <div id="filterMenu" class="shadow p-3 rounded">
          <CharacteristicsList
            v-if="characteristics && maxPrice"
            v-bind:characteristics="characteristics"
            v-bind:maxPriceFinal="maxPrice"
            v-on:useFiltered="useFiltered"
          />
        </div>
      </div>
      <div class="col-lg-8" v-if="pages[currentPages]">
        <ProductCard
          v-for="product in pages[currentPages].content"
          v-bind:product="product"
          v-bind:likes="userStore.isLikedProduct(product.id)"
          v-bind:isBasketItem="userStore.isInBasketProduct(product.id)"
          v-on:addToFavorite="userStore.toFavorite"
          v-on:removeFromBasket="userStore.removeBasket"
          v-on:addToBasket="userStore.addBasket"
          :key="product.id"
        ></ProductCard>
        <div class="row">
          <div class="col-12">
            <nav aria-label="Page navigation">
              <ul class="pagination">
                <li
                  v-for="number in pages[0].totalPages"
                  :key="number"
                  class="page-item"
                >
                  <a
                    class="page-link"
                    v-on:click="changePages(number - 1)"
                    href="#"
                  >
                    {{ number }}
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { useRoute } from "vue-router";
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import CharacteristicsList from "@/entities/characteristics/ui/CharacteristicsList.vue";
import { PageableType } from "@/shared/pageable/pageableType";
import { Product } from "@/entities/product/model/Product";
import { CharacteristicsAction } from "@/entities/characteristics/api/model/actions";
import { Characteristics } from "@/entities/characteristics/model/Characteristics";
import { userStoreModule } from "@/entities/user/api/index.js";

const route = useRoute();

const currentPages = ref(0);
const pageSize = 20;
const categoriesId: string = route.params.id.toString();
const userStore = userStoreModule();
const maxPrice = ref(0);

const filterMaxPrice = ref(0);
const filterMinPrice = ref(0);

const pages = ref<PageableType<Product>[]>([]);
const characteristics = ref<Characteristics[]>();

const characteristicsForFilter = ref<Characteristics[]>([]);

const changePages = async (pageIndex: number) => {
  currentPages.value = pageIndex;
  if (!pages.value[pageIndex]) {
    const resp = await loadPages();
    pages.value[pageIndex] = resp;
  }
};

const useFiltered = async (
  selectedValues: Characteristics[],
  minPrice: number,
  maxPrice: number
) => {
  filterMaxPrice.value = maxPrice;
  filterMinPrice.value = minPrice;
  characteristicsForFilter.value = selectedValues;
  pages.value = [];
  const res = await loadPages();
  pages.value.push(res);
};

const loadPages = async (): Promise<PageableType<Product>> => {
  return await ProductActions.searchProduct(
    currentPages.value,
    pageSize,
    categoriesId,
    characteristicsForFilter.value,
    filterMinPrice.value,
    filterMaxPrice.value,
    ""
  );
};

onMounted(async () => {
  const res = await loadPages();
  characteristics.value =
    await CharacteristicsAction.loadCharacteristicsByProductCategories(
      categoriesId
    );
  pages.value.push(res);
  maxPrice.value = await ProductActions.findMaxPrice();
  console.log(maxPrice.value);
  return { pages, characteristics, maxPrice };
});
</script>

<style scoped></style>
