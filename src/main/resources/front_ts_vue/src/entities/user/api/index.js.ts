import { defineStore } from "pinia";
import { User } from "@/entities/user/model/User";
import { UserActions } from "@/entities/user/api/model/actions";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { LoginException } from "@/entities/user/model/LoginException";
import { ProductItem } from "@/entities/product/model/ProductItem";

export const userStoreModule = defineStore("user", {
  state: () => ({
    user: {} as User,
    isLogin: !!localStorage.getItem("token"),
    isLoading: true,
    errorMessage: {} as LoginException,
  }),
  getters: {
    getUser: (state) => {
      return state.user;
    },
    isLikedProduct: (state) => {
      return (id: string) => {
        return state.user.favoriteProduct?.some((product) => product.id === id);
      };
    },
    getBasketPrice: (state) =>
      state?.user.basket?.products.reduce(
        (acc, productItem) => (acc += productItem.price),
        0
      ),
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

    loadUser() {
      this.isLoading = true;
      UserActions.loadUser()
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
          UserActions.dislikeProduct(product);
        }
      } else alert("Вы не авторизованы");
    },

    addToBasket(product: Product) {
      if (this.isLogin) {
        const productItem: ProductItem = {
          id: "",
          product: product,
          quantity: 1,
          price: product.price,
        };
        this.user.basket.products.push(productItem);
        UserActions.addToBasket(product);
      } else alert("Вы не авторизованы");
    },

    removeFromBasket(product: Product) {
      if (this.isLogin) {
        const index = this.user.basket.products.findIndex((productItem) => {
          return productItem.product === product;
        }, 0);
        if (index > -1) {
          this.user.basket.products.splice(index, 1);
          UserActions.removeFromBasket(product);
        }
      } else alert("Вы не авторизованы");
    },

    updateUser(response: User) {
      console.log("USER", response);
      this.user = response;
      this.isLogin = true;
      this.isLoading = false;
    },

    updateErrorMessage(response: LoginException) {
      this.errorMessage = response;
    },
  },
});
