<template>
  <div class="root">
    <div class="container">
      <div v-if="!isDataLoad" class="content">
        <h1 class="title">{{ promotion?.title }}</h1>
        <img
          :src="'http://localhost:9000/api/image/' + promotion?.banner?.id"
          style="width: 100%"
        />
        <div class="content shadow p-5">
          <div class="center--block">
            <div class="promo--product">
              <h3>Аукционный товары</h3>
              <div class="product">
                <swiper
                  :breakpoints="breakpoints"
                  :navigation="true"
                  :modules="modules"
                >
                  <swiper-slide
                    :slidesPerView="'auto'"
                    :centeredSlides="true"
                    v-for="product in promotion?.promotionProductItems"
                    :key="product.id"
                  >
                    <ProductCard
                      v-bind:product="product"
                      v-bind:typeCard="false"
                      v-bind:likes="userStore.isLikedProduct(product.id)"
                      v-bind:isBasketItem="
                        userStore.isInBasketProduct(product.id)
                      "
                      v-on:removeFromBasket="userStore.addBasket"
                      v-on:addToBasket="userStore.removeBasket"
                    />
                  </swiper-slide>
                </swiper>
              </div>
            </div>
          </div>
          <div class="description">
            <h1>Описание</h1>
            <p>{{ promotion?.description }}</p>
          </div>
          <p>
            Срок проведения акции {{ promotion?.dateOfStart }} по
            {{ promotion?.dateOfEnd }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { PromotionAction } from "@/entities/promotion/api/PromotionAction";
import { Promotion } from "@/entities/promotion/model/promotion";
import { onMounted, ref } from "vue";
import ProductCard from "@/entities/product/ui/ProductCard.vue";
import { useRoute } from "vue-router";

import { Swiper, SwiperSlide } from "swiper/vue";
import { Navigation } from "swiper/modules";
// Import Swiper styles
import "swiper/css";
import "swiper/css/navigation";
import { userStoreModule } from "@/entities/user/api/index.js";

const modules = [Navigation];

const userStore = userStoreModule();

const breakpoints = {
  320: {
    slidesPerView: 1,
    spaceBetween: 10,
    centeredSlides: true,
  },
  480: {
    slidesPerView: 2,
    spaceBetween: 10,
  },
  1024: {
    slidesPerView: 3,
    spaceBetween: 10,
  },
  1200: {
    slidesPerView: 5,
    spaceBetween: 10,
  },
};

const route = useRoute();

const isDataLoad = ref(true);
const promotion = ref<Promotion>();
const promoId = route.params.id.toString();

onMounted(async () => {
  promotion.value = await PromotionAction.loadPromoById(promoId);
  isDataLoad.value = false;
  return { promotion, isDataLoad };
});
</script>
