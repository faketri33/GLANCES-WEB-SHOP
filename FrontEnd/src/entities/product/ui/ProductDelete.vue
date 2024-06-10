<script setup>
import SearchComponent from "@/widgets/SearchComponent.vue";
import { ref, watch } from "vue";
import { ProductActions } from "@/entities/product/api/model/Actions";

const products = ref([]);
const searchQuery = ref("");
const selectedProducts = ref();

const selectProduct = (product) => {
  selectedProducts.value = product;
};

const loadProduct = async (searchQuery) => {
  console.log(searchQuery);
  if (searchQuery.trim() !== "" && searchQuery.length >= 3)
    products.value = await ProductActions.searchProduct(
      0,
      10,
      null,
      null,
      null,
      null,
      searchQuery
    );
};

const deleteProduct = () => {
  ProductActions.deleteProduct(selectedProducts.value.id);
};

watch(searchQuery, (newQuery) => {
  if (newQuery) {
    loadProduct(newQuery);
  } else {
    products.value = [];
  }
});
</script>

<template>
  <div class="root container">
    <p>Выберите продукт при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQuery"
      :results="products?.content"
      class="w-100 mb-4"
    >
      <template v-slot:default="{ results }">
        <ul>
          <li
            class="list-group-item"
            v-for="product in results"
            :key="product.id"
          >
            <button @click="selectProduct(product)" class="btn wrap d-flex">
              <img
                :src="
                  'http://localhost:9000/api/image/' + product?.image[0]?.id
                "
                :alt="product.nameModel"
                style="width: 50px; height: 50px"
                class="mx-2"
              />
              {{ product.brand.name }} {{ product.nameModel }}
            </button>
          </li>
        </ul>
      </template>
    </search-component>
    <div v-if="selectedProducts" class="selected-product">
      <p>
        Выбран продукт
        {{ selectedProducts.brand.name + " " + selectedProducts.nameModel }}
      </p>
      <button class="btn btn-outline-danger" @click="deleteProduct">
        Удалить
      </button>
    </div>
  </div>
</template>

<style scoped></style>
