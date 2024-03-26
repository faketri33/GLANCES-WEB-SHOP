import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import { Characteristics } from "@/entities/characteristics/model/Characteristics";

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
    categoriesId: string,
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

  loadProductById(id: string): Promise<Product> {
    return new Promise<Product>((resolve, reject) =>
      $axios
        .get("/product/" + id)
        .then((data) => {
          resolve(data.data);
        })
        .catch((err) => reject(err.message))
    );
  },
};
