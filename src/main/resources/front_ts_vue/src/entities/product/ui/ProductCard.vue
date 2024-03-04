<template>
  <div class="wrapper">
    <div v-show="!isSmallWidth" class="row p-2 m-3 border rounded-1">
      <div class="img col-md-4 col-lg-4 text-center">
        <img
          :src="'http://localhost:8080/api/image/' + product.image[0].id"
          class="img"
          :alt="getFullName"
          style="width: 100%"
        />
      </div>
      <div class="card-body p-0 ms-3 col-md-2">
        <h5 class="card-title">
          <router-link
            :to="'/product/' + product.id"
            class="text-decoration-none"
            style="color: black"
          >
            {{ getFullName }}
          </router-link>
        </h5>
        <div class="rating mt-2">
          <p>Рейтинг товара - <strong> 0 </strong></p>
        </div>
        <div class="stock d-flex align-items-center col">
          <p class="in-stock text-primary m-0">В наличии</p>
          <button
            @click="mutateLikes = !mutateLikes"
            class="btn btn-outline-light shadow mb-2 ms-5"
            title="Добавить в избраное"
          >
            <img :src="mutateLikes ? '/red.svg' : '/Vector.svg'" alt="" />
          </button>
        </div>
      </div>
      <div
        class="card-price-buy col-md-3 col-lg-2 text-center d-flex align-items-center"
      >
        <div class="wrapper w-100">
          <h5 class="price">
            <strong>{{ product.price }}руб.</strong>
          </h5>
          <button class="btn btn-primary w-100">В корзину</button>
        </div>
      </div>
    </div>
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
};
</script>
