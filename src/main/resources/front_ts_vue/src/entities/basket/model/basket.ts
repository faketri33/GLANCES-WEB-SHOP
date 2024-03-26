import { ProductItem } from "@/entities/product/model/ProductItem";

export type Basket = {
  id: string;
  products: Array<ProductItem>;
};
