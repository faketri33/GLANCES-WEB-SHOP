<template>
  <div class="container">
    <p>Выберите бренд при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQuery"
      :results="brands?.content"
      class="w-100 mb-4"
    >
      <template v-slot:default="{ results }">
        <ul>
          <li class="list-group-item" v-for="brand in results" :key="brand.id">
            <button @click="selectBrand(brand)" class="btn wrap d-flex">
              {{ brand.name }}
            </button>
          </li>
        </ul>
      </template>
    </search-component>
    <form
      @submit.prevent="BrandAction.update(selectedBrand)"
      class="form-inline d-inline-block"
    >
      <h2>Изменить бренд</h2>
      <div class="wrap">
        <label for="brand">Название бренда</label>
        <input
          class="input-group-text shadow"
          id="brand"
          type="text"
          minlength="2"
          placeholder="Название бренда"
          v-model="selectedBrand.name"
          required
        />
      </div>
      <button class="btn btn-success mt-2">Обновить</button>
    </form>
  </div>
</template>

<script setup>
import SearchComponent from "@/widgets/SearchComponent.vue";
import { ref, watch } from "vue";
import { BrandAction } from "@/entities/brand/api";

const brands = ref([]);
const searchQuery = ref("");
const selectedBrand = ref({
  id: "",
  name: "",
});

const selectBrand = (brand) => {
  selectedBrand.value = brand;
};

const searchBrand = async (name) => {
  brands.value = await BrandAction.searchByName(name);
  console.log(brands.value);
};

watch(
  () => searchQuery.value,
  async (newVal, oldVal) => searchBrand(newVal)
);
</script>
