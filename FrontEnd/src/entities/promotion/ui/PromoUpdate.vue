<template>
  <div class="root container">
    <p>Выберите продукт при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQueryPromo"
      :results="promotions.content"
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
    <p>Выберите продукт при помощи поиска</p>
    <search-component
      v-model:searchQuery="searchQuery"
      :results="products.content"
      class="w-100 mb-4"
    >
      <template v-slot:default="{ results }">
        <ul>
          <li
            class="list-group-item"
            v-for="product in results"
            :key="product.id"
          >
            <button @click="selectProduct(product)" class="btn wrap d-flex">
              <img
                :src="
                  'http://localhost:9000/api/image/' + product?.image[0]?.id
                "
                :alt="product.nameModel"
                style="width: 50px; height: 50px"
                class="mx-2"
              />
              {{ product.brand.name }} {{ product.nameModel }}
            </button>
          </li>
        </ul>
      </template>
    </search-component>
    <form @submit.prevent="submitPromotion">
      <div class="form-group">
        <label for="title">Название акции</label>
        <input
          type="text"
          class="form-control"
          id="title"
          v-model="promotion.title"
          required
        />
      </div>
      <label class="form-label m-0" for="upload-file"
        >Добавить изображения</label
      >
      <input
        class="form-control"
        type="file"
        accept="image/jpeg, image/gif, image/png"
        id="upload-file"
        placeholder="Изображение"
        @change="handleFileSelect"
      />
      <div class="form-group">
        <label for="description">Описание</label>
        <textarea
          class="form-control"
          id="description"
          v-model="promotion.description"
          rows="3"
          required
        ></textarea>
      </div>
      <div class="form-group">
        <label for="conditions">Условия</label>
        <textarea
          class="form-control"
          id="conditions"
          v-model="promotion.conditions"
          rows="3"
          required
        ></textarea>
      </div>
      <div class="form-group">
        <label for="dateOfStart">Дата начала</label>
        <input
          type="date"
          class="form-control"
          id="dateOfStart"
          :min="minStartDay"
          v-model="promotion.dateOfStart"
          required
        />
      </div>
      <div class="form-group">
        <label for="dateOfEnd">Дата окончания</label>
        <input
          type="date"
          class="form-control"
          id="dateOfEnd"
          :min="minEndDay"
          v-model="promotion.dateOfEnd"
          required
        />
      </div>
      <p>Продукты</p>
      <div class="product-list" v-if="promotion.promotionProductItems">
        <div
          class="list-item mt-2 d-flex"
          v-for="product in promotion.promotionProductItems"
          :key="product.id"
        >
          <div class="wrap">
            <img
              :src="'http://localhost:9000/api/image/' + product?.image[0]?.id"
              :alt="product.nameModel"
              style="width: 50px; height: 50px"
              class="mx-2"
            />
            {{ product.brand.name }} {{ product.nameModel }}
          </div>
          <button
            @click="removeProduct(product)"
            type="button"
            class="btn btn-outline-danger ms-3"
          >
            Удалить
          </button>
          <div class="discount">
            <label class="ms-2" for="discount">Скидка</label>
            <input
              class="ms-2 form-control"
              type="number"
              name="discount"
              id=""
              v-model="product.discount"
              min="0"
              max="95"
              step="1"
            />
          </div>
        </div>
      </div>

      <button type="submit" class="btn btn-primary mt-2">
        Обновить данные акции
      </button>
    </form>
  </div>
</template>
<script setup>
import { ref, watch } from "vue";
import { ProductActions } from "@/entities/product/api/model/Actions";
import SearchComponent from "@/widgets/SearchComponent.vue";
import { PromotionAction } from "@/entities/promotion/api/PromotionAction";

const products = ref([]);
const searchQuery = ref("");
const selectedFiles = ref();
const promotions = ref([]);
const searchQueryPromo = ref("");

const todayStart = new Date(Date.now());
todayStart.setDate(todayStart.getDate());
const todayEnd = new Date(Date.now());
todayEnd.setDate(todayEnd.getDate() + 2);
const minStartDay = new Date(todayStart).toISOString().split("T")[0];
const minEndDay = new Date(todayEnd).toISOString().split("T")[0];

const promotion = ref({
  id: "",
  title: "",
  description: "",
  conditions: "",
  dateOfStart: "",
  dateOfEnd: "",
  promotionProductItems: [],
});

const handleFileSelect = (event) => {
  const files = event.target.files;
  selectedFiles.value = files[0];
};
const selectPromo = (promo) => {
  console.log(promo);
  promotion.value = promo;
};

const selectProduct = (product) => {
  if (promotion.value.id) {
    promotion.value.promotionProductItems.push(product);
  } else alert("Выберите акцию.");
};

const loadPromo = async (searchQueryPromo) => {
  console.log(searchQueryPromo);
  promotions.value = await PromotionAction.loadPromoByName(
    searchQueryPromo,
    0,
    10
  );
};

const loadProduct = async (searchQuery) => {
  console.log(searchQuery);
  if (searchQuery.trim() !== "" && searchQuery.length >= 3)
    products.value = await ProductActions.searchProduct(
      0,
      10,
      null,
      null,
      null,
      null,
      searchQuery
    );
};
const removeProduct = (product) => {
  promotion.value.promotionProductItems =
    promotion.value.promotionProductItems.filter(
      (prod) => prod.id !== product.id
    );
};

const submitPromotion = () => {
  console.log(promotion.value);
  PromotionAction.updatePromotion(promotion.value, selectedFiles.value);
};

watch(searchQuery, (newQuery) => {
  if (newQuery) {
    loadProduct(newQuery);
  } else {
    products.value = [];
  }
});

watch(searchQueryPromo, (newQuery) => {
  if (newQuery) {
    loadPromo(newQuery);
  } else {
    promotions.value = [];
  }
});
</script>

<style scoped>
.list-group-item {
  cursor: pointer;
}
</style>
