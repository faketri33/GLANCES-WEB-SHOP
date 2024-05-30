import { ProductItem } from "@/entities/product/model/ProductItem";
import { $axios } from "@/shared/client/AxiosClient";
import { Orders } from "../model";

export const OrdersActions = {
  async createOrder(product: Array<ProductItem>) {
    return (await $axios.post("/orders/create", product)).data;
  },
  async loadByUserId(
    userId: string,
    page: number,
    size: number
  ): Promise<Orders[]> {
    return await $axios.get("/orders/user/" + userId, {
      params: { page: page, size: size },
    });
  },
  async loadOrderById(id: string): Promise<Orders[]> {
    const response = await $axios.get("/orders/id/" + id);

    return response.data;
  },
  async loadOrders(page: number, size: number) {
    const response = await $axios.get("/orders/", {
      params: { page: page, size: size },
    });

    return response.data;
  },
  async loadOrdersBySuffixAndStatus(
    page: number,
    size: number,
    siffux: string,
    status: number
  ) {
    const response = await $axios.get("/orders/", {
      params: { page: page, size: size, siffux: siffux, status: status },
    });
    return response.data;
  },
};
