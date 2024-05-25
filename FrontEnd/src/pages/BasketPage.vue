<template>
  <div class="container">
    <div class="wrapper row">
      <div class="col-12 col-sm-10 col-md-8">
        <h1>Товары в корзине</h1>
        <div class="product-list">
          <ProductCard
            v-for="productItem in userStore?.getUser.basket?.products"
            v-bind:product="productItem.product"
            :key="productItem.product.id"
            v-bind:likes="userStore.isLikedProduct(productItem.product.id)"
            v-bind:isBasketItem="
              userStore.isInBasketProduct(productItem.product.id)
            "
            v-on:addToFavorite="userStore.toFavorite(productItem.product)"
            v-on:removeFromBasket="userStore.removeBasket"
            v-on:addToBasket="userStore.addBasket"
          />
        </div>
      </div>
      <div class="col-12 col-md-4">
        <h1>Оплата</h1>
        <form>
          <p>
            К оплате -
            <span style="font-weight: 700"
              >{{ userStore?.getBasketPrice }} руб</span
            >
          </p>
          <button
            class="btn btn-success"
            @click="
              OrdersActions.createOrder(userStore.getUser.basket.products)
            "
          >
            Оформить заказ
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import { userStoreModule } from "@/entities/user/api/index.js";
import { OrdersActions } from "@/entities/orders/api";
import { BasketAction } from "@/entities/basket/api";
import { useRouter } from "vue-router";
import { onMounted } from "vue";

const router = useRouter();
const userStore = userStoreModule();
console.log("BASKET");
onMounted(() => {
  if (!userStore.isLogin) router.push("/auth");
});
</script>

<style>
.example-slide {
  align-items: center;
  background-color: #666;
  color: #999;
  display: flex;
  font-size: 1.5rem;
  justify-content: center;
  min-height: 10rem;
}
</style>
