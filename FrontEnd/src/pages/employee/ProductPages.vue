<template>
  <div class="container">
    <div class="wrap ms-4 mb-4">
      <RouterLink to="/user/workspace/product/create" class="me-2"
        >Создать</RouterLink
      >
      <RouterLink to="/user/workspace/product/update" class="me-2"
        >Изменить</RouterLink
      >
      <RouterLink to="/user/workspace/product/delete" class="me-2"
        >Удалить</RouterLink
      >
    </div>
    <RouterView></RouterView>
  </div>
</template>

<script setup></script>
