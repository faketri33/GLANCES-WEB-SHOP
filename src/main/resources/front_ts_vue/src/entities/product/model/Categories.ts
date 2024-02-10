import Image from "../../image/model/Image";

export default class Categories {
  private _id: number;
  private _name: string;
  private _image: Image;

  constructor(id: number, name: string, image: Image) {
    this._id = id;
    this._name = name;
    this._image = image;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get image(): Image {
    return this._image;
  }

  set image(value: Image) {
    this._image = value;
  }
}
