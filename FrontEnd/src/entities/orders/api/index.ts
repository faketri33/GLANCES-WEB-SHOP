import { $axios } from "@/shared/client/AxiosClient";

export const OrdersActions = {
  async loadOrderById(id: string) {
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
