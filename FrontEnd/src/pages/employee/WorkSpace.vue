<template>
  <div class="root">
    <div class="">
      <div class="">
        <SliderVue style="height: 100%; margin-left: 10px">
          <RouterLink
            class="text-decoration-none border-bottom"
            to="/user/workspace/product/"
            style="margin-left: 5px; font-size: 26px"
            >Управление продуктами</RouterLink
          >
          <RouterLink
            class="text-decoration-none border-bottom"
            to="/user/workspace/promo/"
            style="margin-left: 5px; font-size: 26px"
            >Управление акциями</RouterLink
          >
          <RouterLink
            class="text-decoration-none border-bottom"
            to="/user/workspace/brand/create"
            style="margin-left: 5px; font-size: 26px"
            >Добавить бренд</RouterLink
          >
          <RouterLink
            class="text-decoration-none border-bottom"
            to="/user/workspace/categories/create"
            style="margin-left: 5px; font-size: 26px"
            >Добавить категорию</RouterLink
          >
          <RouterLink
            class="text-decoration-none border-bottom"
            to="/user/workspace/orders/list"
            style="margin-left: 5px; font-size: 26px"
            >Заказы</RouterLink
          >
        </SliderVue>
      </div>
      <div class="container mt-2">
        <RouterView></RouterView>
      </div>
    </div>
  </div>
</template>
<script setup>
import SliderVue from "@/shared/slider/SliderVue.vue";
</script>
