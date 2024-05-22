<template>
  <div
    id="carouselExampleControlsNoTouching"
    class="carousel slide rounded"
    data-bs-touch="false"
  >
    <div class="carousel-inner">
      <div class="carousel-item active">
        <router-link
          v-for="promo in getPromo"
          :key="promo.id"
          :to="'/promo/' + promo.id"
        >
          <img
            :src="'data:image/jpeg;base64,' + promo.banner"
            class="d-block w-100 rounded"
            :alt="promo.title"
            :title="promo.title"
          />
        </router-link>
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExampleControlsNoTouching"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExampleControlsNoTouching"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.dispatch("featchPromo");
  },
  computed: {
    getPromo() {
      return this.$store.getters["getPromo"];
    },
  },
};
</script>
