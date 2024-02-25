import Characteristics from "@/entities/characteristics/model/Characteristics";

export type CharacteristicsState = {
  characteristics: Array<Characteristics>;
  isRequestLoading: boolean;
};
