import User from "@/entities/user/model/User";
import { axiosInstance } from "@/shared/client/AxiosClient";

export const UserActions = {
  signIn(params: User): Promise<string> {
    return new Promise((resolve) =>
      axiosInstance
        .post("/auth/sing-in", {
          login: params.login,
          password: params.password,
        })
        .then((data) => resolve(data.data.token))
    );
  },
  signUp(params: User): Promise<string> {
    return new Promise((resolve) =>
      axiosInstance
        .post("/auth/sing-up", {
          login: params.login,
          email: params.email,
          password: params.password,
        })
        .then((data) => resolve(data.data.token))
    );
  },
};
