import Categories from "@/entities/categories/model/Categories";

export type CategoriesState = {
  categories: Categories[];
  isRequestLoading: boolean;
};
