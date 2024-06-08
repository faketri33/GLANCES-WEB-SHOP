<template>
  <div class="root">
    <h1>Страница заказов</h1>
    <div class="search">
      <p>Введите последние 6 символов номера заказа.</p>
      <search-component
        v-model:searchQuery="searchQuery"
        :results="orders?.content"
        class="w-100 mb-4"
      >
        <template v-slot:default="{ results }">
          <ul>
            <li
              class="list-group-item"
              v-for="order in results"
              :key="order.id"
            >
              <router-link :to="'/user/workspace/orders/about/' + order.id">{{
                order.id
              }}</router-link>
            </li>
          </ul>
        </template>
      </search-component>
    </div>
    <div class="container">
      <RouterView></RouterView>
    </div>
  </div>
</template>
<script setup lang="ts">
import SearchComponent from "@/widgets/SearchComponent.vue";
import { ref, watch } from "vue";
import { OrdersActions } from "@/entities/orders/api";

const orders = ref();
const searchQuery = ref("");

watch(searchQuery, async (newQuery) => {
  if (newQuery && newQuery.length == 6) {
    orders.value = await OrdersActions.loadOrdersBySuffix(0, 10, newQuery);
    console.log(orders.value);
  } else {
    orders.value = [];
  }
});
</script>
