<template>
  <div class="root container">
    <div class="categories-list d-flex" v-if="hasData">
      <CategoriesCard
        v-for="cat in categories"
        :key="cat.id"
        v-bind:categories="cat"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import Categories from "@/entities/categories/model/Categories";
import { CategoriesAction } from "@/entities/categories/api/model/actions";
import CategoriesCard from "@/entities/categories/ui/CategoriesCard.vue";

const hasData = ref(false);
const categories = ref<Categories[]>();
onMounted(async () => {
  categories.value = await CategoriesAction.loadCategories();
  hasData.value = true;
  return { categories, hasData };
});
</script>

<style scoped></style>
