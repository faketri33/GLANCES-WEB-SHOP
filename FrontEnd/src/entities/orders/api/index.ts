import { ProductItem } from "@/entities/product/model/ProductItem";
import { $axios } from "@/shared/client/AxiosClient";
import { Orders } from "../model";

export const OrdersActions = {
  async paidOrder(orderId: number) {
    return await $axios
      .get("/payments/" + orderId + "/status/paid")
      .then((data) => {
        alert("Заказ оплачен!");
        return data.data;
      })
      .catch((err) => {
        console.log(err);
        alert("Попробуйте повторить операцию позже.");
      });
  },
  createOrder: async function (product: Array<ProductItem>) {
    if (product) {
      alert("Заказ не может быть пустой");
      return;
    }
    return await $axios
      .post("/orders/create", product)
      .then(() => alert("Заказ создан!"))
      .catch((err) => alert(err.response.data.message));
  },
  async updateStatus(id: string, status: string) {
    return await $axios
      .post("/orders/" + id + "/change-status", {
        status: status,
      })
      .then((data) => data.data)
      .catch((err) => {
        console.log(err);
        alert("Не удалось обновить статус заказа.");
      });
  },
  async getAllStatus(): Promise<Map<string, string>> {
    return await $axios
      .get("/orders/get-all-status")
      .then((data) => data.data)
      .catch((err) => {
        console.log(err);
        alert("Ошибка получения статусов.");
      });
  },
  async loadByUserId(
    userId: string,
    page: number,
    size: number
  ): Promise<Orders[]> {
    return (
      await $axios.get("/orders/user/" + userId, {
        params: { page: page, size: size },
      })
    ).data;
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
