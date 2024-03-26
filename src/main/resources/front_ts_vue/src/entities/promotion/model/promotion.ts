import { Product } from "@/entities/product/model/Product";
import { Image } from "@/entities/image/model/Image";

export type Promotion = {
  id: string;
  banner: Image;
  title: string;
  description: Date;
  dateOfStart: Date;
  dateOfEnd: Date;
  promotionProductItems: Array<Product>;
};
