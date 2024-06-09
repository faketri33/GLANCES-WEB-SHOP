package com.faketri.market.infastructure.product.payload.product.dto;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import com.faketri.market.infastructure.product.payload.characteristics.dto.CharacteristicsRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProductCreateRequest {

    @NotNull
    private Brand brand;
    @NotNull
    @NotBlank(message = "Название не может быть пустым")
    private String nameModel;
    @NotNull
    private Categories categories;
    @NotNull
    @NotBlank(message = "Название не может быть пустым")
    private String description;
    @NotNull
    @Min(value = 100, message = "Цена не может быть меньше 100")
    private Integer price;
    @NotNull
    @Min(value = 1, message = "Количетсво не может быть меньше 1")
    private Integer quantity;
    @NotNull
    private List<CharacteristicsRequest> characteristicsRequestSet;

    public ProductCreateRequest() {
    }

    public ProductCreateRequest(Brand brand, String nameModel, Categories categories, Integer price, Integer quantity) {
        this.brand = brand;
        this.nameModel = nameModel;
        this.categories = categories;
        this.price = price;
        this.quantity = quantity;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<CharacteristicsRequest> getCharacteristicsRequestSet() {
        return characteristicsRequestSet;
    }

    public void setCharacteristicsRequestSet(List<CharacteristicsRequest> characteristicsRequestSet) {
        this.characteristicsRequestSet = characteristicsRequestSet;
    }

    @Override
    public String toString() {
        return "ProductCreateRequest{" +
                "brand=" + brand +
                ", nameModel='" + nameModel + '\'' +
                ", categories=" + categories +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
