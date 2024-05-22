<template>
  <div class="root p-2 border rounded m-2">
    <div class="wrapper">
      <div class="row">
        <div class="img col-1">
          <img
            class="rounded-circle"
            :src="'http://localhost:9000/api/image/' + rating.userImage.id"
            alt=""
            style="width: 100px"
          />
        </div>
        <div class="body col-11 d-flex flex-column">
          <div class="username">
            {{ rating.username }}
          </div>
          <div class="rating">Оценка - {{ rating.grade }}</div>
          <div class="card-body">{{ rating.description }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from "vue";
import { Rating } from "@/entities/rating/model";

defineProps<{
  rating: Rating;
}>();
</script>

<style scoped></style>
