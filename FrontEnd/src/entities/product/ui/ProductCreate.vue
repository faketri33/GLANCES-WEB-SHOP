<template>
  <div class="container">
    <form class="d-flex flex-column gap-3">
      <select v-model="brandSelect" class="form-select" id="brand-combobox">
        <option value="Выберите бренд" selected disabled>Выберите бренд</option>
        <option v-for="brand in brands" :key="brand.id" :value="brand.name">
          {{ brand.name }}
        </option>
      </select>
      <input
        v-model="product.nameModel"
        class="input-group-text shadow"
        id="nameModel"
        name="nameModel"
        type="text"
        placeholder="Название модели"
      />
      <select
        v-model="categoriesSelect"
        class="form-select"
        id="brand-combobox"
      >
        <option value="Выберите категорию" selected disabled>
          Выберите категорию
        </option>
        <option v-for="cat in categories" :value="cat.name" :key="cat.id">
          {{ cat.name }}
        </option>
      </select>
      <label class="form-label m-0" for="upload-file">Изображения</label>
      <input
        class="form-control"
        type="file"
        accept="image/jpeg, image/gif, image/png"
        id="upload-file"
        placeholder="Изображение"
        multiple
        @change="handleFileSelect"
      />
      <label for="price">Цена</label>
      <input
        v-model="product.price"
        class="input-group-text shadow"
        id="price"
        name="price"
        type="text"
        placeholder="Цена"
      />
      <label for="quantity">Количество</label>
      <input
        v-model="product.quantity"
        class="input-group-text shadow"
        id="quantity"
        name="quantity"
        type="text"
        placeholder="Количество"
      />
      <label for="description">Описание</label>
      <textarea
        id="description"
        class="form-control"
        v-model="product.description"
      ></textarea>
      <p>Характеристики</p>
      <div class="characteristics">
        <div
          class="wrap"
          v-for="(charactest, index) in characteristics"
          :key="index"
        >
          <div class="inputs d-flex mt-2">
            <input v-model="charactest.name" class="input-group-text shadow" />
            <input
              v-model="charactest.value"
              class="input-group-text shadow ms-5"
            />
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

      <button type="button" @click="click" class="btn btn-success">
        Добавить
      </button>
    </form>
  </div>
</template>
<script setup>
import { BrandAction } from "@/entities/brand/api";
import { CategoriesAction } from "@/entities/categories/api/model/actions";
import { onMounted, ref } from "vue";
import { ProductActions } from "../api/model/Actions";

const characteristics = ref([
  {
    name: "",
    value: "",
  },
]);

var selectedFiles = [];
const categories = ref([]);
const brands = ref([]);
const brandSelect = ref("Выберите бренд");
const categoriesSelect = ref("Выберите категорию");

const product = {
  brand: null,
  nameModel: "",
  categories: null,
  description: "",
  price: 0,
  quantity: 0,
  characteristicsRequestSet: characteristics.value,
};

const click = () => {
  product.brand = brands.value.find(
    (brand) => brandSelect.value === brand.name
  );
  product.categories = categories.value.find(
    (categories) => categories.name === categoriesSelect.value
  );

  ProductActions.saveProduct(product, selectedFiles);
};

const handleFileSelect = (event) => {
  selectedFiles = event.target.files;
};

const delInputs = () => {
  if (characteristics.value.length === 1) return;
  characteristics.value = characteristics.value.slice(
    0,
    characteristics.value.length - 1
  );
};

const addInputs = () => {
  const newInput1 = {
    name: "",
    value: "",
  };
  characteristics.value.push(newInput1);
  console.log(characteristics.value);
};

onMounted(async () => {
  categories.value = await CategoriesAction.loadCategories();
  brands.value = await BrandAction.loadBrand();
  return { categories, brands };
});
</script>
