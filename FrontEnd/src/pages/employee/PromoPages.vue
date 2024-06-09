<script setup lang="ts"></script>

<template>
  <div class="container">
    <div class="wrap mb-2">
      <router-link to="/user/workspace/promo/create" class="me-2"
        >Создать</router-link
      >
      <router-link to="/user/workspace/promo/update" class="me-2"
        >Изменить</router-link
      >
      <router-link to="/user/workspace/promo/update">Удалить</router-link>
    </div>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
