<template>
  <div class="error-dialog" v-if="dialog">
    <div class="error-dialog__content">
      <h3>{{ errorTitle }}</h3>
      <p>{{ errorText }}</p>
      <div class="progress-bar" :style="{ width: progress + '%' }"></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    errorTitle: {
      type: String,
      default: "Ошибка",
    },
    errorText: {
      type: String,
      default: "",
    },
    progress: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      dialog: true,
    };
  },
};
</script>

<style>
.error-dialog {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 9999;
}

.error-dialog__content {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  text-align: center;
  position: relative;
}

.progress-bar {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 5px;
  background-color: rgb(143, 255, 199);
  transition: width 0.5s ease;
}
</style>
