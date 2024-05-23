<template>
  <div class="root">
    <div class="wrap" v-if="order">
      <h1>Номер заказа - {{ order.id }}</h1>
      <p>Пользователь - {{ order.users.login }}</p>
      <p>Стоимость заказа {{ order.price }}руб.</p>
      <p>Оплата - {{ order.payment.paymentStatus }}</p>
      <p>Статус заказа - {{ order.statusOrder }}</p>
      <button class="btn btn-success">Выдать заказ</button>
      <div class="product-list">
        <div class="wrap" v-for="product in order.products" :key="product.id">
          <ProductCard
            v-bind:product="product.product"
            v-bind:typeCard="true"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { onMounted, ref } from "vue";
import { OrdersActions } from "../api";
import { useRoute } from "vue-router";
import ProductCard from "@/entities/product/ui/ProductCard.vue";

const route = useRoute();
const order = ref();

onMounted(async () => {
  order.value = await OrdersActions.loadOrderById(route.params.id.toString());
  return order;
});
</script>
