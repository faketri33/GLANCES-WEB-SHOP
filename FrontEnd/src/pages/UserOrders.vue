<template>
  <div class="root container mt-4" v-if="orders[currentPage]">
    <div
      class="wrap"
      v-for="order in orders[currentPage]?.content"
      :key="order.id"
    >
      <h4>
        <RouterLink :to="'/user/' + order.id + '/orders/about/'"
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
import { OrdersActions } from "@/entities/orders/api/";
import { useRoute } from "vue-router";

const currentPage = 0;
const sizePage = 20;
const route = useRoute();

const orders = ref([]);

onMounted(async () => {
  const userId = route.params.id;
  orders.value[currentPage] = await OrdersActions.loadByUserId(userId);
  return orders;
});
</script>
