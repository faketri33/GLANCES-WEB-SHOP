import Brand from "@/entities/product/model/Brand";
import Categories from "@/entities/categories/model/Categories";
import Image from "@/entities/image/model/Image";
import Characteristics from "@/entities/characteristics/model/Characteristics";

export class Product {
  private _id: number;
  private _brand: Brand;
  private _nameModel: string;
  private _categories: Categories;
  private _image: Array<Image>;
  private _characteristics: Array<Characteristics>;
  private _price: number;
  private _promoPrice: number;
  private _isPromoActive: boolean;
  private _discount: number;
  private _quantity: number;
  private _quantitySold: number;

  constructor(
    id: number,
    brand: Brand,
    nameModel: string,
    categories: Categories,
    image: Array<Image>,
    characteristics: Array<Characteristics>,
    price: number,
    promoPrice: number,
    isPromoActive: boolean,
    discount: number,
    quantity: number,
    quantitySold: number
  ) {
    this._id = id;
    this._brand = brand;
    this._nameModel = nameModel;
    this._categories = categories;
    this._image = image;
    this._characteristics = characteristics;
    this._price = price;
    this._promoPrice = promoPrice;
    this._isPromoActive = isPromoActive;
    this._discount = discount;
    this._quantity = quantity;
    this._quantitySold = quantitySold;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get brand(): Brand {
    return this._brand;
  }

  set brand(value: Brand) {
    this._brand = value;
  }

  get nameModel(): string {
    return this._nameModel;
  }

  set nameModel(value: string) {
    this._nameModel = value;
  }

  get categories(): Categories {
    return this._categories;
  }

  set categories(value: Categories) {
    this._categories = value;
  }

  get image(): Array<Image> {
    return this._image;
  }

  set image(value: Array<Image>) {
    this._image = value;
  }

  get characteristics(): Array<Characteristics> {
    return this._characteristics;
  }

  set characteristics(value: Array<Characteristics>) {
    this._characteristics = value;
  }

  get price(): number {
    return this._price;
  }

  set price(value: number) {
    this._price = value;
  }

  get promoPrice(): number {
    return this._promoPrice;
  }

  set promoPrice(value: number) {
    this._promoPrice = value;
  }

  get isPromoActive(): boolean {
    return this._isPromoActive;
  }

  set isPromoActive(value: boolean) {
    this._isPromoActive = value;
  }

  get discount(): number {
    return this._discount;
  }

  set discount(value: number) {
    this._discount = value;
  }

  get quantity(): number {
    return this._quantity;
  }

  set quantity(value: number) {
    this._quantity = value;
  }

  get quantitySold(): number {
    return this._quantitySold;
  }

  set quantitySold(value: number) {
    this._quantitySold = value;
  }
}
