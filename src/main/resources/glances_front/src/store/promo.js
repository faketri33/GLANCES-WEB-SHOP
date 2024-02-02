import axios from "axios";

const loadPromo = () => {
  return new Promise((resolve) => {
    resolve(axios.get("http://localhost:8080/api/promotion/"));
  });
};

const loadPromoById = (id) => {
  return new Promise((resolve) => {
    resolve(axios.get("http://localhost:8080/api/promotion/" + id));
  });
};

export default {
  namespace: true,
  state: {
    promo: [],
  },
  getters: {
    getPromo(state) {
      return state.promo;
    },
  },
  mutations: {
    SET_PROMO(state, promo) {
      console.log(promo);
      state.promo = promo;
    },
  },
  actions: {
    async featchPromo({ commit }) {
      await loadPromo().then((data) => commit("SET_PROMO", data.data));
    },
    async featchPromoById({ commit }, id) {
      await loadPromoById(id).then((data) => commit("SET_PROMO", data.data));
    },
  },
  modules: {},
};
