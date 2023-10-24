package com.faketri.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private Brand brand;
    private String name_model;
    private Set<Categories> categories = new HashSet<>();
    private Set<Image> image = new HashSet<>();
    private Set<Rating> rating = new HashSet<>();
    private Set<Characteristics> characteristics = new HashSet<>();
    private double price;

    public Product(long id, Brand brand, String name_model, double price) {
        this.id = id;
        this.brand = brand;
        this.name_model = name_model;
        this.price = price;
    }

    public static Builder newBuilder() {
        return new Product().new Builder();
    }

    public Builder Builder() {
        return new Builder();
    }

    public class Builder {

        public Builder() {
        }

        public Builder id(Long id){
            Product.this.id = id;
            return this;
        }
        public Builder brand(Brand brand){
            Product.this.brand = brand;
            return this;
        }

        public Builder nameModel(String nameModel){
            Product.this.name_model = nameModel;
            return this;
        }

        public Builder categories(Categories categories){
            Product.this.categories.add(categories);
            return this;
        }

        public Builder image(Image image){
            Product.this.image.add(image);
            return this;
        }

        public Builder rating(Rating rating){
            Product.this.rating.add(rating);
            return this;
        }

        public Builder characteristics(Characteristics characteristics){
            Product.this.characteristics.add(characteristics);
            return this;
        }

        public Builder price(Double price){
            Product.this.price = price;
            return this;
        }

        public Product build(){
            return Product.this;
        }
    }
}
