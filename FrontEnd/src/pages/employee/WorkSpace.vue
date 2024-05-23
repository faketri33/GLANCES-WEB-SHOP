<template>
  <div class="root">
    <div class="row">
      <div class="col-3 p-5">
        <SliderVue style="height: 100%">
          <RouterLink
            class="text-decoration-none border-bottom"
            to="/user/workspace/product/create"
            style="margin-left: 5px; font-size: 26px"
            >Добавить продукт</RouterLink
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
      <div class="col mt-5">
        <RouterView></RouterView>
      </div>
    </div>
  </div>
</template>
<script setup>
import SliderVue from "@/shared/slider/SliderVue.vue";
</script>
