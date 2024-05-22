export class PageableType<T> {
  private _content: T[];
  private _pageable: {
    pageNumber: number;
    pageSize: number;
    sort: {
      empty: boolean;
      sorted: boolean;
      unsorted: boolean;
    };
    offset: number;
    paged: boolean;
    unpaged: boolean;
  };
  private _totalElements: number;
  private _totalPages: number;
  private _last: boolean;
  private _size: number;
  private _number: number;
  private _sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  private _numberOfElements: number;
  private _first: boolean;
  private _empty: boolean;

  constructor(
    content: T[],
    pageable: {
      pageNumber: number;
      pageSize: number;
      sort: { empty: boolean; sorted: boolean; unsorted: boolean };
      offset: number;
      paged: boolean;
      unpaged: boolean;
    },
    totalElements: number,
    totalPages: number,
    last: boolean,
    size: number,
    number: number,
    sort: { empty: boolean; sorted: boolean; unsorted: boolean },
    numberOfElements: number,
    first: boolean,
    empty: boolean
  ) {
    this._content = content;
    this._pageable = pageable;
    this._totalElements = totalElements;
    this._totalPages = totalPages;
    this._last = last;
    this._size = size;
    this._number = number;
    this._sort = sort;
    this._numberOfElements = numberOfElements;
    this._first = first;
    this._empty = empty;
  }

  get content(): T[] {
    return this._content;
  }

  set content(value: T[]) {
    this._content = value;
  }

  get pageable(): {
    pageNumber: number;
    pageSize: number;
    sort: { empty: boolean; sorted: boolean; unsorted: boolean };
    offset: number;
    paged: boolean;
    unpaged: boolean;
  } {
    return this._pageable;
  }

  set pageable(value: {
    pageNumber: number;
    pageSize: number;
    sort: { empty: boolean; sorted: boolean; unsorted: boolean };
    offset: number;
    paged: boolean;
    unpaged: boolean;
  }) {
    this._pageable = value;
  }

  get totalElements(): number {
    return this._totalElements;
  }

  set totalElements(value: number) {
    this._totalElements = value;
  }

  get totalPages(): number {
    return this._totalPages;
  }

  set totalPages(value: number) {
    this._totalPages = value;
  }

  get last(): boolean {
    return this._last;
  }

  set last(value: boolean) {
    this._last = value;
  }

  get size(): number {
    return this._size;
  }

  set size(value: number) {
    this._size = value;
  }

  get number(): number {
    return this._number;
  }

  set number(value: number) {
    this._number = value;
  }

  get sort(): { empty: boolean; sorted: boolean; unsorted: boolean } {
    return this._sort;
  }

  set sort(value: { empty: boolean; sorted: boolean; unsorted: boolean }) {
    this._sort = value;
  }

  get numberOfElements(): number {
    return this._numberOfElements;
  }

  set numberOfElements(value: number) {
    this._numberOfElements = value;
  }

  get first(): boolean {
    return this._first;
  }

  set first(value: boolean) {
    this._first = value;
  }

  get empty(): boolean {
    return this._empty;
  }

  set empty(value: boolean) {
    this._empty = value;
  }
}
