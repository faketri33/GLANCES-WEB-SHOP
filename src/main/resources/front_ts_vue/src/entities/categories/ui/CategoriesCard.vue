<template>
  <router-link
    :to="'/catalog/' + categories.id"
    class="card text-white bg-primary m-3 text-decoration-none"
    style="max-width: 12rem; max-height: 13rem"
  >
    <div class="card-body text-center">
      <img
        style="max-width: 120px; max-height: 134px"
        :src="'http://localhost:8080/api/image/' + categories.image.id"
        alt=""
      />
      <h5 class="card-text mt-2">{{ categories.name }}</h5>
    </div>
  </router-link>
</template>

<script>
import Categories from "@/entities/categories/model/Categories";

export default {
  props: {
    categories: Categories,
  },
};
</script>
