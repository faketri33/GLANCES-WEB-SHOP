export default class Characteristics {
  id = 0;
  name: string;
  value: string;

  constructor(name: string, value: string, id?: number) {
    if (id) this.id = id;
    this.name = name;
    this.value = value;
  }
}
