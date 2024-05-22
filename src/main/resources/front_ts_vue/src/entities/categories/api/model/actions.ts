import { Categories } from "@/entities/categories/model/Categories";
import { $axios } from "@/shared/client/AxiosClient";

export const CategoriesAction = {
  loadCategories(): Promise<Categories[]> {
    return new Promise((resolve) =>
      $axios.get("/categories/", {}).then((response) => resolve(response.data))
    );
  },
  saveCategories(categories: any, images: any) {
    const formData = new FormData();
    formData.append("categories", JSON.stringify(categories));

    for (let i = 0; i < images.length; i++) {
      console.log(images);
      formData.append("images", images[i]);
    }

    $axios({
      method: "post",
      url: "/categories/save",
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
