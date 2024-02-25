import { axiosInstance } from "@/shared/client/AxiosClient";
import Characteristics from "@/entities/characteristics/model/Characteristics";

export const CharacteristicsAction = {
  loadCharacteristicsByProductCategories(
    categoriesId: number
  ): Promise<Characteristics[]> {
    return new Promise<Characteristics[]>((resolve) =>
      axiosInstance
        .get("/characteristics/product/" + categoriesId)
        .then((data) => resolve(data.data))
        .catch((err) => new Error(err.message))
    );
  },
};
