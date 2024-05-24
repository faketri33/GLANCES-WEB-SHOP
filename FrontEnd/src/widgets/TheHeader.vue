<template>
  <header class="header border-bottom" style="height: 80px">
    <div class="container-xxl">
      <nav
        class="d-flex w-100 align-items-center navigation"
        style="padding: 0; width: 100%"
      >
        <div class="header__item col-xl-3 col-md-3 col-sm-9 col-9">
          <router-link to="/" class="logo text-decoration-none"
            ><h2 class="logo__name text-center">GLANCES</h2></router-link
          >
        </div>
        <div class="header__item col-xl-6 col-md-4 col-sm-9">
          <search-component class="w-100"></search-component>
        </div>
        <div
          class="tab__bar col-xl-3 col-md-5 col-lg-4 col-xs-9 col-sm-9 mt-2 row"
        >
          <router-link
            class="tab__bar__item col flex text-center text-decoration-none"
            to="/categories/"
          >
            <img src="../app/assets/img/catalog.svg" alt="" />
            <p class="mt-1 text-black">Каталог</p>
          </router-link>

          <router-link
            class="tab__bar__item col flex text-center text-decoration-none"
            to="/basket/"
          >
            <img src="../app/assets/img/basket.svg" alt="" />
            <p class="mt-1 text-black">Корзина</p>
          </router-link>

          <router-link
            class="tab__bar__item col flex text-center text-decoration-none"
            :to="isLogin ? '/profile/' : '/auth/'"
          >
            <img src="../app/assets/img/profil.svg" alt="" />
            <p class="mt-1 text-black" v-text="isLogin ? 'Профиль' : 'Войти'" />
          </router-link>
        </div>
      </nav>
    </div>
  </header>
</template>

<script setup>
import SearchComponent from "./SearchComponent.vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import { defineProps } from "vue";

defineProps({
  isLogin: Boolean,
});
</script>

<style lang="scss" scoped>
.header {
  margin-top: 15px;
}

@font-face {
  font-family: "Disket Mono";
  src: url("../../src/app/assets/font/Disket-Mono-Regular.ttf");
}

.logo__name {
  font-family: "Disket Mono", sans-serif;
  font-size: 28px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;

  background: linear-gradient(267deg, #260dc1 6.29%, #59006f 100%);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  margin: 0;
}

.tab__bar {
  p {
    margin: 0;
  }
}

@media screen and (max-width: 768px) {
  .tab__bar {
    position: fixed;
    bottom: 0;
    z-index: 999;
    background-color: white;
    width: 100%;
    .tab__bar__item {
      border-left: 1px solid #dee2e6;
    }
  }
  .navigation {
    flex-direction: column;
    padding-bottom: 2rem;
  }
}
</style>
