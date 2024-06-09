<script setup lang="ts"></script>

<template>
  <div class="container">
    <div class="nav-menu">
      <router-link class="me-2" to="/user/workspace/brand/create"
        >Создать</router-link
      >
      <router-link class="me-2" to="/user/workspace/brand/delete"
        >Удалить</router-link
      >
      <router-link class="me-2" to="/user/workspace/brand/update"
        >Изменить</router-link
      >
    </div>
    <router-view></router-view>
  </div>
</template>

<style scoped></style>
