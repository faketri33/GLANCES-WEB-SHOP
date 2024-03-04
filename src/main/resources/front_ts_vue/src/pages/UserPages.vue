<template>
  <div class="root container mt-5">
    <div v-if="!userStore.isLoading" class="user-profile">
      <div class="wrapper row">
        <div class="col-2 img"><img src="" alt="" /></div>
        <div class="col w-100">
          <h3 v-text="userStore.getUser.login"></h3>
          <h6 class="text-muted" v-text="userStore.getUser.email"></h6>
        </div>
      </div>

      <form class="form-check-inline w-100 gap-5" action="">
        <input class="input-group-text" placeholder="test" />
        <input class="input-group-text" placeholder="День рождения" />
        <input
          class="input-group-text"
          :placeholder="userStore.getUser.city || 'Город'"
        />
        <input
          class="input-group-text"
          :placeholder="userStore.getUser.email"
        />
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
  userStore.logout();
  router.push("/");
};

onMounted(() => {
  if (!userStore.isLogin) router.push("/auth");
});
</script>

<style scoped></style>
