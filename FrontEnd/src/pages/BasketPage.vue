<template>
  <div class="container">
    <div class="wrapper row">
      <div class="col-12 col-sm-10 col-md-8">
        <h1>Товары в корзине</h1>
        <div class="product-list">
          <div
            class="product-item mt-2 border-bottom pb-2"
            v-for="productItem in userStore?.getUser.basket?.products"
            :key="productItem.id"
          >
            <ProductCard
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
            <div class="quantity d-flex align-items-center">
              <span>Количество - </span>
              <input
                class="form-control ms-2"
                style="width: 75px"
                type="number"
                :id="productItem.id"
                min="1"
                v-model="productItem.quantity"
                @change="updateQuantity(productItem)"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="col-12 col-md-4">
        <h1>Оплата</h1>
        <form
          @submit.prevent="
            OrdersActions.createOrder(userStore.getUser.basket.products)
          "
        >
          <p>
            К оплате -
            <span style="font-weight: 700"
              >{{ userStore?.getBasketPrice }} руб</span
            >
          </p>
          <button class="btn btn-success">Оформить заказ</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import { userStoreModule } from "@/entities/user/api/index.js";
import { OrdersActions } from "@/entities/orders/api";
import { useRouter } from "vue-router";
import { onMounted } from "vue";

const router = useRouter();
const userStore = userStoreModule();
console.log("BASKET");

const updateQuantity = (productItem) => {
  console.log(productItem);
  if (productItem.quantity && productItem.quantity > 0)
    userStore.updateQuantity(productItem);
  else alert("Некорректное количество товара.");
};

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
