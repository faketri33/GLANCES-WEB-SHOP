import User from "@/entities/user/model/User";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { AxiosResponse } from "axios";

export class UserResponse {
  token: string;
  user: User;

  constructor(token: string, user: User) {
    this.user = user;
    this.token = token;
  }
}
export class ErrorResponse {
  statusCode: number;
  message: [];

  constructor(statusCode?: number, message?: []) {
    this.statusCode = statusCode || 0;
    this.message = message || [];
  }
}

export const UserActions = {
  async signIn(params: User): Promise<UserResponse> {
    const response = await $axios.post("/auth/sing-in", {
      login: params.login,
      password: params.password,
    });
    return response.status === 200
      ? Promise.resolve(this.additionDataToStore(response))
      : Promise.reject(
          new ErrorResponse(response.data.statusCode, response.data.message)
        );
  },
  async signUp(params: User): Promise<UserResponse> {
    const response = await $axios.post("/auth/sing-up", {
      login: params.login,
      email: params.email,
      password: params.password,
    });
    return response.status === 200
      ? Promise.resolve(this.additionDataToStore(response))
      : Promise.reject(
          new ErrorResponse(response.data.statusCode, response.data.message)
        );
  },
  async loadUserByLogin(login: string): Promise<UserResponse> {
    const response = await $axios.get("/user/", {
      params: {
        login: login,
      },
    });
    return response.status === 200
      ? Promise.resolve(new UserResponse("", response.data))
      : Promise.reject(
          new ErrorResponse(response.data.statusCode, response.data.message)
        );
  },
  async likeProduct(product: Product) {
    return await $axios.post("/user/like/product", product);
  },

  additionDataToStore(response: AxiosResponse): UserResponse {
    console.log(response);
    localStorage.setItem("login", response.data.login);
    /*$axios.defaults.headers.common["Authorization"] =
      "Bearer " + response.data.token;*/
    return new UserResponse(response.data.token, response.data.user);
  },
};
