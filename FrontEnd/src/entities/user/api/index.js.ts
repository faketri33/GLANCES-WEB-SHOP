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
    getBasketPrice: (state) =>
      state?.user.basket?.products.reduce(
        (acc, productItem) => (acc += productItem.price),
        0
      ),
  },
  actions: {
    uploadProfileImage(image: any) {
      UserActions.uploadProfileImage(image).then(
        (data) => (this.user.profileImage = data)
      );
    },
    signIn(params: User) {
      this.isLoading = true;
      UserActions.signIn(params)
        .then((user) => this.updateUser(user))
        .catch((err) => {
          console.log(err);
          throw err;
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
          this.updateErrorMessage(err);
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

    dislikeProduct(product: Product) {
      if (this.isLogin) {
        const index = this.user.favoriteProduct.indexOf(product, 0);
        if (index > -1) {
          this.user.favoriteProduct.splice(index, 1);
          UserActions.dislikeProduct(product);
        }
      } else alert("Вы не авторизованы");
    },

    toBasket(product: Product) {
      if (this.isInBasketProduct(product.id)) this.removeFromBasket(product);
      else this.addToBasket(product);
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
        UserActions.addToBasket(productItem);
      } else alert("Вы не авторизованы");
    },

    createOrder(product: Array<ProductItem>) {
      if (this.isLogin) {
        UserActions.createOrder(product);
      } else alert("Вы не авторизованы");
    },

    removeFromBasket(product: Product) {
      if (this.isLogin) {
        console.log("удаляем");
        const index = this.user.basket.products.findIndex((productItem) => {
          return productItem.product.id === product.id;
        }, 0);
        if (index > -1) {
          this.user.basket.products.splice(index, 1);
          UserActions.removeFromBasket(product);
        }
      } else alert("Вы не авторизованы");
    },

    addRating(productId: string, rating: object) {
      UserActions.addRating(productId, rating);
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
