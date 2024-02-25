import Categories from "@/entities/categories/model/Categories";
import { axiosInstance } from "@/shared/client/AxiosClient";

export const CategoriesAction = {
  loadCategories(): Promise<Categories[]> {
    return new Promise((resolve) =>
      axiosInstance
        .get("/categories/", {})
        .then((response) => resolve(response.data))
    );
  },
};
