<template>
  <div class="modal" v-if="show">
    <div class="modal-content">
      <slot></slot>
      <button class="btn btn-primary mt-2" @click="emit('close')">
        Закрыть
      </button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { defineProps, defineEmits } from "vue";

defineProps<{
  show: boolean;
}>();

const emit = defineEmits(["close"]);
</script>

<style scoped>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  width: 25rem;
  border: 1px solid #ccc;
}
</style>
