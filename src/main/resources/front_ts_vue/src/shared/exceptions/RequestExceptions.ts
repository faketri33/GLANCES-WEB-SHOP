export class RequestExceptions extends Error {
  private _httpStatus: number;
  constructor(message: string, httpStatus: number) {
    super(message);
    this.name = "RequestExceptions";
    this._httpStatus = httpStatus;
  }

  get httpStatus(): number {
    return this._httpStatus;
  }

  set httpStatus(value: number) {
    this._httpStatus = value;
  }
}
