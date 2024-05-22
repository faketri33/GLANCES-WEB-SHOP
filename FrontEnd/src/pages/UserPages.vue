<template>
  <div class="root container mt-5">
    <div v-if="userStore.user" class="user-profile shadow border p-4 rounded-2">
      <div class="wrapper row">
        <div class="col-12 col-md-4 col-lg-3 img text-center position-relative">
          <img
            class="border rounded-5 profile-image"
            :src="
              'http://localhost:9000/api/image/' +
              userStore.getUser?.profileImage?.id
            "
            alt="Изображение профиля"
            style="max-width: 200px"
            @mouseover="showLoadingOverlay"
            @mouseleave="hideLoadingOverlay"
            @click="showFileInput"
          />
          <i class="fa-solid fa-upload"></i>
        </div>
        <div class="col mt-5">
          <div class="info flex-column ms-3">
            <h3 v-text="userStore.getUser.login"></h3>
            <h6 class="text-muted" v-text="userStore.getUser.email"></h6>
            <RouterLink
              v-if="userStore.getUser?.role?.some((r) => r === 'EMPLOYEE')"
              :to="'/user/workspace/orders/list'"
              >Кабинет работника</RouterLink
            >
          </div>
        </div>
      </div>
      <h1 class="mt-3">Данные пользователя</h1>
      <form
        class="d-flex border p-3 flex-column col-12 col-sm-9 col-md-6 gap-3 mt-5"
        action=""
      >
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
          :placeholder="userStore.getUser.dateOfBirthday || 'День рождения'"
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

      <div class="oredrs shadow p-3 m-2">
        <h2>Заказы - {{ userStore.getUser.orders?.length || 0 }}</h2>
        <div
          v-for="(order, index) in userStore.getUser.orders"
          :key="index"
          class="content"
        >
          <p>
            {{ index + 1 }}) Номер заказа - {{ order.id }}, цена -
            {{ order.price }}, статус - {{ order.statusOrder }}
          </p>
        </div>
      </div>

      <button class="btn btn-light" @click="userLogout">Выйти</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { userStoreModule } from "@/entities/user/api/index.js";
import { useRouter } from "vue-router";
import { onMounted, ref } from "vue";

const router = useRouter();
const userStore = userStoreModule();
const loadingOverlay = ref(false);

const showLoadingOverlay = () => {
  loadingOverlay.value = true;
};
const hideLoadingOverlay = () => {
  loadingOverlay.value = false;
};
const showFileInput = () => {
  // Show file input dialog
  const fileInput = document.createElement("input");
  fileInput.type = "file";
  fileInput.accept = "image/*";
  fileInput.addEventListener("change", (e) => {
    // Handle file selection
    console.log(e.target.files[0]);
  });
  document.body.appendChild(fileInput);
  fileInput.click();
};

const userLogout = () => {
  userStore.logout();
  router.push("/");
};
onMounted(() => {
  if (!userStore.isLogin) router.push("/auth");
});
</script>

<style scoped>
.profile-image:hover,
.upload-icon:hover {
  display: block;
}

.upload-icon {
  display: none;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  cursor: pointer;
  font-size: 2rem;
  color: rgba(0, 0, 0, 0.5);
}
</style>
