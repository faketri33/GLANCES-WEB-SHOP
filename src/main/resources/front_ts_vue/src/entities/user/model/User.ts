import { Product } from "@/entities/product/model/Product";
import Image from "@/entities/image/model/Image";

export default class User {
  private _id: number;
  private _login: string;
  private _profileImage?: Image;
  private _name?: string;
  private _surname?: string;
  private _email: string;
  private _password?: string;
  private _city: string;
  private _role?: Set<string>;
  private _orders?: Set<string>;
  private _favoriteProduct?: Array<Product>;
  private _dateOfCreate?: Date;
  private _dateOfBirthday?: Date;

  constructor(
    id: number,
    login: string,
    email: string,
    password: string,
    city: string
  ) {
    this._id = id;
    this._login = login;
    this._email = email;
    this._password = password;
    this._city = city;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get login(): string {
    return this._login;
  }

  set login(value: string) {
    this._login = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get password(): string {
    return this._password ? this._password : "";
  }

  set password(value: string) {
    this._password = value;
  }

  get city(): string {
    return this._city;
  }

  set city(value: string) {
    this._city = value;
  }

  get role(): Set<string> {
    return this._role ? this._role : new Set<string>();
  }

  set role(value: Set<string>) {
    this._role = value;
  }

  get orders(): Set<string> {
    return this._orders ? this._orders : new Set<string>();
  }

  set orders(value: Set<string>) {
    this._orders = value;
  }

  get favoriteProduct(): Array<Product> {
    return this._favoriteProduct ? this._favoriteProduct : new Array<Product>();
  }

  set favoriteProduct(value: Array<Product>) {
    this._favoriteProduct = value;
  }

  get dateOfCreate(): Date {
    return this._dateOfCreate ? this._dateOfCreate : new Date();
  }

  set dateOfCreate(value: Date) {
    this._dateOfCreate = value;
  }
}
