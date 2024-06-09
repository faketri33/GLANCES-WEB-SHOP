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
      class="d-flex flex-column m-auto gap-2 w-50"
      @submit.prevent="updateCategories"
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
      <input
        v-model="selectedCategories.name"
        type="text"
        class="input-group-text shadow"
        placeholder="Название категории"
        minlength="3"
      />
      <div v-if="selectedCategories.image" class="form-group">
        <div class="images-list d-flex">
          <div
            class="list-item m-3 position-relative text-center"
            style="width: 200px; overflow: hidden"
          >
            <img
              style="max-width: 100%; max-height: 200px"
              :src="
                'http://localhost:9000/api/image/' + selectedCategories.image.id
              "
              :alt="selectedCategories.name"
            />
            <button type="button" class="delete-button" @click="removeImage">
              Удалить
            </button>
          </div>
        </div>
      </div>
      <button type="submit" class="btn btn-success">Изменить</button>
    </form>
  </div>
</template>
<script setup>
import { ref, watch } from "vue";
import { CategoriesAction } from "../api/model/actions";
import SearchComponent from "@/widgets/SearchComponent.vue";

var selectedFiles = null;
const name = ref("");
const categories = ref([]);
const searchQuery = ref("");
const selectedCategories = ref({
  id: "",
  name: "",
  image: null,
});

const updateCategories = () => {
  if (selectedCategories.value && selectedCategories.value.name)
    CategoriesAction.update(selectedCategories.value, selectedFiles);
  else alert("Выберите категорию и заполните все поля.");
};

const handleFileSelect = (event) => {
  const files = event.target.files;
  selectedFiles = files;
};

const selectCategories = (categories) => {
  selectedCategories.value = categories;
};

const removeImage = (id) => {
  if (selectedCategories.value) {
    selectedCategories.value.image = null;
  }
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
