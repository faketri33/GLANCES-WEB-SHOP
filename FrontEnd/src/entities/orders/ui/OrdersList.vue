<template>
  <div class="container">
    <div class="root" v-if="orders">
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
      <div class="col-12" v-if="orders[0]">
        <nav aria-label="Page navigation">
          <ul class="pagination">
            <li
              v-for="number in orders[0].totalPages"
              :key="number"
              class="page-item"
            >
              <button class="page-link" @click="changePage(number - 1)">
                {{ number }}
              </button>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</template>
<script setup>
import { onMounted, ref } from "vue";
import { OrdersActions } from "../api";

const currentPage = ref(0);
const sizePage = 20;

const orders = ref([]);

async function changePage(page) {
  currentPage.value = page;
  if (!orders.value[page]) orders.value[page] = await loadOrders();
}

async function loadOrders() {
  return await OrdersActions.loadOrders(currentPage.value, sizePage);
}

onMounted(async () => {
  orders.value[currentPage.value] = await OrdersActions.loadOrders(
    currentPage.value,
    sizePage
  );
  console.log(orders.value[currentPage.value]);
  return orders;
});
</script>
