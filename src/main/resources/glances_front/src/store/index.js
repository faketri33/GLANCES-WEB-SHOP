import axios from "axios";

const loadProduct = () => {
  return new Promise((resolve) => {
    resolve(axios.get("http://localhost:8080/api/product/"));
  });
};

export default {
  namespace: true,
  state: {
    product: [],
  },
  getters: {
    getProduct(state) {
      return state.product;
    },
  },
  mutations: {
    SET_PRODUCTS(state, product) {
      console.log(product);
      state.product = product;
    },
  },
  actions: {
    async featchProduct({ commit }) {
      await loadProduct().then((data) => commit("SET_PRODUCTS", data.data));
    },
  },
  modules: {},
};
