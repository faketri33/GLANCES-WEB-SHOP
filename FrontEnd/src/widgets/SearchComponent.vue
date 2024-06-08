<template>
  <div class="root">
    <input
      class="form-control border rounded-2 me-2 w-100"
      type="text"
      v-model="searchQuery"
      @input="showSuggestionsHandler"
      @focus="showSuggestionsHandler"
      @blur="hideSuggestionsHandler"
      placeholder="Поиск..."
    />
    <ul
      class="position-absolute list-group"
      id="search-suggestions"
      v-show="showSuggestions"
      style="background-color: white; z-index: 999"
    >
      <slot :results="results"></slot>
    </ul>
  </div>
</template>

<script setup>
import { ref, defineEmits, watch, defineProps } from "vue";

const props = defineProps({
  results: {
    type: Array,
    required: true,
  },
});

const emit = defineEmits(["update"]);

const searchQuery = ref("");
const showSuggestions = ref(false);

const emitSearch = () => {
  emit("update:searchQuery", encodeURIComponent(searchQuery.value));
};

const showSuggestionsHandler = () => {
  if (searchQuery.value !== "") {
    showSuggestions.value = true;
  }
};

const hideSuggestionsHandler = () => {
  setTimeout(() => {
    showSuggestions.value = false;
  }, 200);
};

watch(searchQuery, emitSearch);
</script>

<style scoped></style>
