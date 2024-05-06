package com.faketri.market.infastructure.product.payload.product.dto;

import com.faketri.market.entity.product.payload.brand.model.Brand;
import com.faketri.market.entity.product.payload.categories.model.Categories;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class ProductCreateRequest {

    private Brand brand;
    private String nameModel;
    private Categories categories;
    private Integer price;
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
