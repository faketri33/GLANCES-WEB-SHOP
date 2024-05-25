import { Product } from "@/entities/product/model/Product";

export type ProductItem = {
  id: string;
  product: Product;
  quantity: number;
};
