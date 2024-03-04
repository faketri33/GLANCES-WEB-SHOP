import axios, { AxiosInstance, AxiosResponse } from "axios";
import { Vue } from "vue-class-component";

const BASE_URL = "http://127.0.0.1:8080/api";
localStorage.setItem("token", "");
// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

/* eslint-disable */
const config = {
  baseURL: BASE_URL,
  timeout: 30000,
  validateStatus (status: number) {
    return status < 500 // Resolve only if the status code is less than 500
  }
}
/* eslint-enable */

const _axios: AxiosInstance = axios.create(config);

/* eslint-disable */
// @ts-ignore
_axios.interceptors.request.use(async (config: AxiosRequestConfig): AxiosRequestConfig | Promise<AxiosRequestConfig> => {
  console.log("HEADERS CFG ", config);

    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = token;
    }
    return Promise.resolve(config)
  },  (error) => {
    // Do something with request error
    return Promise.reject(error)
  }
)
/* eslint-disable */

// Add a response interceptor
_axios.interceptors.response.use((response): Promise<AxiosResponse> | any => {

  const { headers } = response;
  const authorizationHeader = headers.authorization || headers.Authorization; // Check for both lowercase and uppercase headers
  console.log("HEADER ",authorizationHeader);
  console.log("TOKE",localStorage.getItem("token"))
  if (authorizationHeader) {
    localStorage.setItem("token", authorizationHeader);
  }
  if (response.status === 403) alert("Вы не авторизованы");
  return response
  }, function (error) {
      // Do something with response error
      return Promise.reject(error.response);
  }
)

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