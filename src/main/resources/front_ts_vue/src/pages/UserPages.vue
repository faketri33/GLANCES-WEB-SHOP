<template>
  <div class="root container mt-5">
    <div v-if="!userStore.isLoading" class="user-profile">
      <div class="wrapper row">
        <div class="col-12 col-md-4 col-lg-3 img text-center">
          <img
            class="border rounded-5"
            :src="
              'http://localhost:8080/api/image/' +
              userStore.getUser?.profileImage?.id
            "
            alt="Изображения профиля"
            style="max-width: 200px"
          />
        </div>
        <div class="col mt-5">
          <div class="info flex-column ms-3">
            <h3 v-text="userStore.getUser.login"></h3>
            <h6 class="text-muted" v-text="userStore.getUser.email"></h6>
          </div>
        </div>
      </div>

      <form class="d-flex flex-column col-md-5 gap-3 mt-5" action="">
        <input
          class="input-group-text"
          :placeholder="userStore.getUser.name || 'Имя'"
        />
        <input
          class="input-group-text"
          :placeholder="userStore.getUser.surname || 'Фамилия'"
        />
        <input
          class="input-group-text"
          :placeholder="userStore.getUser._dateOfBirthday || 'День рождения'"
        />
        <input
          class="input-group-text"
          :placeholder="userStore.getUser.city || 'Город'"
        />
        <input
          class="input-group-text"
          :placeholder="userStore.getUser.email"
        />
        <button class="btn btn-success">Сохранить</button>
      </form>

      <h2>Заказы - {{ userStore.getUser.orders?.length || 0 }}</h2>

      <button class="btn btn-light" @click="userLogout">Выйти</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { userStoreModule } from "@/entities/user/api/index.js";
import { useRouter } from "vue-router";
import { onMounted } from "vue";

const router = useRouter();
const userStore = userStoreModule();

const userLogout = () => {
  router.push("/");
  userStore.logout();
};

onMounted(() => {
  if (!userStore.isLogin) router.push("/auth");
});
</script>

<style scoped></style>
