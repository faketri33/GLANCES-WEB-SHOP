import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PromoView from '../views/PromoView.vue'
import MainPages from '../views/MainPages.vue'
import AboutView from '../views/AboutView.vue'
import AuthView from '@/views/AuthView.vue'
import CatalogView from '@/views/CatalogView.vue'

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
  },
  {
    path: '/auth',
    component: AuthView
  },
  {
    path: '/catalog/:id',
    component: CatalogView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
