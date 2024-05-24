import { Rating } from "@/entities/rating/model";
import { $axios } from "@/shared/client/AxiosClient";
import { PageableType } from "@/shared/pageable/pageableType";

export const RatingAction = {
  async loadByProductId(
    pageNumber: number,
    pageSize: number,
    id: string
  ): Promise<PageableType<Rating>> {
    const response = await $axios.get("/rating/" + id, {
      params: { page: pageNumber, size: pageSize },
    });
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response);
  },
};
