import { createApp } from "vue";
import App from "./App.vue";
import router from "./app/router";
import "bootstrap/dist/css/bootstrap.css";
import { createPinia } from "pinia";

createApp(App).use(axiosPlugin).use(createPinia()).use(router).mount("#app");

import "bootstrap/dist/js/bootstrap.js";
import axiosPlugin from "@/shared/client/AxiosClient";
