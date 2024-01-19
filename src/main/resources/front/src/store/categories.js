
import axios from 'axios'

export default {
  namespaced: true,
  state: {
    categories: [],
  },
  mutations: {
    setCategories(state, categories) {
      state.categories = categories;
    },
  },
  actions: {
    fetchCategories({ commit }) {
      axios.get('http://localhost:8080/api/categories/')
        .then(response => {
           commit('setCategories', response.data);
        })
        .catch(error => {
            console.error(error);
        });
    },
  },
  getters: {
    getCategories: (state) => {
      return state.categories;
    },
    getCategoriesById: (state) => (id) => {
      return state.categories.find((categories) => categories.id === id);
    },
  },
}
