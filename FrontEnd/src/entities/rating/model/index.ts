import { Image } from "@/entities/image/model/Image";

export type Rating = {
  description: string;
  grade: number;
  userImage: Image;
  username: string;
};
