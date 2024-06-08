import { $axios } from "@/shared/client/AxiosClient";
import { Product } from "@/entities/product/model/Product";
import { PageableType } from "@/shared/pageable/pageableType";
import { Characteristics } from "@/entities/characteristics/model/Characteristics";

export const ProductActions = {
  async findMaxPrice(): Promise<number> {
    return (await $axios.get("/product/max-price")).data;
  },
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
  async deleteProduct(id: string) {
    return await $axios.post(`/product/delete/${id}`);
  },
  async searchProduct(
    pageNum: number,
    pageSize: number,
    categoriesId: string,
    characteristics: Characteristics[],
    minPrice: number,
    maxPrice: number,
    name: string
  ) {
    const params = new URLSearchParams();
    params.append("number", String(pageNum));
    params.append("size", String(pageSize));
    if (categoriesId) params.append("categories", String(categoriesId));
    params.append("name", encodeURIComponent(name));
    /* eslint-disable */
    if (characteristics.length > 0) params.append(
      "characteristics",
      characteristics?.map((c) => c.id).join(",")
    );
    if (minPrice) params.append("minPrice", String(minPrice));
    if (maxPrice) params.append("maxPrice", String(maxPrice));
    return await $axios
      .get("/product/search", {
        params,
      })
      .then((responseData) => Promise.resolve(responseData.data))
      .catch((err) => alert("Повторите попытку позже."));
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

  updateProduct(product: any, images: any) {
    const formData = new FormData();
    formData.append("product", JSON.stringify(product));

    for (let i = 0; i < images.length; i++) {
      console.log(images);
      formData.append("images", images[i]);
    }

    $axios({
      method: "post",
      url: "/product/update",
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
