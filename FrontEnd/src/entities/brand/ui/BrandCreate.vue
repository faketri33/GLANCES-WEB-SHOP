<template>
  <div class="container">
    <form
      @submit.prevent="BrandAction.save(brand)"
      class="form-inline d-inline-block"
    >
      <h2>Создать новый бренд</h2>
      <div class="wrap">
        <label for="brand">Название бренда</label>
        <input
          class="input-group-text shadow"
          id="brand"
          type="text"
          placeholder="Название бренда"
          minlength="2"
          v-model="brand.name"
        />
      </div>
      <button class="btn btn-success mt-2">Добавить</button>
    </form>
  </div>
</template>

<script setup>
import { BrandAction } from "@/entities/brand/api";

const brand = {
  id: "",
  name: "",
};
</script>
