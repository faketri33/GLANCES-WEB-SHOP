<template>
  <router-link
    :to="'/product/caregories/' + categories.id"
    class="card text-white bg-primary m-3 text-decoration-none"
    style="max-width: 12rem; max-height: 13rem"
  >
    <div class="card-body text-center">
      <img
        style="width: 100px; height: 134px"
        :src="'data:image/jpeg;base64,' + categories.image"
        alt=""
      />
      <h5 class="card-text mt-2">{{ categories.name }}</h5>
    </div>
  </router-link>
</template>

<script>
export default {
  props: {
    categories: {
      id: Number,
      name: String,
      image: [],
    },
  },
};
</script>
