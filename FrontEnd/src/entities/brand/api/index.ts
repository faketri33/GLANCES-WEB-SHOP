import { Categories } from "@/entities/categories/model/Categories";
import { $axios } from "@/shared/client/AxiosClient";
import { Brand } from "@/entities/brand/model/Brand";

export const BrandAction = {
  async loadBrand(): Promise<Brand[]> {
    return (await $axios.get("/brand/")).data;
  },
  async save(brand: any) {
    return await $axios
      .post("/brand/create", brand)
      .then(() => alert("Данные успешно сохранены"))
      .catch(() => alert("Ошбика сохранения данных"));
  },
  async update(brand: any) {
    return await $axios
      .post("/brand/update", brand)
      .then(() => alert("Данные успешно сохранены"))
      .catch(() =>
        alert(
          "Ошбика сохранения данных. Выберите бренд и проверьте корректность заполненых данных."
        )
      );
  },
  async searchByName(name: string): Promise<Categories[]> {
    return await $axios
      .get("/brand/search", {
        params: {
          name: encodeURIComponent(name),
        },
      })
      .then((response) => response.data)
      .catch(() => console.error("Find brand " + name));
  },
  async delete(id: string): Promise<void> {
    await $axios
      .delete(`/brand/${id}/delete`)
      .then((response) => {
        alert("Бренд успешно удален");
        return response.data;
      })
      .catch(() => {
        console.error("delete brand " + id);
        alert("Ошибка удаления, возможно данный бренд связан с продуктом.");
      });
  },
};
