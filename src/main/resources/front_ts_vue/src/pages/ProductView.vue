<template>
  <div class="container mt-2">
    <h1 v-if="product">
      {{ product.brand.name + " " + product.nameModel }}
    </h1>
    <div v-if="product" class="wrapper row rounded-2 shadow p-3">
      <div class="col-12 col-sm-8 col-md-5 shadow rounded-2 p-2 img">
        <swiper
          :slidesPerView="1"
          :centered-slides="true"
          :spaceBetween="10"
          :thumbs="{ swiper: thumbsSwiper }"
          :modules="modules"
        >
          <swiper-slide
            class="text-center"
            v-for="image in product.image"
            :key="image.id"
          >
            <img
              :src="'http://localhost:9000/api/image/' + image.id"
              alt=""
              style="max-height: 220px"
            />
          </swiper-slide>
        </swiper>
        <swiper
          @swiper="setThumbsSwiper"
          :spaceBetween="10"
          :slidesPerView="3"
          :freeMode="true"
          :navigation="true"
          :watchSlidesProgress="true"
          :modules="modules"
          class="mySwiper"
        >
          <swiper-slide v-for="image in product.image" :key="image.id">
            <img
              :src="'http://localhost:9000/api/image/' + image.id"
              alt=""
              style="max-height: 120px"
            />
          </swiper-slide>
        </swiper>
      </div>
      <div class="characteristics col-12 col-sm-4">
        <div class="characteristics">
          <h4>Характеристики</h4>
          <CharacteristicsToProductPage
            :characteristics="product.characteristics"
          ></CharacteristicsToProductPage>
        </div>
      </div>
      <div
        class="info col-12 col-sm-4 col-md-3 info d-flex flex-column justify-content-center align-items-center"
      >
        <div class="price d-flex justify-content-around">
          <h2 v-if="product.isPromoActive">{{ product.promoPrice }} руб.</h2>
          <h2
            :class="product.isPromoActive ? 'text-decoration-line-through' : ''"
          >
            {{ product.price }} руб.
          </h2>
        </div>
        <div class="actions w-100 d-flex">
          <button class="btn shadow me-2">
            <img
              :src="
                userStore.isLikedProduct(productId) ? '/red.svg' : '/Vector.svg'
              "
              alt=""
            />
          </button>
          <button @click="addToBasket" class="btn btn-primary w-100">
            В корзину
          </button>
        </div>
      </div>
      <div class="additional-information mt-5">
        <div class="additional-information-body">
          <div class="rating">
            <h1>Отзывы</h1>
            <div class="rating-add">
              <router-link to="">
                <button type="button" class="btn btn-outline-primary">
                  Добавить отзыв
                </button>
              </router-link>
            </div>
            <div v-if="productRating?.content" class="wrapper">
              <RatingComp
                v-for="item in productRating.content"
                :key="item.id"
                v-bind:rating="item"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { Product } from "@/entities/product/model/Product";
import { Rating } from "@/entities/rating/model";
import { onMounted, ref } from "vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { useRoute } from "vue-router";
import { Swiper, SwiperSlide } from "swiper/vue";
import { FreeMode, Pagination, Navigation, Thumbs } from "swiper/modules";
import { Swiper as SwiperClass } from "swiper/types";
import CharacteristicsToProductPage from "@/entities/characteristics/ui/CharacteristicsToProductPage.vue";
import RatingComp from "@/entities/rating/ui/RatingComp.vue";
import { RatingAction } from "@/entities/rating/api/actions";
import { userStoreModule } from "@/entities/user/api/index.js";

import "swiper/css/navigation";
import "swiper/css/thumbs";
import "swiper/css";
import { PageableType } from "@/shared/pageable/pageableType";

const route = useRoute();
const userStore = userStoreModule();

const productId = route.params.id.toString();
const dataLoading = ref(true);
const product = ref<Product>();
const productRating = ref<PageableType<Rating>>();

const thumbsSwiper = ref(null);

const setThumbsSwiper = (swiper: SwiperClass) => {
  thumbsSwiper.value = swiper;
};

const modules = [FreeMode, Navigation, Thumbs, Pagination];

onMounted(async () => {
  product.value = await ProductActions.loadProductById(productId);
  productRating.value = await RatingAction.loadByProductId(productId);
  dataLoading.value = false;
  return { product, productRating, dataLoading };
});
</script>

<style scoped>
@media screen and (max-width: 570px) {
  .info {
    position: fixed;
    bottom: 60px;
    z-index: 999;
    background-color: white;
    width: 100%;
  }
  .container {
    margin: 0 auto;
    margin: 0 0 150px 0;
  }
}
</style>
