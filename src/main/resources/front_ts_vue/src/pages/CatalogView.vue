<template>
  <div class="container-xl root">
    <div class="wrapper row">
      <div class="left-slide-bar col-2 col-sm-3">
        <CharacteristicsList
          v-if="characteristics"
          v-bind:characteristics="characteristics"
          v-on:useFiltered="useFiltered"
        />
      </div>
      <div class="content col" v-if="pages[currentPages]">
        <div class="list" style="min-height: 80vh">
          <ProductCard
            v-for="product in pages[currentPages].content"
            v-bind:product="product"
            v-bind:likes="false"
            :key="product.id"
          ></ProductCard>
        </div>
        <div class="align-content-center">
          <button
            class="btn btn-dark"
            v-for="index in pages[currentPages].totalPages"
            :key="index"
            @click="currentPages = index - 1"
          >
            {{ index }}
          </button>
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
import Characteristics from "@/entities/characteristics/model/Characteristics";

const route = useRoute();

const currentPages = ref(0);
const pageSize = 20;
const categoriesId: number = parseInt(route.params.id.toString());

const pages = ref<PageableType<Product>[]>([]);
const characteristics = ref<Characteristics[]>();

const filter = ref<Characteristics[]>([]);

const useFiltered = async (selectedValues: Characteristics[]) => {
  filter.value = selectedValues;
  pages.value = [];
  const res = await loadPages();
  pages.value.push(res);
};

const loadPages = async (): Promise<PageableType<Product>> => {
  console.log(filter.value);
  return filter.value.length > 0
    ? await ProductActions.loadProductByCategories(
        currentPages.value,
        pageSize,
        categoriesId,
        filter.value
      )
    : await ProductActions.loadProductByCategories(
        currentPages.value,
        pageSize,
        categoriesId
      );
};

onMounted(async () => {
  const res = await loadPages();
  characteristics.value =
    await CharacteristicsAction.loadCharacteristicsByProductCategories(
      categoriesId
    );
  pages.value.push(res);
  return { pages, characteristics };
});
</script>

<style scoped></style>
