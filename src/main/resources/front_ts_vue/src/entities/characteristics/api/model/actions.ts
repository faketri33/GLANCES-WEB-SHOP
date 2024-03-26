import { $axios } from "@/shared/client/AxiosClient";
import { Characteristics } from "@/entities/characteristics/model/Characteristics";

export const CharacteristicsAction = {
  loadCharacteristicsByProductCategories(
    categoriesId: string
  ): Promise<Characteristics[]> {
    return new Promise<Characteristics[]>((resolve) =>
      $axios
        .get("/characteristics/product/" + categoriesId)
        .then((data) => resolve(data.data))
        .catch((err) => new Error(err.message))
    );
  },

  loadCharacteristicsByProductId(productId: string) {
    return [
      { id: null, name: "ТЕСТЕРОВОЧКА", value: "ТЕСТЕРОВОЧКА" },
      { id: null, name: "ТЕСТЕРОВОЧКА1", value: "ТЕСТЕРОВОЧКА1" },
      { id: null, name: "ТЕСТЕРОВОЧКА2", value: "ТЕСТЕРОВОЧКА2" },
    ];
  },
};
