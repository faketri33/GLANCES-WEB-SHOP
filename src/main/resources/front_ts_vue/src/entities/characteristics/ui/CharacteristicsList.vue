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
              :id="subItem.id"
              :value="subItem.value"
              @click="addToSelected($event, subItem.id)"
            />
            <label :for="subItem.id" class="ms-2">{{ subItem.value }}</label>
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
      console.log(this.characteristics);
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
    addToSelected(e, id) {
      if (e.target.checked) {
        this.selectedValues.push(
          this.characteristics.find((element) => element.id === id)
        );
      } else {
        const index = this.selectedValues.findIndex(
          (element) => element.id === id
        );
        console.log("INDEX", index);
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
