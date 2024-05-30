import { defineStore } from "pinia";
import { User } from "@/entities/user/model/User";
import { UserActions } from "@/entities/user/api/model/actions";
import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { LoginException } from "@/entities/user/model/LoginException";
import { BasketAction } from "@/entities/basket/api";

export const userStoreModule = defineStore("user", {
  state: () => ({
    user: {} as User,
    isLogin: !!localStorage.getItem("access"),
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
    isInBasketProduct: (state) => {
      return (id: string) => {
        return state.user.basket?.products.some(
          (productItem) => productItem.product.id === id
        );
      };
    },
    getBasketPrice: (state) => state?.user?.basket?.price,
  },
  actions: {
    uploadProfileImage(image: any) {
      UserActions.uploadProfileImage(image).then(
        (data) => (this.user.profileImage = data)
      );
    },
    async uploadProfileData() {
      await UserActions.updateProfile(this.user);
    },
    signIn(params: User) {
      this.isLoading = true;
      UserActions.signIn(params)
        .then((user) => this.updateUser(user))
        .catch((err) => {
          this.updateErrorMessage(err.response.data);
        });
      this.isLoading = false;
    },

    signUp(params: User) {
      this.isLoading = true;
      UserActions.signUp(params)
        .then((r) => {
          this.updateUser(r);
        })
        .catch((err) => {
          this.updateErrorMessage(err.response.data);
        });
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

    toFavorite(product: Product) {
      if (!this.isLikedProduct(product.id)) this.likeProduct(product);
      else this.dislikeProduct(product);
    },

    likeProduct(product: Product) {
      if (this.isLogin) {
        this.user.favoriteProduct.push(product);
        UserActions.likeProduct(product);
      } else alert("Вы не авторизованы");
    },

    addBasket(product: string, quantity: number) {
      if (this.isLogin) {
        BasketAction.addProductBasket(
          this.user.basket.id,
          product,
          quantity
        ).then((bakset) => (this.user.basket = bakset));
      } else alert("Вы не авторизованы");
    },

    removeBasket(product: string, quantity: number) {
      if (this.isLogin) {
        const index = this.user.basket.products.findIndex((productItem) => {
          return productItem.product.id === product;
        }, 0);
        if (index > -1) {
          this.user.basket.products.splice(index, 1);
          BasketAction.removeFromBasket(this.user.basket.id, product, quantity);
        }
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

    async addRating(productId: string, rating: object) {
      return await UserActions.addRating(productId, rating);
    },

    updateUser(response: User) {
      console.log("USER", response);
      this.user = response;
      this.isLogin = true;
      this.isLoading = false;
    },

    updateErrorMessage(response: any) {
      this.errorMessage = response;
    },
  },
});
