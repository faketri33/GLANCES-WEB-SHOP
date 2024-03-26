import { Promotion } from "@/entities/promotion/model/promotion";
import { $axios } from "@/shared/client/AxiosClient";

export const PromotionAction = {
  async loadPromo(): Promise<Promotion[]> {
    const response = await $axios.get("/promotion/");
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response);
  },
};
