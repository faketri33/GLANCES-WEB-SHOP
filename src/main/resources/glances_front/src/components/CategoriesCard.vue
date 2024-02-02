<template>
  <div
    class="card text-white bg-primary m-3"
    style="max-width: 12rem; max-height: 13rem"
  >
    <div class="card-body text-center">
      <img src="../../../../../../assets/Mask group.png" alt="" />
      <h5 class="card-text mt-2">Смартфоны</h5>
    </div>
  </div>
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
