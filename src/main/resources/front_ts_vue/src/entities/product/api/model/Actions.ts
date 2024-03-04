import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Characteristics from "@/entities/characteristics/model/Characteristics";

export const ProductActions = {
  loadProduct(
    pageNumber: number,
    pageSize: number
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve) =>
      $axios
        .get("/product/product", {
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => resolve(data.data))
    );
  },

  loadProductByCategories(
    pageNumber: number,
    pageSize: number,
    categoriesId: number,
    filter?: Characteristics[]
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve) =>
      !filter
        ? $axios
            .get("/product/categories/" + categoriesId, {
              params: { number: pageNumber, size: pageSize },
            })
            .then((data) => resolve(data.data))
            .catch((err) => new Error(err.message))
        : $axios
            .post("/product/categories/" + categoriesId, filter, {
              params: { number: pageNumber, size: pageSize },
            })
            .then((data) => resolve(data.data))
            .catch((err) => new Error(err.message))
    );
  },

  loadProductInPromotion(
    pageNumber: number,
    pageSize: number
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve) =>
      $axios
        .get("/product/promotion/", {
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => resolve(data.data))
        .catch((err) => new Error(err.message))
    );
  },

  loadProductWithFilter(
    pageNumber: number,
    pageSize: number,
    filter: Characteristics[]
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve) =>
      $axios
        .post("/product/categories/2/filter", filter, {
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => {
          $axios.defaults.headers.common["Authorization"] = data.data.token;
          resolve(data.data);
        })
    );
  },

  loadProductById(id: number): Promise<Product> {
    return new Promise<Product>((resolve) =>
      $axios
        .get("/product/" + id)
        .then((data) => {
          $axios.defaults.headers.common["Authorization"] = data.data.token;
          resolve(data.data);
        })
        .catch((err) => new Error(err.message))
    );
  },
};
