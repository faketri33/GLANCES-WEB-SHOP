export default class Characteristics {
  private _id: number;
  private _name: string;
  private _value: string;

  constructor(id: number, name: string, value: string) {
    this._id = id;
    this._name = name;
    this._value = value;
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

  get value(): string {
    return this._value;
  }

  set value(value: string) {
    this._value = value;
  }
}
