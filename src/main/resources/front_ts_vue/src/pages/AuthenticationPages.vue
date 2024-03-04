<template>
  <div class="root container-xl">
    <div class="wrapper w-75 m-0 m-auto border border-primary rounded mt-5">
      <form class="form d-flex flex-column gap-3" @submit.prevent="auth">
        <h2 v-text="isSingIn ? 'Регистарция' : 'Авторизация'"></h2>
        <p
          v-if="userStore.errorMessage?.statusCode === 404"
          class="border rounded border-warning"
        >
          {{ userStore.errorMessage?.message }}
        </p>
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
        <p
          class="border rounded text-center border-warning"
          v-if="userStore.errorMessage?.message?.hasOwnProperty('login')"
        >
          {{ userStore.errorMessage.message["login"] }}
        </p>

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
        <p
          class="border rounded text-center border-warning"
          v-if="userStore.errorMessage?.message?.hasOwnProperty('email')"
        >
          {{ userStore.errorMessage.message["email"] }}
        </p>

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
        <p
          class="border rounded text-center border-warning"
          v-if="userStore.errorMessage?.message?.hasOwnProperty('password')"
        >
          {{ userStore.errorMessage.message["password"] }}
        </p>
        <div class="wrap">
          <button
            class="btn btn-outline-primary"
            v-text="isSingIn ? 'Регистарция' : 'Авторизация'"
          ></button>
        </div>

        <p
          class="border rounded text-center border-success"
          v-if="userStore.isLogin"
        >
          Вы успешно авторизованы
        </p>
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
import { onMounted, ref } from "vue";
import { userStoreModule } from "@/entities/user/api/index.js";
import User from "@/entities/user/model/User";
import { ErrorResponse } from "@/entities/user/api/model/actions";

const user = new User(0, "", "", "", "");

const isSingIn = ref(true);
const userStore = userStoreModule();

const auth = () => {
  if (!isSingIn.value) userStore.signIn(user);
  else userStore.signUp(user);
};

onMounted(() => (userStore.errorMessage = new ErrorResponse()));
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
