import { User } from "@/entities/user/model/User";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { AxiosResponse } from "axios";

export const UserActions = {
  async signIn(params: User): Promise<User> {
    const response = await $axios.post("/auth/sing-in", {
      login: params.login,
      password: params.password,
    });

    return response.status === 200
      ? Promise.resolve(this.additionDataToStore(response.data))
      : Promise.reject(response.data);
  },
  async signUp(params: User): Promise<User> {
    const response = await $axios.post("/auth/sing-up", {
      login: params.login,
      email: params.email,
      password: params.password,
    });

    return response.status === 200
      ? Promise.resolve(this.additionDataToStore(response.data))
      : Promise.reject(response.data);
  },
  async loadUser(): Promise<User> {
    const response = await $axios.get("/user/");
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response.data);
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

  additionDataToStore(response: any): User {
    console.log(response.user);
    localStorage.setItem("login", response.user.login);
    localStorage.setItem("access", response.accessToken);
    localStorage.setItem("refresh", response.refreshToken);
    return response.user;
  },
};
