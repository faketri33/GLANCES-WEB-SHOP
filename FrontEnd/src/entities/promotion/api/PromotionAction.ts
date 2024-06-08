import { Promotion } from "@/entities/promotion/model/promotion";
import { $axios } from "@/shared/client/AxiosClient";

export const PromotionAction = {
  async loadPromo(): Promise<Promotion[]> {
    const response = await $axios.get("/promotion/");
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
        alert("Акция создана.");
        // Обработка успешного ответа
      })
      .catch((error) => {
        console.log(error);
        alert("Ошибка создания, проверьте данные и повторите попытку.");
        // Обработка ошибки
      });
  },
};
