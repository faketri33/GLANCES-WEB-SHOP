import Categories from "@/entities/categories/model/Categories";
import { $axios } from "@/shared/client/AxiosClient";

export const CategoriesAction = {
  loadCategories(): Promise<Categories[]> {
    return new Promise((resolve) =>
      $axios.get("/categories/", {}).then((response) => resolve(response.data))
    );
  },
};
