<template>
  <div class="root">
    <ul v-if="characteristics" class="list">
      <li class="item" v-for="(char, index) in characteristics" :key="index">
        {{ char.name }}, {{ char.value }}
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { defineProps } from "vue";
import { Characteristics } from "@/entities/characteristics/model/Characteristics";

defineProps<{
  characteristics?: Characteristics[];
}>();
</script>

<style scoped></style>
