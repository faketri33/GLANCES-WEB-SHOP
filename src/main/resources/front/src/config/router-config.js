import  { createRouter, createWebHistory } from 'vue-router'
import MainPages from '@/view/MainPages.vue'
import ProductPage from '@/view/Products.vue'

const routes = [{
      name: 'MainPages',
      path: '/',
      component: MainPages
    },
    {
        name: 'ProductCategories',
        path: '/categories/product/:id',
        component: ProductPage,
        props: true
    }]

const router = createRouter({
  history: createWebHistory(), 
  routes
})

export default router
