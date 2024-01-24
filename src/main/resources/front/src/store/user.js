
import axios from '../axiosConfig'

export default {
    namespaced: true,
    state: {
        user: [],
        token: String
    },
    mutations: {
        setUser(state, user) {
            state.user = user;
        },
        setToken(state, token) {
            state.token = token;
            localStorage.setItem('token', token);
        }
    },
    actions: {
        fetchSignIn({ commit }) {
            axios.get('http://localhost:8080/api/auth/', { user })
                .then(response => {
                    commit('setToken', response.data);
                })
                .catch(error => {
                    console.error(error);
                });
        },
        fetchSignUp({ commit }) {
            axios.get('http://localhost:8080/api/auth/', { user })
                .then(response => {
                    commit('setToken', response.data);
                })
                .catch(error => {
                    console.error(error);
                });
        },
    },
    getters: {
        getUser: (state) => {
            return state.user;
        }
    },
}
