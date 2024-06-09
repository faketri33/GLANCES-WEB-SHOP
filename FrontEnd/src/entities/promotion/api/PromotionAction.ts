import { Promotion } from "@/entities/promotion/model/promotion";
import { $axios } from "@/shared/client/AxiosClient";

export const PromotionAction = {
  async loadPromo(): Promise<Promotion[]> {
    const response = await $axios.get("/promotion/");
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response);
  },
  async delete(id: string) {
    await $axios
      .delete(`/promotion/delete/${id}`)
      .then(() => alert("Акция удалена."))
      .catch(() => alert("Ошибка удаления акции."));
  },
  async loadPromoByName(
    name: string,
    number: number,
    size: number
  ): Promise<Promotion[]> {
    const response = await $axios.get("/promotion/search", {
      params: {
        name: name,
        number: number,
        size: size,
      },
    });
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response);
  },
  async loadPromoById(id: string): Promise<Promotion> {
    const response = await $axios.get("/promotion/" + id);
    return response.status === 200
      ? Promise.resolve(response.data)
      : Promise.reject(response);
  },
  async createPromotion(promotion: Promotion, images: any) {
    const formData = new FormData();
    formData.append("promo", JSON.stringify(promotion));
    formData.append("images", images);

    $axios({
      method: "post",
      url: "/promotion/save",
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
        alert(
          "Ошибка создания, проверьте данные и повторите попытку. Возможно товар уже участвует в акции. Максимальный размер изображения 1мб."
        );
        // Обработка ошибки
      });
  },
  async updatePromotion(promotion: Promotion, images: any) {
    const formData = new FormData();
    formData.append("promo", JSON.stringify(promotion));
    if (images) formData.append("images", images);

    $axios({
      method: "post",
      url: "/promotion/update",
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
        alert(
          "Ошибка создания, проверьте данные и повторите попытку. Возможно товар уже участвует в акции."
        );
        // Обработка ошибки
      });
  },
};
