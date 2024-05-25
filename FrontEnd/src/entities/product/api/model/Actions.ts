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
  async searchProduct(
    pageNum: number,
    pageSize: number,
    categoriesId: string,
    characteristics: Characteristics[],
    name: string
  ) {
    const response = await $axios.get("/product/search", {
      params: {
        number: pageNum,
        size: pageSize,
        categories: categoriesId,
        name: name,
        characteristics: characteristics?.map((c) => c.id).join(",") || null,
      },
    });
    return response.data;
  },

  loadProductInPromotion(
    pageNumber: number,
    pageSize: number
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve, reject) =>
      $axios
        .get("/product/promotion", {
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => resolve(data.data))
        .catch((err) => reject(err.message))
    );
  },
  loadTopSales(
    pageNumber: number,
    pageSize: number
  ): Promise<PageableType<Product>> {
    return new Promise<PageableType<Product>>((resolve, reject) =>
      $axios
        .get("/product/top-selling", {
          params: { number: pageNumber, size: pageSize },
        })
        .then((data) => resolve(data.data))
        .catch((err) => reject(err.message))
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

  saveProduct(product: any, images: any) {
    const formData = new FormData();
    formData.append("product", JSON.stringify(product));

    for (let i = 0; i < images.length; i++) {
      console.log(images);
      formData.append("images", images[i]);
    }

    $axios({
      method: "post",
      url: "/product/save",
      data: formData,
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
      .then((response) => {
        console.log(response);
        // Обработка успешного ответа
      })
      .catch((error) => {
        console.log(error);
        // Обработка ошибки
      });
  },
};
