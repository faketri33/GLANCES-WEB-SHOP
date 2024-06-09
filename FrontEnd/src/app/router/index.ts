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
import ProductPages from "@/pages/employee/ProductPages.vue";
import ProductUpdate from "@/entities/product/ui/ProductUpdate.vue";
import OrdersList from "@/entities/orders/ui/OrdersList.vue";
import OrderAbout from "@/entities/orders/ui/OrderAbout.vue";
import BrandCreate from "@/entities/brand/ui/BrandCreate.vue";
import CategoriesCreate from "@/entities/categories/ui/CategoriesCreate.vue";
import UserOrders from "@/pages/UserOrders.vue";
import ProductDelete from "@/entities/product/ui/ProductDelete.vue";
import PromoPages from "@/pages/employee/PromoPages.vue";
import PromoCreate from "@/entities/promotion/ui/PromoCreate.vue";
import PromoUpdate from "@/entities/promotion/ui/PromoUpdate.vue";
import CategoriesPages from "@/pages/employee/CategoriesPages.vue";
import BrandPages from "@/pages/employee/BrandPages.vue";
import PromoDelete from "@/entities/promotion/ui/PromoDelete.vue";
import BrandUpdate from "@/entities/brand/ui/BrandUpdate.vue";
import BrandDelete from "@/entities/brand/ui/BrandDelete.vue";
import CategoriesUpdate from "@/entities/categories/ui/CategoriesUpdate.vue";
import CategoriesDelete from "@/entities/categories/ui/CategoriesDelete.vue";

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
        path: "/user/workspace/promo/",
        name: "PromoPage",
        component: PromoPages,
        children: [
          {
            path: "/user/workspace/promo/create",
            name: "PromoCreate",
            component: PromoCreate,
          },
          {
            path: "/user/workspace/promo/update",
            name: "PromoUpdate",
            component: PromoUpdate,
          },
          {
            path: "/user/workspace/promo/delete",
            name: "PromoDelete",
            component: PromoDelete,
          },
        ],
      },
      {
        path: "/user/workspace/product/",
        name: "ProductPages",
        component: ProductPages,
        children: [
          {
            path: "/user/workspace/product/create",
            name: "ProductCreate",
            component: ProductCreate,
          },
          {
            path: "/user/workspace/product/update",
            name: "ProductUpdate",
            component: ProductUpdate,
          },
          {
            path: "/user/workspace/product/delete",
            name: "ProductDelete",
            component: ProductDelete,
          },
        ],
      },
      {
        path: "/user/workspace/brand",
        name: "BrandPage",
        component: BrandPages,
        children: [
          {
            path: "/user/workspace/brand/create",
            name: "BrandCreate",
            component: BrandCreate,
          },
          {
            path: "/user/workspace/brand/update",
            name: "BrandUpdate",
            component: BrandUpdate,
          },
          {
            path: "/user/workspace/brand/delete",
            name: "BrandDelete",
            component: BrandDelete,
          },
        ],
      },
      {
        path: "/user/workspace/categories",
        name: "CategoriesPage",
        component: CategoriesPages,
        children: [
          {
            path: "/user/workspace/categories/create",
            name: "CategoriesCreate",
            component: CategoriesCreate,
          },
          {
            path: "/user/workspace/categories/update",
            name: "CategoriesUpdate",
            component: CategoriesUpdate,
          },
          {
            path: "/user/workspace/categories/delete",
            name: "CategoriesDelete",
            component: CategoriesDelete,
          },
        ],
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
