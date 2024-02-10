export default class Image {
  private _id: number;
  private _path: string;

  constructor(id: number, path: string) {
    this._id = id;
    this._path = path;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get path(): string {
    return this._path;
  }

  set path(value: string) {
    this._path = value;
  }
}
