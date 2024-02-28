import { defineStore } from "pinia";
import User from "@/entities/user/model/User";
import { UserActions } from "@/entities/user/api/model/actions";
import { ref } from "vue";

export const userStoreModule = defineStore("user", {
  state: () => ({
    user: {} as User,
    isLogin: ref(!!localStorage.getItem("token")),
  }),
  getters: {
    getUser: (state): User => {
      return state.user;
    },
  },
  actions: {
    async signIn(params: User) {
      const response = await UserActions.signIn(params);
      console.log(response);
      localStorage.setItem("token", response);
    },
    async signUp(params: User) {
      const response = await UserActions.signUp(params);
      localStorage.setItem("token", response);
    },
    async logout() {
      localStorage.clear();
    },
  },
});
