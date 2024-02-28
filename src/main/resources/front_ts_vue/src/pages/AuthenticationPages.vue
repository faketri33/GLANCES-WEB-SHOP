<template>
  <div class="root container-xl">
    <div class="wrapper w-75 m-0 m-auto border border-primary rounded mt-5">
      <form class="form d-flex flex-column gap-3" @submit.prevent="auth">
        <h2 v-text="isSingIn ? 'Регистарция' : 'Авторизация'"></h2>
        <label for="first"> Логин </label>
        <input
          class="input-group-text shadow"
          type="text"
          id="first"
          name="first"
          placeholder="Введите логин"
          v-model="user.login"
          required
        />

        <label v-show="isSingIn" for="email"> Почта </label>
        <input
          v-show="isSingIn"
          class="input-group-text shadow"
          type="email"
          id="email"
          name="email"
          placeholder="Введите почту"
          v-model="user.email"
        />

        <label for="password"> Пароль </label>
        <input
          class="input-group-text shadow"
          type="password"
          id="password"
          name="password"
          placeholder="Введите пароль"
          v-model="user.password"
          required
        />

        <div class="wrap">
          <button class="btn btn-outline-primary">Submit</button>
        </div>
      </form>
      <button
        class="btn-form-action btn link text-decoration-underline"
        style="color: #260dc1"
        v-on:click="isSingIn = !isSingIn"
        v-text="isSingIn ? 'Авторизация' : 'Регистарция'"
      ></button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { userStoreModule } from "@/entities/user/api/index.js";
import User from "@/entities/user/model/User";

const user = new User(0, "", "", "");

const isSingIn = ref(true);
const userStore = userStoreModule();

const auth = () => {
  if (!isSingIn.value) userStore.signIn(user);
  else userStore.signUp(user);
  console.log(user);
  console.log(!isSingIn.value);
};
</script>

<style scoped>
.form {
  padding: 3rem 3rem 1rem 3rem;
}
.btn-form-action {
  margin-left: 3rem;
}

@media screen and (max-width: 720px) {
  .form {
    padding: 1rem;
  }
  .btn-form-action {
    margin: 0;
  }
}
</style>
