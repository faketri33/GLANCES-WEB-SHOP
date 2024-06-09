<template>
  <div class="root">
    <h1>Категории</h1>
    <form
      class="d-flex flex-column m-auto gap-2 w-50"
      @submit.prevent="saveCategories"
    >
      <input
        class="form-control"
        type="file"
        accept="image/jpeg, image/gif, image/png"
        id="upload-file"
        placeholder="Изображение"
        required
        @change="handleFileSelect"
      />
      <input
        v-model="name"
        type="text"
        class="input-group-text shadow"
        placeholder="Название категории"
        minlength="3"
        required
      />
      <button class="btn btn-success">Добавить</button>
    </form>
  </div>
</template>
<script setup>
import { ref } from "vue";
import { CategoriesAction } from "../api/model/actions";

var selectedFiles = null;
const name = ref("");

const saveCategories = () => {
  if (!selectedFiles || name.value.trim().length === 0) {
    alert("Заполните все поля.");
  } else CategoriesAction.saveCategories(name.value, selectedFiles);
};

const handleFileSelect = (event) => {
  // Получить выбранные файлы
  const files = event.target.files;
  // Сохранить файлы в переменной
  selectedFiles = files;
};
</script>
