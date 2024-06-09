<template>
  <router-link
    :to="'/catalog/' + categories.id"
    class="text-white d-flex justify-content-center align-items-center bg-primary m-3 rounded-3 text-decoration-none"
    style="max-width: 12rem; height: 13rem"
  >
    <div
      class="text-center p-3 d-flex flex-column align-items-center justify-content-center gap-2"
    >
      <img
        style="max-width: 120px; max-height: 134px"
        :src="'http://localhost:9000/api/image/' + categories.image?.id"
        alt=""
      />
      <h5 class="text mt-2 align-items-center w-100">
        {{ categories.name }}
      </h5>
    </div>
  </router-link>
</template>

<script>
import { Categories } from "@/entities/categories/model/Categories";

export default {
  props: {
    categories: Categories,
  },
};
</script>

<style scoped>
.text {
  text-overflow: ellipsis;
  overflow: hidden;
  max-height: 3.6em;
  white-space: normal;
}
</style>
