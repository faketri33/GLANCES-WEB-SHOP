<template>
  <div class="container shadow rounded">
    <div class="root">
      <div class="type-paymant">
        <div class="form-check">
          <input
            class="form-check-input"
            type="radio"
            name="flexRadioDefault"
            id="flexRadioDefault1"
          />
          <label class="form-check-label" for="flexRadioDefault1">
            Default radio
          </label>
          <input
            class="form-check-input"
            type="radio"
            name="flexRadioDefault"
            id="flexRadioDefault1"
          />
          <label class="form-check-label" for="flexRadioDefault1">
            Default radio
          </label>
        </div>
      </div>
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
          v-on:toBasket="userStore.toBasket(productItem.product)"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import { userStoreModule } from "@/entities/user/api/index.js";

const userStore = userStoreModule();
</script>

<style></style>
