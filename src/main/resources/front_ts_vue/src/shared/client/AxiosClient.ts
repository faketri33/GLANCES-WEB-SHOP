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
  headers: {
    Authorization: localStorage.getItem("type") || "" + localStorage.getItem("token") || ""
  },
  validateStatus (status: number) {
    return status < 500 // Resolve only if the status code is less than 500
  }
}
/* eslint-enable */

const _axios: AxiosInstance = axios.create(config);

/* eslint-disable */
// @ts-ignore
_axios.interceptors.request.use(async (config: AxiosRequestConfig): AxiosRequestConfig | Promise<AxiosRequestConfig> => {
    config.headers.Authorization = localStorage.getItem("token");
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
    // предотвращаем зацикленный запрос, добавляя свойство _isRetry
    const originalRequest = {...error.config};
    originalRequest._isRetry = true;
    if (
      // проверим, что ошибка именно из-за невалидного accessToken
      error.response.status === 401 &&
      // проверим, что запрос не повторный
      error.config &&
      !error.config._isRetry
    ) {
      try {
        // запрос на обновление токенов
        const resp = await _axios.post("/api/auth/access");
        // сохраняем новый accessToken в localStorage
        localStorage.setItem("type", resp.data.type);
        localStorage.setItem("token", resp.data.accessToken);
        // переотправляем запрос с обновленным accessToken
        return _axios.request(originalRequest);
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