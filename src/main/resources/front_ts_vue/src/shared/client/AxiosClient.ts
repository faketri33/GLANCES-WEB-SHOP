import {
  IAuthTokens,
  TokenRefreshRequest,
  applyAuthTokenInterceptor,
  getBrowserLocalStorage,
} from "axios-jwt";
import axios from "axios";

const BASE_URL = "http://localhost:8080/api";

// 1. Create an axios instance that you wish to apply the interceptor to
export const axiosInstance = axios.create({ baseURL: BASE_URL });

// 2. Define token refresh function.
const requestRefresh: TokenRefreshRequest = async (): Promise<
  IAuthTokens | string
> => {
  // Important! Do NOT use the axios instance that you supplied to applyAuthTokenInterceptor (in our case 'axiosInstance')
  // because this will result in an infinite loop when trying to refresh the token.
  // Use the global axios client or a different instance
  const login: string | null = localStorage.getItem("login");
  const password: string | null = localStorage.getItem("password");

  const response = await axios.post(`${BASE_URL}/auth/refresh_token`, {
    login: login,
    password: password,
  });

  // If your backend supports rotating refresh tokens, you may also choose to return an object containing both tokens:
  // return {
  //  accessToken: response.data.access_token,
  //  refreshToken: response.data.refresh_token
  //}

  return response.data.access_token;
};

// 3. Add interceptor to your axios instance
// eslint-disable-next-line prettier/prettier
applyAuthTokenInterceptor(axiosInstance, { requestRefresh });

// New to 2.2.0+: initialize with storage: localStorage/sessionStorage/nativeStorage. Helpers: getBrowserLocalStorage, getBrowserSessionStorage
const getStorage = getBrowserLocalStorage;

// You can create you own storage, it has to comply with type StorageType
applyAuthTokenInterceptor(axiosInstance, { requestRefresh, getStorage });
