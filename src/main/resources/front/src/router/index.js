import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PromoView from '../views/PromoView.vue'
import MainPages from '../views/MainPages.vue'
import AboutView from '../views/AboutView.vue'

const routes = [
  {
    path: '/',
    component:
      MainPages
    
  },
  {
    path: '/promo/:id',
    component: PromoView
    
  },
  {
    path: '/about',
    component: AboutView
  },
  {
    path: '/home',
    component: HomeView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
