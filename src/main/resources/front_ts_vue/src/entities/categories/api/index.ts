import { defineStore } from "pinia";
import { CategoriesState } from "@/entities/categories/api/model/state";
import { PageableType } from "@/shared/pageable/pageableType";
import Categories from "@/entities/categories/model/Categories";
import { CategoriesAction } from "@/entities/categories/api/model/actions";

export const storeModuleCategories = defineStore("categories", {
  state: () =>
    ({
      categories: [] as Categories[],
      isRequestLoading: true,
    } as CategoriesState),
  getters: {
    getCategories: (state) => {
      return state.categories;
    },
  },
  actions: {
    async loadCategories() {
      this.isRequestLoading = true;
      const res = await CategoriesAction.loadCategories();
      this.updateCategories(res);
      this.isRequestLoading = false;
    },

    updateCategories(payload: Categories[]) {
      this.categories = payload;
    },
  },
});
