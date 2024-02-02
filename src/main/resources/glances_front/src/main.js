import "bootstrap/dist/css/bootstrap.css";
import { createApp } from "vue";
import Vuex from "vuex";
import App from "./App.vue";
import router from "./router";
import product from "./store/index";
import promo from "./store/promo";

createApp(App)
  .use(
    new Vuex.Store({
      modules: {
        promo,
        product,
      },
    })
  )
  .use(router)
  .mount("#app");

import "bootstrap/dist/js/bootstrap.js";
