import { $axios } from "@/shared/client/AxiosClient";
import { Basket } from "../model/basket";

export const BasketAction = {
  async addProductBasket(
    basketId: string,
    productId: string,
    quantity: number
  ): Promise<Basket> {
    return (
      await $axios.put("/basket/add/" + basketId, null, {
        params: {
          productId: productId,
          quantity: quantity,
        },
      })
    ).data;
  },
  async removeFromBasket(
    basketId: string,
    productId: string,
    quantity: number
  ) {
    return await $axios.delete("/basket/remove/" + basketId, {
      params: {
        productId: productId,
        quantity: quantity,
      },
    });
  },
};
