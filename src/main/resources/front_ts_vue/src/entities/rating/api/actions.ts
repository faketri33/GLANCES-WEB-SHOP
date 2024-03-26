import { Rating } from "@/entities/rating/model";
import { $axios } from "@/shared/client/AxiosClient";
import { PageableType } from "@/shared/pageable/pageableType";

export const RatingAction = {
  async loadByProductId(id: string): Promise<PageableType<Rating>> {
    const response = await $axios.get("/rating/" + id);
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response);
  },
};
