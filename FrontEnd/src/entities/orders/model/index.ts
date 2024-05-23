import { Product } from "@/entities/product/model/Product";

export type Orders = {
  id: string;
  products: Array<Product>;
  dateOfCreate: Date;
  dateOfRelease: Date;
  price: number;
  payment: object;
  statusOrder: string;
};
