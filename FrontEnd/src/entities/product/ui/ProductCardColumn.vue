<template>
  <div
    v-show="isSmallWidth"
    class="card p-4"
    style="max-width: 16rem; gap: 24px"
  >
    <router-link
      :to="'/product/' + product.id"
      class="text-decoration-none text-center"
      style="color: black; gap: 24px"
    >
      <img
        :src="'http://localhost:9000/api/image/' + product.image[0].id"
        class="card-img-top"
        :alt="getFullName"
        style="max-height: 206px; max-width: 174px"
      />
      <div class="card-body p-0">
        <h5 class="card-title">
          {{ getFullName }}
        </h5>
        <div class="d-flex justify-content-between">
          <p class="card-price" v-if="product.isPromoActive">
            <strong>{{ product.promoPrice }}</strong
            >руб.
          </p>
          <p
            class="card-price"
            :style="
              product.isPromoActive
                ? 'text-decoration:line-through; color: grey;'
                : 'text-decoration: none;'
            "
          >
            <strong>{{ product.price }}</strong
            >руб.
          </p>
        </div>
      </div>
    </router-link>
    <div class="stock d-flex justify-content-between align-items-center">
      <p class="in-stock text-primary m-0">В наличии</p>
      <button
        @click="addToFavorite"
        class="btn btn-outline-light shadow mb-2"
        title="Добавить в избраное"
      >
        <img :src="mutateLikes ? '/red.svg' : '/Vector.svg'" alt="" />
      </button>
    </div>
    <button @click="toBasket" class="btn btn-primary w-100">В корзину</button>
  </div>
</template>

<script>
import { Product } from "@/entities/product/model/Product";

export default {
  props: {
    product: Product,
    likes: Boolean,
    isSmallWidth: Boolean,
  },
  data() {
    return {
      mutateLikes: this.likes,
    };
  },
  computed: {
    getFullName() {
      return this.product.brand.name + " " + this.product.nameModel;
    },
  },
  methods: {
    addToFavorite() {
      this.mutateLikes = !this.mutateLikes;
      this.$emit("addToFavorite", this.product, this.mutateLikes);
    },
    toBasket() {
      this.$emit("toBasket", this.product);
    },
  },
};
</script>

<style scoped></style>
