<template>
  <div class="container">
    <p>Выберите категорию при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQuery"
      :results="categories?.content"
      class="w-100 mb-4"
    >
      <template v-slot:default="{ results }">
        <ul>
          <li
            class="list-group-item"
            v-for="categorie in results"
            :key="categorie.id"
          >
            <button
              @click="selectCategories(categorie)"
              class="btn wrap d-flex"
            >
              {{ categorie.name }}
            </button>
          </li>
        </ul>
      </template>
    </search-component>
    <h1>Категории</h1>
    <form
      v-if="selectedCategories.id"
      class="d-flex flex-column m-auto gap-2 w-50"
      @submit.prevent="deleteCategories"
    >
      <span>Название - {{ selectedCategories.name }}</span>
      <button class="btn btn-success">Удалить</button>
    </form>
  </div>
</template>
<script setup>
import { ref, watch } from "vue";
import { CategoriesAction } from "../api/model/actions";
import SearchComponent from "@/widgets/SearchComponent.vue";

const categories = ref([]);
const searchQuery = ref("");
const selectedCategories = ref({
  id: "",
  name: "",
  images: null,
});

const deleteCategories = () => {
  CategoriesAction.delete(selectedCategories.value.id);
};

const selectCategories = (categories) => {
  selectedCategories.value = categories;
};

const searchCategories = async (name) => {
  categories.value = await CategoriesAction.searchByName(name);
  console.log(categories.value);
};

watch(
  () => searchQuery.value,
  async (newVal, oldVal) => searchCategories(newVal)
);
</script>

<style scoped>
.list-item {
  position: relative;
}

.delete-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: rgba(255, 0, 0, 0.7);
  color: white;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
  opacity: 0;
  transition: opacity 0.3s ease;
}

.list-item:hover .delete-button {
  opacity: 1;
}
</style>
