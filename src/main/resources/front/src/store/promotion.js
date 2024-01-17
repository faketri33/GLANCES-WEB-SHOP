import axios from 'axios'

export default {
  namespaced: true,
  state: {
    promotion: [],
  },
  mutations: {
    setPromotion(state, promotion){
      state.promotion = promotion;
    }
  },
  actions: {
    fetchPromotion({ commit }) {
      axios.get('http://localhost:8080/api/promotion/')
        .then(response => {
          commit('setPromotion', response.data);
        })
        .catch(error => {
            console.error(error);
        });
    },
    fetchPromotionById({ commit }, id) {
      axios.get('http://localhost:8080/api/promotion/' + id)
        .then(response => {
          commit('setPromotion', response.data);
        })
        .catch(error => {
            console.error(error);
        });
    },
  },
  getters: {
    getPromotionById: (state) => (id) => {
      return state.promotion.find((promotion) => promotion.id === id);
    },
    getPromotion: (state) => {
      return state.promotion;
    },
  },  
}