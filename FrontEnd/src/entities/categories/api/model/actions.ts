import { Categories } from "@/entities/categories/model/Categories";
import { $axios } from "@/shared/client/AxiosClient";

export const CategoriesAction = {
  loadCategories(): Promise<Categories[]> {
    return new Promise((resolve) =>
      $axios.get("/categories/", {}).then((response) => resolve(response.data))
    );
  },
  async searchByName(name: string): Promise<Categories[]> {
    return await $axios
      .get("/categories/search", {
        params: {
          name: name,
        },
      })
      .then((response) => response.data)
      .catch(() => console.error("Find categories " + name));
  },
  async delete(id: string) {
    return await $axios
      .delete(`/categories/${id}/delete`)
      .then(() => alert("Данные успещно удалены"))
      .catch(() => {
        alert("Ошибка удаления, возможно категория свазана с продуктом.");
      });
  },
  update(categories: any, images: any) {
    const formData = new FormData();
    formData.append("categories", JSON.stringify(categories));

    console.log(images);
    if (images) formData.append("images", images);

    $axios({
      method: "post",
      url: "/categories/update",
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
