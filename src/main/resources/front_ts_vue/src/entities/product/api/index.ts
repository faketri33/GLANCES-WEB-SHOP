import { defineStore } from "pinia";
import { ProductState } from "@/entities/product/api/model/ProductState";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";

export const storeModule = defineStore("product", {
  state: (): ProductState => ({
    product: {} as Product,
    pages: [],
    isRequestLoading: false,
  }),
  getters: {
    getProducts: (state) => {
      return (pageNumber: number) =>
        state.pages.find((page) => page.pageable.pageNumber === pageNumber);
    },
  },
  actions: {
    async loadProduct() {
      this.isRequestLoading = true;
      const res = await ProductActions.loadProduct(0, 20);
      this.updateProducts(res);
      this.isRequestLoading = false;
    },

    updateProducts(payload: PageableType<Product>) {
      console.log(payload);
      console.log(this.pages);
      this.pages.push(payload);
    },
  },
});
