<template>
  <div class="container">
    <p>Выберите продукт при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQuery"
      :results="products.content"
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

    <form @submit.prevent="updateProduct">
      <div class="form-group">
        <label for="brand">Бренд</label>
        <select id="brand" class="form-control" v-model="selectedProduct.brand">
          <option v-for="brand in brands" :key="brand.id" :value="brand">
            {{ brand.name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="nameModel">Название модели</label>
        <input
          type="text"
          id="nameModel"
          class="form-control"
          v-model="selectedProduct.nameModel"
        />
      </div>
      <div class="form-group">
        <label for="categories">Категория</label>
        <select
          id="categories"
          class="form-control"
          v-model="selectedProduct.categories"
        >
          <option
            v-for="category in categories"
            :key="category.id"
            :value="category"
          >
            {{ category.name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <label for="description">Описание</label>
        <textarea
          id="description"
          class="form-control"
          v-model="selectedProduct.description"
        ></textarea>
      </div>
      <div class="form-group">
        <label for="price">Цена</label>
        <input
          type="number"
          id="price"
          class="form-control"
          v-model="selectedProduct.price"
        />
      </div>
      <div class="form-group">
        <label for="discount">Процент скидки</label>
        <input
          type="number"
          id="discount"
          class="form-control"
          v-model="selectedProduct.discount"
        />
      </div>
      <div class="form-group">
        <label for="quantity">Количество</label>
        <input
          type="number"
          id="quantity"
          class="form-control"
          v-model="selectedProduct.quantity"
        />
      </div>
      <div class="form-group">
        <label for="quantitySold">Количество продаж</label>
        <input
          type="number"
          id="quantitySold"
          class="form-control"
          v-model="selectedProduct.quantitySold"
        />
      </div>
      <p>Изображения</p>
      <label class="form-label m-0" for="upload-file"
        >Добавить изображения</label
      >
      <input
        class="form-control"
        type="file"
        accept="image/jpeg, image/gif, image/png"
        id="upload-file"
        placeholder="Изображение"
        multiple
        @change="handleFileSelect"
      />
      <div v-if="selectedProduct.image" class="form-group">
        <div class="images-list d-flex">
          <div
            class="list-item m-3 position-relative text-center"
            v-for="image in selectedProduct.image"
            :key="image.id"
            style="width: 200px; overflow: hidden"
          >
            <img
              style="max-width: 100%; max-height: 200px"
              :src="'http://localhost:9000/api/image/' + image.id"
              :alt="selectedProduct.nameModel"
            />
            <button class="delete-button" @click="removeImage(image.id)">
              Удалить
            </button>
          </div>
        </div>
      </div>
      <p>Характеристики</p>
      <div class="characteristics">
        <div
          class="wrap"
          v-for="(charactest, index) in selectedProduct.characteristics"
          :key="index"
        >
          <div class="inputs d-flex mt-2">
            <input v-model="charactest.name" class="input-group-text shadow" />
            <input
              v-model="charactest.value"
              class="input-group-text shadow ms-5"
            />
            <button
              type="button"
              @click="removeCharacteristics(charactest.name, charactest.value)"
              class="btn btn-outline-success ms-2"
            >
              Удалить
            </button>
          </div>
        </div>
        <button
          type="button"
          @click="addInputs"
          class="btn btn-success me-2 mt-2"
        >
          +
        </button>
        <button type="button" @click="delInputs" class="btn btn-success mt-2">
          -
        </button>
      </div>
      <button type="submit" class="btn btn-primary mt-2">
        Обновить продукт
      </button>
    </form>
  </div>
</template>

<script setup>
import SearchComponent from "@/widgets/SearchComponent.vue";
import { ref, watch, onMounted } from "vue";
import { ProductActions } from "../api/model/Actions";
import { CategoriesAction } from "@/entities/categories/api/model/actions";
import { BrandAction } from "@/entities/brand/api";

const products = ref([]);
const searchQuery = ref("");

var selectedFiles = [];
const categories = ref([]);
const brands = ref([]);

const selectedProduct = ref({
  brand: null,
  nameModel: "",
  categories: null,
  description: "",
  image: [],
  price: 0,
  quantity: 0,
  characteristics: [
    {
      id: "",
      name: "",
      value: "",
    },
  ],
});

const selectProduct = (product) => {
  selectedProduct.value = product;
};

const handleFileSelect = (event) => {
  selectedFiles = event.target.files;
};

const delInputs = () => {
  if (selectedProduct.value.characteristics.length === 1) return;
  selectedProduct.value.characteristics =
    selectedProduct.value.characteristics.slice(
      0,
      selectedProduct.value.characteristics.length - 1
    );
};

const addInputs = () => {
  const newInput1 = {
    id: "",
    name: "",
    value: "",
  };
  selectedProduct.value.characteristics.push(newInput1);
  console.log(selectedProduct.value.characteristics);
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

const updateProduct = () => {
  console.log(selectedProduct.value);
  console.log(selectedFiles);
  ProductActions.updateProduct(selectedProduct.value, selectedFiles);
};

const removeImage = (id) => {
  console.log(id);
  selectedProduct.value.image = selectedProduct.value.image.filter(
    (img) => img.id !== id
  );
};

const removeCharacteristics = (name, value) => {
  selectedProduct.value.characteristics =
    selectedProduct.value.characteristics.filter((chr) => {
      console.log(name, value);
      console.log(
        chr.name,
        chr.value,
        chr.name !== name && chr.value !== value
      );
      return !(chr.name === name && chr.value === value);
    });
};

watch(searchQuery, (newQuery) => {
  if (newQuery) {
    loadProduct(newQuery);
  } else {
    products.value = [];
  }
});

onMounted(async () => {
  categories.value = await CategoriesAction.loadCategories();
  brands.value = await BrandAction.loadBrand();
  return { categories, brands };
});
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
