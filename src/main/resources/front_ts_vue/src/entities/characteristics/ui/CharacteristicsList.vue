<template>
  <div class="root">
    <p>ТИПО МЕНЮ</p>
    <p>ТИПО ФИЛЬТРАЦИЯ</p>
    <div>
      <div v-for="item in getCharacteristics" :key="item.value">
        <h3>{{ item.name }}</h3>
        <ul style="list-style-type: none">
          <li v-for="subItem in item.values" :key="subItem">
            <input
              type="checkbox"
              v-model="selectedValues"
              :value="subItem.id"
            />
            <label class="ms-2">{{ subItem.value }}</label>
          </li>
        </ul>
      </div>
      <button class="btn btn-success" @click="getSelectedValues">
        Применить
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CharacteristicsList",
  props: {
    characteristics: [],
  },
  data() {
    return {
      selectedValues: [],
    };
  },
  computed: {
    getCharacteristics() {
      return this.characteristics.reduce((acc, obj) => {
        const key = obj.name;
        if (!acc[key]) {
          acc[key] = { name: obj.name, values: [] };
        }
        acc[key].values.push({ value: obj.value, id: obj.id });
        return acc;
      }, {});
    },
  },
  methods: {
    getSelectedValues() {
      this.filtered = this.selectedValues.length > 0;
    },
  },
};
</script>

<style scoped></style>
