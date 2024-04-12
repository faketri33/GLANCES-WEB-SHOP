import axios, { AxiosInstance } from "axios";
import { Vue } from "vue-class-component";

const BASE_URL = "http://127.0.0.1:9000/api";
// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

/* eslint-disable */
const config = {
  baseURL: BASE_URL,
  timeout: 30000,
}
/* eslint-enable */

const _axios: AxiosInstance = axios.create(config);

/* eslint-disable */
// @ts-ignore
_axios.interceptors.request.use(async (config: AxiosRequestConfig): AxiosRequestConfig | Promise<AxiosRequestConfig> => {
    if(localStorage.getItem("access") != null)
      config.headers.Authorization = "Bearer " + localStorage.getItem("access");
    return Promise.resolve(config)
  },  (error) => {
    // Do something with request error
    return Promise.reject(error)
  }
)
/* eslint-disable */

// который в случае невалидного accessToken попытается его обновить
// и переотправить запрос с обновленным accessToken
_axios.interceptors.response.use(
  // в случае валидного accessToken ничего не делаем:
  (config) => {
    return config;
  },
  // в случае просроченного accessToken пытаемся его обновить:
  async (error) => {
    const config = error?.config;
    console.log("I'A HERE");
    if (
      error?.response?.status === 403 && !config?.sent
    ) {
      try {
        config.sent = true;
        // запрос на обновление токенов
        const resp = await _axios.post("/auth/access", {
          refreshToken: localStorage.getItem("refresh")
        });
        // сохраняем новый accessToken в localStorage
        localStorage.setItem("access", resp.data.accessToken);
        // переотправляем запрос с обновленным accessToken
        return _axios.request(config);

      } catch (error) {
        console.log("AUTH ERROR");
      }
    }
    // на случай, если возникла другая ошибка (не связанная с авторизацией)
    // пробросим эту ошибку
    throw error;
  }
);

class AxiosPlugin {
  public install () {
    Object.defineProperties(Vue.prototype, {
      axios: {
        get () {
          return _axios
        }
      },
      $axios: {
        get () {
          return _axios
        }
      }
    })
  }
}

const axiosPlugin: AxiosPlugin = new AxiosPlugin()

export default axiosPlugin
export const $axios: AxiosInstance = _axios