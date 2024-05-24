<template>
  <div class="root">
    <div id="filterMenu">
      <h3>Цена</h3>
      <div class="slider">
        <div class="track" :style="trackStyle"></div>
        <input
          type="range"
          v-model="minPrice"
          :min="min"
          :max="max"
          step="500"
          @input="updateSlider"
        />
        <input
          type="range"
          v-model="maxPrice"
          :min="min"
          :max="max"
          step="500"
          @input="updateSlider"
        />
      </div>
      <div class="row mt-3">
        <div class="col-6">
          <span class="d-flex justify-content-center align-items-center">
            От
            <input
              style="width: 65px"
              class="input form-control border rounded shadow m-2"
              type="number"
              v-model="minPrice"
              :max="max"
              min="0"
            />руб.
          </span>
        </div>
        <div class="col-6">
          <span class="d-flex justify-content-center align-items-center">
            От
            <input
              style="width: 65px"
              class="input form-control border rounded shadow m-2"
              type="number"
              v-model="maxPrice"
              :max="max"
              min="0"
            />руб.
          </span>
        </div>
      </div>
      <div class="mt-3"></div>
      <div v-for="item in getCharacteristics" :key="item.value">
        <h3>{{ item.name }}</h3>
        <ul style="list-style-type: none">
          <li v-for="subItem in item.values" :key="subItem">
            <input
              v-if="subItem.hidden"
              class="form-check-input"
              type="checkbox"
              :id="subItem.id"
              :value="subItem.value"
              @click="addToSelected($event, subItem.id)"
            />
            <label
              class="form-check-label ms-2"
              v-if="subItem.hidden"
              :for="subItem.id"
              >{{ subItem.value }}</label
            >
          </li>
          <button
            class="btn btn-link"
            @click="showMore(item.name, !item.isOpen)"
            v-if="item.values.length > 3"
          >
            {{ !item.isOpen ? "Показать еще" : "Скрыть" }}
          </button>
        </ul>
      </div>
      <button class="btn btn-success" @click="getSelectedValues">
        Применить
      </button>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "CharacteristicsList",
  props: {
    characteristics: [],
  },
  data() {
    return {
      mutableCharacteristics: ref([]),
      selectedValues: [],
      minPrice: 100,
      maxPrice: 500000,
      min: 0,
      max: 500000,
    };
  },
  created() {
    this.formatedCharacteristics();
  },
  computed: {
    getCharacteristics() {
      return this.mutableCharacteristics.value;
    },
    trackStyle() {
      const min = ((this.minPrice - this.min) / (this.max - this.min)) * 100;
      const max = ((this.maxPrice - this.min) / (this.max - this.min)) * 100;
      return {
        left: `${min}%`,
        width: `${max - min}%`,
      };
    },
  },
  watch: {
    minPrice(val, old) {
      if (val > this.max) this.minPrice = this.max;
      else this.minPrice = val;
    },
    maxPrice(val, old) {
      if (val > this.max) this.maxPrice = this.max;
      else this.maxPrice = val;
    },
  },
  methods: {
    valudInputPrice() {
      console.log("s");
    },
    formatedCharacteristics() {
      this.mutableCharacteristics.value = this.characteristics.reduce(
        (acc, obj) => {
          const key = obj.name;
          if (!acc[key]) {
            acc[key] = { name: obj.name, values: [], isOpen: false };
          }
          acc[key].values.push({
            value: obj.value,
            id: obj.id,
            hidden: acc[key].values.length < 3 ? true : false,
          });
          return acc;
        },
        {}
      );
    },
    updateSlider() {
      if (parseInt(this.minPrice) > parseInt(this.maxPrice)) {
        let temp = this.minPrice;
        this.minPrice = this.maxPrice;
        this.maxPrice = temp;
      }
    },
    addToSelected(e, id) {
      if (e.target.checked) {
        this.selectedValues.push(
          this.characteristics.find((element) => element.id === id)
        );
      } else {
        const index = this.selectedValues.findIndex(
          (element) => element.id === id
        );
        if (index > -1) {
          this.selectedValues.splice(index, 1);
        }
      }
    },
    getSelectedValues() {
      this.$emit("useFiltered", this.selectedValues);
    },
    showMore(key, type) {
      this.getCharacteristics[key].isOpen = type;
      [...this.getCharacteristics[key].values].slice(3).forEach((element) => {
        element.hidden = type;
      });
    },
  },
};
</script>

<style scoped>
.slider {
  position: relative;
  width: 100%;
  height: 5px;
  background: #ddd;
  border-radius: 5px;
}
.slider .track {
  position: absolute;
  height: 100%;
  background: #007bff;
  border-radius: 5px;
}
.slider input[type="range"] {
  position: absolute;
  width: 100%;
  height: 5px;
  -webkit-appearance: none;
  appearance: none;
  background: transparent;
  pointer-events: none;
}
.slider input[type="range"]::-webkit-slider-thumb {
  -webkit-appearance: none;
  appearance: none;
  width: 25px;
  height: 25px;
  background: #007bff;
  border: 2px solid #fff;
  border-radius: 50%;
  cursor: pointer;
  pointer-events: auto;
}
.slider input[type="range"]::-moz-range-thumb {
  width: 25px;
  height: 25px;
  background: #007bff;
  border: 2px solid #fff;
  border-radius: 50%;
  cursor: pointer;
  pointer-events: auto;
}
</style>
