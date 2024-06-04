import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "@/pages/HomeView.vue";
import CatalogView from "@/pages/CatalogView.vue";
import ProductView from "@/pages/ProductView.vue";
import CategoriesSelected from "@/pages/CategoriesSelected.vue";
import AuthenticationPages from "@/pages/AuthenticationPages.vue";
import UserPage from "@/pages/UserPages.vue";
import BasketPage from "@/pages/BasketPage.vue";
import PromotionView from "@/pages/PromotionView.vue";
import WorkSpace from "@/pages/employee/WorkSpace.vue";
import OrdersPages from "@/pages/employee/OrdersPages.vue";
import ProductCreate from "@/entities/product/ui/ProductCreate.vue";
import OrdersList from "@/entities/orders/ui/OrdersList.vue";
import OrderAbout from "@/entities/orders/ui/OrderAbout.vue";
import BrandCreate from "@/entities/brand/ui/BrandCreate.vue";
import CategoriesCreate from "@/entities/categories/ui/CategoriesCreate.vue";
import UserOrders from "@/pages/UserOrders.vue";

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
  {
    path: "/promotion/:id",
    name: "promotion",
    component: PromotionView,
  },
  {
    path: "/user/:id/orders/",
    name: "UsersOrders",
    component: UserOrders,
  },
  {
    path: "/user/:id/orders/about/",
    name: "UsersOrdersAbout",
    component: OrderAbout,
  },
  {
    path: "/user/workspace",
    name: "workspace",
    component: WorkSpace,
    children: [
      {
        path: "/user/workspace/product/create",
        name: "ProductCreate",
        component: ProductCreate,
      },
      {
        path: "/user/workspace/brand/create",
        name: "BrandPage",
        component: BrandCreate,
      },
      {
        path: "/user/workspace/categories/create",
        name: "CategoriesCreate",
        component: CategoriesCreate,
      },
      {
        path: "/user/workspace/orders",
        name: "orders",
        component: OrdersPages,
        children: [
          {
            path: "/user/workspace/orders/about/:id",
            name: "order-about",
            component: OrderAbout,
          },
          {
            path: "/user/workspace/orders/list",
            name: "order-list",
            component: OrdersList,
          },
        ],
      },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
