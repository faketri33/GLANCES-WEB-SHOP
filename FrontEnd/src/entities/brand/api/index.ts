import { Categories } from "@/entities/categories/model/Categories";
import { $axios } from "@/shared/client/AxiosClient";

export const BrandAction = {
  async loadBrand(): Promise<Categories[]> {
    return (await $axios.get("/brand/")).data;
  },
};
