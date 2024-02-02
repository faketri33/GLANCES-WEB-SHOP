<template>
  <div class="home container mt-5">
    <PromoSlider />
    <h2 style="">Категории</h2>
    <CategoriesCard />
    <h2>Акции</h2>
    <div class="product row">
      <ProductCard
        class="col-xxl-3 col-xl-3 col-lg-3 col-md-4 col-sm-6 mt-2 d-flex justify-content-center"
        v-for="product in getProduct"
        :key="product.id"
        v-bind:product="product"
        v-bind:isSmallWidth="true"
      />
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";
import PromoSlider from "@/components/PromoSlider.vue";
import CategoriesCard from "@/components/CategoriesCard.vue";
export default {
  name: "HomeView",
  components: { ProductCard, PromoSlider, CategoriesCard },
  created() {
    this.$store.dispatch("featchProduct");
  },
  computed: {
    getProduct() {
      return this.$store.getters["getProduct"];
    },
  },
};
</script>
