import { createApp } from 'vue'
import Vuex from 'vuex';
import App from './App.vue'
import router from './router'
import product from './store/product'
import promotion from '@/store/promotion'
import categories from './store/categories';
import user from './store/user';

createApp(App).use(
    new Vuex.Store({
        modules:{
            promotion,
            product,
            categories,
            user,
        }
    })
).use(router).mount('#app')
