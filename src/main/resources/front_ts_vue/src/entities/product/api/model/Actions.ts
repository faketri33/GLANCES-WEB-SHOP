import { axiosInstance, headers } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import Characteristics from "@/entities/characteristics/model/Characteristics";

export const ProductActions = {
  loadProduct(
    pageNumber: number,
    pageSize: number
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve) =>
      axiosInstance
        .get("/product/product", {
          headers: headers,
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => resolve(data.data))
        .catch((err) => new Error(err.message))
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
        ? axiosInstance
            .get("/product/categories/" + categoriesId, {
              headers: headers,
              params: { number: pageNumber, size: pageSize },
            })
            .then((data) => resolve(data.data))
            .catch((err) => new Error(err.message))
        : axiosInstance
            .post("/product/categories/" + categoriesId, filter, {
              headers: headers,
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
      axiosInstance
        .get("/product/promotion/", {
          headers: headers,
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
      axiosInstance
        .post("/product/categories/2/filter", filter, {
          headers: headers,
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => resolve(data.data))
    );
  },

  loadProductById(id: number): Promise<Product> {
    return new Promise<Product>((resolve) =>
      axiosInstance
        .get("/product/" + id, { headers: headers })
        .then((data) => resolve(data.data))
        .catch((err) => new Error(err.message))
    );
  },
};
