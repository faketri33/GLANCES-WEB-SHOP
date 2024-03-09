import { Product } from "@/entities/product/model/Product";
import { Image } from "@/entities/image/model/Image";

export type User = {
  id: number;
  login: string;
  profileImage: Image;
  name: string;
  surname: string;
  email: string;
  password: string;
  city: string;
  role: Array<string>;
  orders: Array<string>;
  favoriteProduct: Array<Product>;
  dateOfCreate: Date;
  dateOfBirthday: Date;
};
