import { Product } from "@/entities/product/model/Product";
import { Image } from "@/entities/image/model/Image";
import { Basket } from "@/entities/basket/model/basket";

export type User = {
  id: string;
  login: string;
  profileImage: Image;
  name: string;
  surname: string;
  email: string;
  password: string;
  basket: Basket;
  city: string;
  role: Array<string>;
  orders: Array<string>;
  favoriteProduct: Array<Product>;
  dateOfCreate: Date;
  dateOfBirthday: Date;
};
