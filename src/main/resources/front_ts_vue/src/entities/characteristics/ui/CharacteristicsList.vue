<template>
  <div class="root">
    <div id="filterMenu">
      <div v-for="item in getCharacteristics" :key="item.value">
        <h3>{{ item.name }}</h3>
        <ul style="list-style-type: none">
          <li v-for="subItem in item.values" :key="subItem">
            <input
              v-if="!subItem.hidden"
              class="form-check-input"
              type="checkbox"
              :id="subItem.id"
              :value="subItem.value"
              @click="addToSelected($event, subItem.id)"
            />
            <label
              class="form-check-label ms-2"
              v-if="!subItem.hidden"
              :for="subItem.id"
              >{{ subItem.value }}</label
            >
          </li>
          <button class="btn btn-link">Показать еще</button>
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
        acc[key].values.push({
          value: obj.value,
          id: obj.id,
          hidden: acc[key].values.length < 3 ? false : true,
        });
        return acc;
      }, {});
    },
  },
  methods: {
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
  },
};
</script>

<style scoped></style>
