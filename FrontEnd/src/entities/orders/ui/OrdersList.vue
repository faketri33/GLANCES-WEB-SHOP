<template>
  <div class="root">
    <div
      class="wrap"
      v-for="order in orders[currentPage]?.content"
      :key="order.id"
    >
      <h4>
        <RouterLink :to="'/user/workspace/orders/about/' + order.id"
          >Заказ номер - {{ order.id }}</RouterLink
        >
      </h4>
      <p>Статус заказ - {{ order.statusOrder }}</p>
    </div>
    <div class="col-12">
      <nav aria-label="Page navigation">
        <ul class="pagination">
          <li
            v-for="number in orders[0]?.totalPages"
            :key="number"
            class="page-item"
          >
            <a class="page-link" v-on:click="changePages(number - 1)" href="#">
              {{ number }}
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>
<script setup>
import { onMounted, ref } from "vue";
import { OrdersActions } from "../api";

const currentPage = 0;
const sizePage = 20;

const orders = ref([]);

onMounted(async () => {
  orders.value[currentPage] = await OrdersActions.loadOrders(
    currentPage,
    sizePage
  );

  console.log(orders.value);
  return orders;
});
</script>
