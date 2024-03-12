import { Product } from "@/entities/product/model/Product";

export type Basket = {
  id: string;
  products: Array<Product>;
};
