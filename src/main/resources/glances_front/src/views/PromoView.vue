<script>
import ProductCard from "@/components/ProductCard.vue";

export default {
  name: "PromoView",
  components: {
    ProductCard,
  },
  created() {
    this.$store.dispatch("featchPromoById", this.$route.params.id);
  },
  computed: {
    getPromotion() {
      return this.$store.getters["getPromo"];
    },
  },
};
</script>

<template>
  <div class="wrapper mt-4">
    <div class="container">
      <h1 class="title">{{ getPromotion.title }}</h1>
      <div class="img">
        <img
          class="img"
          :src="'data:image/jpeg;base64,' + getPromotion.banner"
          style="max-width: 100%"
        />
      </div>
      <div class="content border rounded-1 p-3">
        <div class="center--block mt-4">
          <h2>Аукционный товары</h2>
          <div class="promo--product row align-items-center">
            <ProductCard
              class="col-xxl-3 col-xl-3 col-lg-3 col-md-4 col-sm-6 mt-2 d-flex justify-content-center"
              v-for="product in getPromotion.promotionItems"
              :key="product.id"
              v-bind:product="product"
              v-bind:isSmallWidth="true"
            />
          </div>
        </div>
        <div class="description">
          <p style="font-size: 24px">{{ getPromotion.description }}</p>
        </div>
        <p>
          Срок проведения акции {{ getPromotion.dateOfStart }} по
          {{ getPromotion.dateOfEnd }}
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
