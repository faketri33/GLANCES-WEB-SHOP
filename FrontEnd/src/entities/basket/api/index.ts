import { $axios } from "@/shared/client/AxiosClient";
import { Basket } from "../model/basket";
import { ProductItem } from "@/entities/product/model/ProductItem";

export const BasketAction = {
  async addProductBasket(basketId: string, productId: string): Promise<Basket> {
    return (
      await $axios.put("/basket/add/" + basketId, null, {
        params: {
          productId: productId,
        },
      })
    ).data;
  },
  async updateQunatity(
    productItem: ProductItem,
    basketId: string
  ): Promise<Basket> {
    return await (
      await $axios.post(
        "/basket/update-quantity/" + basketId + "/" + productItem.id,
        {
          productItemId: productItem.id,
          quantity: productItem.quantity,
        }
      )
    ).data;
  },
  async removeFromBasket(basketId: string, productId: string) {
    return (
      await $axios.delete("/basket/remove/" + basketId, {
        params: {
          productId: productId,
        },
      })
    ).data;
  },
};
