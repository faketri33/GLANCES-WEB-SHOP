import { defineStore } from "pinia";
import { ProductState } from "@/entities/product/api/model/ProductState";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Characteristics from "@/entities/characteristics/model/Characteristics";

export const storeModule = defineStore("product", {
  state: (): ProductState => ({
    product: {} as Product,
    pages: [] as PageableType<Product>[],
    filtered: [] as PageableType<Product>[],
    isRequestLoading: true,
  }),
  getters: {
    getProducts(state) {
      return (pageNumber: number) =>
        state.pages.find((value) => value.number === pageNumber);
    },
    getFiltered: (state) => {
      return (pageNumber: number) =>
        state.filtered.find((page) => page.pageable?.pageNumber === pageNumber);
    },
  },
  actions: {
    loadProduct(pageNumber: number, pageSize: number) {
      this.isRequestLoading = true;
      ProductActions.loadProduct(pageNumber, pageSize).then((data) =>
        this.updateProducts(data)
      );
      this.isRequestLoading = false;
    },

    async loadProductByCategories(
      pageNumber: number,
      pageSize: number,
      categoriesId: number
    ) {
      this.isRequestLoading = true;
      const res = await ProductActions.loadProductByCategories(
        pageNumber,
        pageSize,
        categoriesId
      );
      console.log(res);
      this.updateProducts(res);
      this.isRequestLoading = false;
    },

    async loadProductInPromotion(pageNumber: number, pageSize: number) {
      this.isRequestLoading = true;
      const res = await ProductActions.loadProductInPromotion(
        pageNumber,
        pageSize
      );
      this.updateProducts(res);
      this.isRequestLoading = false;
    },

    async loadProductByFilter(
      pageNumber: number,
      pageSize: number,
      characteristics: Characteristics[]
    ) {
      this.isRequestLoading = true;
      const res = await ProductActions.loadProductWithFilter(
        pageNumber,
        pageSize,
        characteristics
      );
      this.updateFiltered(res);
      this.isRequestLoading = false;
    },

    updateProducts(payload: PageableType<Product>) {
      this.pages.push(payload);
    },

    updateFiltered(payload: PageableType<Product>) {
      console.log(payload);
      console.log(this.pages);
      this.filtered.push(payload);
    },
  },
});
