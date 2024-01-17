
import axios from 'axios'

export default {
  namespaced: true,
  state: {
    products: [],
  },
  mutations: {
    setProducts(state, products) {
      state.products = products;
    },
  },
  actions: {
    fetchProducts({ commit }) {
      axios.get('http://localhost:8080/api/product/')
        .then(response => {
           commit('setProducts', response.data);
        })
        .catch(error => {
            console.error(error);
        });
    },
  },
  getters: {
    getProduct: (state) => {
      return state.products;
    },
    getProductById: (state) => (id) => {
      return state.products.find((product) => product.id === id);
    },
  },
}
