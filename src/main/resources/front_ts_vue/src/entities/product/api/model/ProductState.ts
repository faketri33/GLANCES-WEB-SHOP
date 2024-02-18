import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";

export type ProductState = {
  product: Product;
  pages: PageableType<Product>[];
  filtered: PageableType<Product>[];
  isRequestLoading: boolean;
};
