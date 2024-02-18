import { defineStore } from "pinia";
import { ProductState } from "@/entities/product/api/model/ProductState";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Characteristics from "@/entities/product/model/Characteristics";

export const storeModule = defineStore("product", {
  state: (): ProductState => ({
    product: {} as Product,
    pages: [] as PageableType<Product>[],
    filtered: [] as PageableType<Product>[],
    isRequestLoading: false,
  }),
  getters: {
    getProducts: (state) => {
      return (pageNumber: number) =>
        state.pages.find((page) => page.pageable.pageNumber === pageNumber);
    },
    getFiltered: (state) => {
      return (pageNumber: number) =>
        state.filtered.find((page) => page.pageable.pageNumber === pageNumber);
    },
  },
  actions: {
    async loadProduct() {
      this.isRequestLoading = true;
      const res = await ProductActions.loadProduct(0, 20);
      this.updateProducts(res);
      this.isRequestLoading = false;
    },

    async loadProductByFilter() {
      this.isRequestLoading = true;
      console.log(Array.of(new Characteristics("Диагональ экрана", '6.7"')));
      const res = await ProductActions.loadProductWithFilter(
        0,
        20,
        Array.of(new Characteristics("Диагональ экрана", '6.7"'))
      );
      this.updateFiltered(res);
      this.isRequestLoading = false;
    },

    updateProducts(payload: PageableType<Product>) {
      console.log(payload);
      console.log(this.pages);
      this.pages.push(payload);
    },
    updateFiltered(payload: PageableType<Product>) {
      console.log(payload);
      console.log(this.pages);
      this.filtered.push(payload);
    },
  },
});
