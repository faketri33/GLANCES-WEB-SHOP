import { User } from "@/entities/user/model/User";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { AxiosResponse } from "axios";
import { RequestExceptions } from "@/shared/exceptions/RequestExceptions";

export const UserActions = {
  async signIn(params: User): Promise<User> {
    const response = await $axios.post("/auth/sing-in", {
      login: params.login,
      password: params.password,
    });
    return response.status === 200
      ? Promise.resolve(this.additionDataToStore(response))
      : Promise.reject(
          new RequestExceptions(response.data.statusCode, response.data.message)
        );
  },
  async signUp(params: User): Promise<User> {
    const response = await $axios.post("/auth/sing-up", {
      login: params.login,
      email: params.email,
      password: params.password,
    });
    return response.status === 200
      ? Promise.resolve(this.additionDataToStore(response))
      : Promise.reject(
          new RequestExceptions(response.data.statusCode, response.data.message)
        );
  },
  async loadUserByLogin(login: string): Promise<User> {
    const response = await $axios.get("/user/", {
      params: {
        login: login,
      },
    });
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(
          new RequestExceptions(response.data.statusCode, response.data.message)
        );
  },
  async likeProduct(product: Product) {
    return await $axios.post("/user/like/product", product);
  },

  async dislikeProduct(product: Product) {
    return await $axios.post("/user/dislike/product", product);
  },

  async addToBasket(product: Product) {
    return await $axios.post("/user/basket/add", product);
  },

  async removeFromBasket(product: Product) {
    return await $axios.post("/user/basket/remove", product);
  },

  additionDataToStore(response: AxiosResponse): User {
    console.log(response);
    localStorage.setItem("login", response.data.user.login);
    localStorage.setItem("token", "Bearer " + response.data.token);
    $axios.defaults.headers.common.Authorization = response.data.token;
    return response.data.user;
  },
};
