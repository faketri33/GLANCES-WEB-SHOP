<template>
  <TheHeader v-bind:isLogin="userStore.isLogin" />

  <div class="wrapper m-2">
    <div class="border rounded-2 text-center d-sm-flex">
      <div class="text-center">
        <img
          src="../../images/samsung-galaxy-s23.png"
          alt=""
          style="width: 200px"
        />
      </div>
      <div class="body p-2 gap-2 row">
        <h4
          class="card-text overflow-hidden col"
          style="
            height: 1.5rem;
            text-overflow: ellipsis;
            white-space: nowrap;
            width: 13rem;
          "
        >
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad aut
          deserunt dicta incidunt libero maxime nemo, officia voluptatibus.
          Aliquam architecto deleniti eum illum maiores neque quo ratione
          repellendus saepe sunt?
        </h4>
        <p class="price"><span style="font-weight: 700">200.000</span> Руб</p>
        <div class="info d-flex justify-content-around align-items-center col">
          <p style="color: #0d6efd; margin: 0">В наличии</p>
          <button class="btn shadow">
            <img src="/Vector.svg" alt="Добавить в избраное" />
          </button>
        </div>
        <button class="btn btn-outline-primary w-100">
          Добавить в корзину
        </button>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <div class="card">
          <img
            src="product-image.jpg"
            class="card-img-top"
            alt="Product Image"
          />
          <div class="card-body">
            <h5 class="card-title">Product Title</h5>
            <p class="card-text">
              Some quick example text to build on the product description.
            </p>
            <a href="#" class="btn btn-primary">Details</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss">
* {
  padding: 0;
  box-sizing: border-box;
  font-family: "Roboto Light", sans-serif;
}
</style>

<script setup lang="ts">
import TheHeader from "@/widgets/TheHeader.vue";
import { userStoreModule } from "@/entities/user/api/index.js";
import { onMounted } from "vue";

const userStore = userStoreModule();
onMounted(async () => {
  const login = localStorage.getItem("login");
  console.log(login);
  return login != null ? await userStore.loadUserByLogin(login) : null;
});
</script>
