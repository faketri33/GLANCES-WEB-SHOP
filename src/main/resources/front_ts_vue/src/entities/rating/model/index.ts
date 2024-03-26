import { User } from "@/entities/user/model/User";

export type Rating = {
  id: string;
  description: string;
  grade: number;
  users: User;
};
