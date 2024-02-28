import axios from "axios";

const BASE_URL = "http://127.0.0.1:8080/api";

export const headers = {
  Authorization:
    "Bearer " + !!localStorage.getItem("token")
      ? localStorage.getItem("token")
      : "",
};

export const axiosInstance = axios.create({
  baseURL: BASE_URL,
});
