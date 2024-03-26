import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "@/pages/HomeView.vue";
import CatalogView from "@/pages/CatalogView.vue";
import ProductView from "@/pages/ProductView.vue";
import CategoriesSelected from "@/pages/CategoriesSelected.vue";
import AuthenticationPages from "@/pages/AuthenticationPages.vue";
import UserPage from "@/pages/UserPages.vue";
import BasketPage from "@/pages/BasketPage.vue";
import CharacteristicsToProductPage from "@/entities/characteristics/ui/CharacteristicsToProductPage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/catalog/:id",
    name: "catalog",
    component: CatalogView,
  },
  {
    path: "/categories",
    name: "categoriesSelected",
    component: CategoriesSelected,
  },
  {
    path: "/auth",
    name: "Authentication",
    component: AuthenticationPages,
  },
  {
    path: "/product/:id",
    name: "product",
    component: ProductView,
  },
  {
    path: "/profile/",
    name: "profile",
    component: UserPage,
  },
  {
    path: "/basket/",
    name: "basket",
    component: BasketPage,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
