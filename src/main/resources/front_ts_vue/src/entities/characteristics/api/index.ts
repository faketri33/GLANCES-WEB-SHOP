import { defineStore } from "pinia";
import { CharacteristicsState } from "@/entities/characteristics/api/model/state";
import Characteristics from "@/entities/characteristics/model/Characteristics";
import { CharacteristicsAction } from "@/entities/characteristics/api/model/actions";

export const storeCharacteristicsModule = defineStore("characteristics", {
  state: (): CharacteristicsState => ({
    characteristics: [] as Array<Characteristics>,
    isRequestLoading: true,
  }),
  getters: {
    getCharacteristics: (state) => state.characteristics,
  },
  actions: {
    async loadCharacteristicsByProductCategories(categoriesId: number) {
      this.isRequestLoading = true;
      const res =
        await CharacteristicsAction.loadCharacteristicsByProductCategories(
          categoriesId
        );
      this.updateCharacteristics(res);
      this.isRequestLoading = false;
    },
    updateCharacteristics(characteristics: Array<Characteristics>) {
      this.characteristics = characteristics;
    },
  },
});
