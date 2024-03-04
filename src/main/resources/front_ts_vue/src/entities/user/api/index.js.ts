import { defineStore } from "pinia";
import User from "@/entities/user/model/User";
import {
  ErrorResponse,
  UserActions,
  UserResponse,
} from "@/entities/user/api/model/actions";
import { ref } from "vue";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";

export const userStoreModule = defineStore("user", {
  state: () => ({
    user: {} as User,
    isLogin: ref(!!localStorage.getItem("token")),
    isLoading: ref(true),
    errorMessage: new ErrorResponse(),
  }),
  getters: {
    getUser: (state) => {
      return state.user;
    },
    isLikedProduct: (state) => {
      return (product: Product) =>
        state.user.favoriteProduct instanceof Set
          ? state.user.favoriteProduct.has(product)
          : false;
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
      console.log("LOGOUT");
      localStorage.clear();
      this.isLogin = false;
      this.user = new User(0, "", "", "", "");
      delete $axios.defaults.headers.common["Authorization"];
      //$axios.defaults.headers.common["Authorization"] = "";
    },

    likeProduct(product: Product) {
      if (this.isLogin) {
        this.user.favoriteProduct.push(product);
        UserActions.likeProduct(product);
      } else alert("Вы не авторизованы");
    },

    dislikeProduct: function (product: Product) {
      if (this.isLogin) {
        const index = this.user.favoriteProduct.indexOf(product, 0);
        if (index > -1) {
          this.user.favoriteProduct.splice(index, 1);
        }
        //UserActions.dislikeProduct(product);
      } else alert("Вы не авторизованы");
    },

    updateUser(response: UserResponse) {
      this.user = response.user;
      this.isLogin = true;
      this.errorMessage = new ErrorResponse();
      this.isLoading = false;
    },

    updateErrorMessage(response: ErrorResponse) {
      console.log(response);
      this.errorMessage = response;
    },
  },
});
