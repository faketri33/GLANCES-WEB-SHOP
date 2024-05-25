<template>
  <div class="root">
    <input
      class="form-control border rounded-2 me-2 w-100"
      type="text"
      v-model="searchQuery"
      @focus="showSuggestionsHandler"
      @blur="hideSuggestionsHandler"
      @input="fetchSuggestionsHandler"
      placeholder="Поиск..."
    />
    <ul
      class="position-absolute list-group"
      id="search-suggestions"
      v-show="showSuggestions"
      style="background-color: white; z-index: 999"
    >
      <li
        class="list-group-item"
        v-for="suggestion in suggestions.content"
        @click="redirectToPageHandler(suggestion)"
        :key="suggestion.id"
      >
        <router-link :to="'/product/' + suggestion.id">
          <div class="wrap d-flex">
            <img
              :src="
                'http://localhost:9000/api/image/' + suggestion?.image[0]?.id
              "
              :alt="suggestion.nameModel"
              style="width: 50px; height: 50px"
              class="mx-2"
            />
            {{ suggestion.brand.name }} {{ suggestion.nameModel }}
          </div>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ProductActions } from "@/entities/product/api/model/Actions";
import { ref, watch } from "vue";

const searchQuery = ref("");
const suggestions = ref([]);
const showSuggestions = ref(false);

const showSuggestionsHandler = () => {
  if (searchQuery.value.trim() !== "") {
    showSuggestions.value = true;
  }
};

const hideSuggestionsHandler = () => {
  setTimeout(() => {
    showSuggestions.value = false;
  }, 200);
};

const fetchSuggestionsHandler = async () => {
  if (searchQuery.value.trim() !== "" && searchQuery.value.length > 3) {
    suggestions.value = await ProductActions.searchProduct(
      0,
      10,
      null,
      null,
      searchQuery.value
    );
    showSuggestions.value = true;
  } else {
    hideSuggestionsHandler();
  }
};

const redirectToPageHandler = (suggestion) => {
  // Здесь происходит переадресация на другую страницу
  console.log("Redirecting to: ", suggestion);
};

watch(searchQuery, fetchSuggestionsHandler);
</script>

<style scoped></style>
