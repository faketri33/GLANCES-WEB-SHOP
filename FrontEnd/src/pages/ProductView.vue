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
          <button
            class="btn shadow me-2"
            @click="userStore.toFavorite(product)"
          >
            <img
              :src="
                userStore.isLikedProduct(product.id)
                  ? '/red.svg'
                  : '/Vector.svg'
              "
              alt=""
            />
          </button>
          <button
            @click="
              userStore.isInBasketProduct(product.id)
                ? BasketAction.removeFromBasket(product)
                : BasketAction.addProductBasket(product)
            "
            class="btn btn-primary w-100"
          >
            {{
              userStore.isInBasketProduct(product.id)
                ? "Удалить из корзины"
                : "В корзину"
            }}
          </button>
        </div>
      </div>
      <div class="additional-information mt-5">
        <div class="additional-information-body">
          <div class="description">
            <h1>Описание</h1>
            <p>
              {{ product.description }}
            </p>
          </div>
          <div class="rating">
            <h1>Отзывы</h1>
            <div class="rating-add">
              <button
                type="button"
                class="btn btn-outline-primary"
                @click="showModal = true"
              >
                Добавить отзыв
              </button>
              <div>
                <!-- Модальное окно -->
                <ModelView v-bind:show="showModal" @close="showModal = false">
                  <form
                    class="d-flex flex-column gap-2"
                    @submit.prevent="addRating"
                  >
                    <label for="grade">Рейтинг</label>
                    <input type="text" id="grade" v-model="review.grade" />

                    <label for="description">Описание</label>
                    <input
                      type="text"
                      id="description"
                      v-model="review.description"
                    />

                    <button class="btn btn-success" type="submit">
                      Отправить
                    </button>
                  </form>
                </ModelView>
              </div>
            </div>
            <div v-if="productRating[ratingPage]" class="wrapper">
              <RatingComp
                v-for="(item, index) in productRating[ratingPage].content"
                :key="index"
                v-bind:rating="item"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { BasketAction } from "@/entities/basket/api";
import { onMounted, ref, watch } from "vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { useRoute } from "vue-router";
import { Swiper, SwiperSlide } from "swiper/vue";
import { FreeMode, Pagination, Navigation, Thumbs } from "swiper/modules";
import CharacteristicsToProductPage from "@/entities/characteristics/ui/CharacteristicsToProductPage.vue";
import RatingComp from "@/entities/rating/ui/RatingComp.vue";
import { RatingAction } from "@/entities/rating/api/actions";
import { userStoreModule } from "@/entities/user/api/index.js";
import ModelView from "@/widgets/ModelView.vue";

import "swiper/css/navigation";
import "swiper/css/thumbs";
import "swiper/css";

const route = useRoute();
const userStore = userStoreModule();

const dataLoading = ref(true);
const product = ref(null);
const productRating = ref([]);
const ratingPage = 0;

const thumbsSwiper = ref();

const setThumbsSwiper = (swiper) => {
  thumbsSwiper.value = swiper;
};

const modules = [FreeMode, Navigation, Thumbs, Pagination];

const showModal = ref(false);

const review = ref({
  grade: 0,
  description: "",
});

watch(
  () => route.params.id,
  async () => {
    console.log(route.params.id);
    await loadProduct();
  }
);

const loadProduct = async () => {
  const productId = route.params.id.toString();
  product.value = await ProductActions.loadProductById(productId);
  productRating.value = [];
  dataLoading.value = false;
};

const loadRating = async () => {
  const productId = route.params.id.toString();
  const response = await RatingAction.loadByProductId(
    ratingPage,
    20,
    productId
  );
  productRating.value[ratingPage] = response;
};

const addRating = async () => {
  const productId = route.params.id.toString();
  const response = await userStore.addRating(productId, review.value);
  if (productRating.value) productRating.value[0] = { content: [] };
  productRating.value[ratingPage].content.push(response);
};

onMounted(async () => {
  await loadProduct();
  await loadRating();
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
