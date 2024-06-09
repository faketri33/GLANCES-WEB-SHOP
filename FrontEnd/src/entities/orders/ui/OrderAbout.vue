<template>
  <div class="root container">
    <div class="wrap" v-if="order">
      <h1>Номер заказа - {{ order.id }}</h1>
      <p>Пользователь - {{ order?.users?.login }}</p>
      <p>Стоимость заказа {{ order.price }}руб.</p>
      <p>Оплата - {{ order.payment?.paymentStatus }}</p>
      <p>Статус заказа - {{ order.statusOrder }}</p>
      <div class="" v-if="userStore?.user?.role?.some((r) => r === 'EMPLOYEE')">
        <button @click="paidOrders" class="btn btn-success m-2">
          Заказ оплачен
        </button>
        <p>Выберите новый статус заказа:</p>
        <select
          class="form-select"
          style="width: 200px"
          v-if="orderStatus"
          v-model="selectedStatus"
        >
          <option selected disabled>Выберите статус заказа</option>
          <option v-for="status in orderStatus" :key="status" :value="status">
            {{ status }}
          </option>
        </select>
        <button class="btn btn-success mt-2" @click="changeOrderStatus">
          Изменить статус заказа
        </button>
      </div>
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
import { userStoreModule } from "@/entities/user/api/index.js";

const route = useRoute();
const order = ref();
const userStore = userStoreModule();
const orderStatus = ref(null);
const selectedStatus = ref("Выберите статус заказа");

function getKeyByValue(object, value) {
  return Object.entries(object).find(([key, val]) => val === value)?.[0];
}

const paidOrders = async () => {
  const response = await OrdersActions.paidOrder(order.value.payment.id);
  if (response) order.value.payment = response;
};

const changeOrderStatus = async () => {
  const key = getKeyByValue(orderStatus.value, selectedStatus.value);
  if (key) {
    const respsonse = await OrdersActions.updateStatus(order.value.id, key);
    if (respsonse) order.value = respsonse;
  }
};

onMounted(async () => {
  order.value = await OrdersActions.loadOrderById(route.params.id.toString());
  orderStatus.value = await OrdersActions.getAllStatus();
  return { order, orderStatus };
});
</script>

<style></style>
