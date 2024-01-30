
import axios from '../axiosConfig'

export default {
    namespaced: true,
    state: {
        user: {
            login: "",
            email: "",
            password: ""
        },
        token: String
    },
    mutations: {
        setUser(state, user) {
            state.user = user;
        }
    },
    actions: {
        fetchSignIn({ state }) {
            return new Promise(() => {
                axios.post("http://localhost:8080/api/auth/authenticate", {
                    login: state.user.login,
                    password: state.user.password,
                }).then((response) => {
                    localStorage.setItem("token", response.data.token)
                    console.log(response);
                    return response.status;
                }).catch((error) => {
                    console.log(error);
                    return error.status;
                })
            });
        },
        fetchSignUp({ state }) {
            return new Promise(() => {
                axios
                    .post("http://localhost:8080/api/auth/register", {
                        login: state.user.login,
                        email: state.user.email,
                        password: state.user.password,
                    })
            });
        },
    },
    getters: {
        getUser: (state) => {
            return state.user;
        }
    },
}
