<template>
  <div class="root">
    <div class="wrapper">
      <div class="card row">
        <div class="img col-3">
          <img
            :src="'localhost:8080/api/image/' + rating.users.profileImage.id"
            alt=""
          />
        </div>
        <div class="body col d-flex flex-column">
          <div class="username">
            {{ rating.users.login + " " + rating.grade }}
          </div>
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
