import { axiosInstance } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";

export const ProductActions = {
  loadProduct(
    pageNumber: number,
    pageSize: number
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve) =>
      axiosInstance
        .get("/product/product?number=" + pageNumber + "&size=" + pageSize)
        .then((data) => resolve(data.data))
    );
  },

  loadProductById(id: number): Promise<Product> {
    return new Promise<Product>((resolve) =>
      axiosInstance.get("/product/" + id).then((data) => resolve(data.data))
    );
  },
};
