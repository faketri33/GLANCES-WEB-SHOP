<template>
  <main>
    <!--    <PromoSlider />
    <h2 style="">Категории</h2>
    <div class="categories row">
      <CategoriesCard
        class="col"
        v-for="categories in getCategories"
        :key="categories.id"
        v-bind:categories="categories"
      />
    </div>-->
    <h2>Акции</h2>
    <div v-if="!productStore.isRequestLoading" class="product row">
      <ProductCard
        class="col-xxl-2 col-xl-3 col-lg-3 col-md-4 col-sm-6 mt-2 d-flex justify-content-center"
        v-for="product in loadByFilter().content"
        :key="product.id"
        v-bind:product="product"
        v-bind:isSmallWidth="true"
        v-bind:likes="false"
      />
    </div>
  </main>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import HelloWorld from "@/widgets/HelloWorld.vue";
import ProductCard from "@/entities/product/ui/ProductCard.vue"; // @ is an alias to /src
import { storeModule } from "@/entities/product/api";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";

@Options({
  data() {
    return {
      productStore: storeModule(),
    };
  },
  created() {
    this.productStore.loadProductByFilter();
  },
  components: {
    ProductCard,
    HelloWorld,
  },
  methods: {
    loadProduct(pageNumber: number): PageableType<Product> {
      return this.productStore.getProducts(pageNumber);
    },
    loadByFilter(): PageableType<Product> {
      return this.productStore.getFiltered(0);
    },
  },
})
export default class HomeView extends Vue {}
</script>
