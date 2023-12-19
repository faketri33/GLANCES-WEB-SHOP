import  {createApp}  from 'vue'
import App from '@/App.vue'
import router from '@/config/router-config.js'
import '@/css/bootstrap.min.css'


const app = createApp(App)
app.use(router)
app.mount("#app")


