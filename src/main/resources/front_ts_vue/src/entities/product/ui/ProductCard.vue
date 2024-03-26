<template>
  <div
    class="wrapper border p-3 rounded-2 mb-3 position-relative"
    :class="typeCard ? 'row' : ''"
    :style="typeCard ? '' : 'max-width: 20rem'"
  >
    <div
      class="img"
      :class="typeCard ? 'col-12 col-sm-6 col-md-5 col-lg-5' : ''"
    >
      <swiper
        :slides-per-view="1"
        :pagination="true"
        :modules="modules"
        style="max-width: 220px"
      >
        <swiper-slide v-for="image in product.image" :key="image.id">
          <img
            :src="'http://localhost:8080/api/image/' + image.id"
            alt=""
            style="max-width: 220px"
          />
        </swiper-slide>
      </swiper>
    </div>
    <div
      class="card-body mt-3"
      :class="typeCard ? 'd-flex flex-column col-3 gap-3' : ''"
    >
      <router-link
        class="text-decoration-none text-black"
        :to="'/product/' + product.id"
      >
        <h5 class="card-title mb-2 cut-text">
          {{ getFullName }}
        </h5>
      </router-link>
      <p class="card-price text-center">
        {{ product.price }} ₽
        <span v-if="product.isPromoActive" class="original-price">87 999₽</span>
      </p>
      <div v-if="product.isPromoActive" class="discount-tag">-4%</div>
      <div
        class="option d-flex justify-content-between align-items-center mb-3"
      >
        <p class="m-0 text-primary">В наличии</p>
        <button class="btn shadow" @click="addToFavorite">
          <img :src="mutateLikes ? '/red.svg' : '/Vector.svg'" alt="" />
        </button>
      </div>
      <div class="text-center w-100">
        <button @click="addToBasket" class="btn btn-primary w-100">
          В корзину
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { Product } from "@/entities/product/model/Product";
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from "swiper/vue";
// import required modules
import { Pagination } from "swiper/modules";
import "swiper/css/pagination";
// Import Swiper styles
import "swiper/css";

export default {
  components: {
    Swiper,
    SwiperSlide,
  },
  props: {
    product: Product,
    likes: Boolean,
    typeCard: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      mutateLikes: this.likes,
    };
  },
  setup() {
    return {
      modules: [Pagination],
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
    addToBasket() {
      this.$emit("toBasket", this.product);
    },
  },
};
</script>

<style>
.card-price {
  font-weight: bold;
  font-size: 1.25rem;
}

.original-price {
  text-decoration: line-through;
  font-weight: normal;
  font-size: 1rem;
  margin-left: 1rem;
  color: #999;
}

.cut-text {
  text-overflow: ellipsis;
  overflow: hidden;
  max-height: 3.6em;
  white-space: normal;
}

.discount-tag {
  background-color: yellow;
  color: #333;
  font-weight: bold;
  padding: 0.25rem 0.5rem;
  position: absolute;
  top: 0;
  right: 0;
}

.options {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
}
</style>
