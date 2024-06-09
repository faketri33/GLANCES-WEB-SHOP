<script setup lang="ts"></script>

<template>
  <div class="container">
    <div class="nav-menu">
      <router-link class="me-2" to="/user/workspace/categories/create"
        >Создать</router-link
      >
      <router-link class="me-2" to="/user/workspace/categories/delete"
        >Удалить</router-link
      >
      <router-link class="me-2" to="/user/workspace/categories/update"
        >Изменить</router-link
      >
    </div>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
