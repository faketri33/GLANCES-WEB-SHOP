<script setup>
import SearchComponent from "@/widgets/SearchComponent.vue";
import { ref, watch } from "vue";
import { PromotionAction } from "@/entities/promotion/api/PromotionAction";

const promotions = ref([]);
const searchQueryPromo = ref("");
const promotion = ref({
  id: "",
  title: "",
  description: "",
  conditions: "",
  dateOfStart: "",
  dateOfEnd: "",
  promotionProductItems: [],
});

const selectPromo = (promo) => {
  promotion.value = promo;
};

const deletePromo = () => {
  if (promotion.value.id) {
    PromotionAction.delete(promotion.value.id);
  }
};

const loadPromo = async (searchQueryPromo) => {
  promotions.value = await PromotionAction.loadPromoByName(
    searchQueryPromo,
    0,
    10
  );
};

watch(searchQueryPromo, (newQuery) => {
  if (newQuery) {
    loadPromo(newQuery);
  } else {
    promotions.value = [];
  }
});
</script>

<template>
  <div class="root">
    <p>Выберите акцию при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQueryPromo"
      :results="promotions?.content"
      class="w-100 mb-4"
    >
      <template v-slot:default="{ results }">
        <ul>
          <li
            class="list-group-item"
            v-for="promotion in results"
            :key="promotion.id"
          >
            <button @click="selectPromo(promotion)" class="btn wrap d-flex">
              <img
                :src="
                  'http://localhost:9000/api/image/' + promotion?.banner?.id
                "
                :alt="promotion.title"
                style="width: 50px; height: 50px"
                class="mx-2"
              />
              {{ promotion.title }}
            </button>
          </li>
        </ul>
      </template>
    </search-component>
    <div v-if="promotion.id" class="selected-product">
      <p>
        Выбрана акция
        {{ promotion.title }}
      </p>
      <button class="btn btn-outline-danger" @click="deletePromo">
        Удалить
      </button>
    </div>
  </div>
</template>

<style scoped></style>
