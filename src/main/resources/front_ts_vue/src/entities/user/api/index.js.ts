import { defineStore } from "pinia";
import { User } from "@/entities/user/model/User";
import { UserActions } from "@/entities/user/api/model/actions";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { RequestExceptions } from "@/shared/exceptions/RequestExceptions";

export const userStoreModule = defineStore("user", {
  state: () => ({
    user: {} as User,
    isLogin: !localStorage.getItem("token"),
    isLoading: true,
    errorMessage: {} as RequestExceptions,
  }),
  getters: {
    getUser: (state) => {
      return state.user;
    },
    isLikedProduct: (state) => {
      return (product: any) => state.user.favoriteProduct.some(product);
    },
  },
  actions: {
    signIn(params: User) {
      this.isLoading = true;
      UserActions.signIn(params)
        .then((r) => this.updateUser(r))
        .catch((err) => this.updateErrorMessage(err));
      this.isLoading = false;
    },

    signUp(params: User) {
      this.isLoading = true;
      UserActions.signUp(params)
        .then((r) => this.updateUser(r))
        .catch((err) => this.updateErrorMessage(err));
      this.isLoading = false;
    },

    async loadUserByLogin(login: string) {
      this.isLoading = true;
      UserActions.loadUserByLogin(login)
        .then((r) => this.updateUser(r))
        .catch((err) => this.updateErrorMessage(err));
      this.isLoading = false;
    },

    async logout() {
      localStorage.clear();
      this.isLogin = false;
      this.user = {} as User;
      delete $axios.defaults.headers.common.Authorization;
    },

    likeProduct(product: Product) {
      if (this.isLogin) {
        this.user.favoriteProduct.push(product);
        UserActions.likeProduct(product);
      } else alert("Вы не авторизованы");
    },

    dislikeProduct(product: Product) {
      if (this.isLogin) {
        const index = this.user.favoriteProduct.indexOf(product, 0);
        if (index > -1) {
          this.user.favoriteProduct.splice(index, 1);
        }
        UserActions.dislikeProduct(product);
      } else alert("Вы не авторизованы");
    },

    addToBasket(product: Product) {
      UserActions.addToBasket(product);
    },

    updateUser(response: User) {
      console.log("USER", response);
      this.user = response;
      this.isLogin = true;
      this.isLoading = false;
    },

    updateErrorMessage(response: RequestExceptions) {
      this.errorMessage = response;
    },
  },
});
