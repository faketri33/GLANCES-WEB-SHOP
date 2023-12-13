package com.faketri.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public")
public class Product {
    @Id
    private Long id;
    @Column
    private Brand brand;
    @Column
    private String name_model;
    private Categories categories;
    @MappedCollection
    private Set<Image> image = new HashSet<>();
    @MappedCollection
    private Set<Rating> rating = new HashSet<>();
    @MappedCollection
    private Set<Characteristics> characteristics = new HashSet<>();
    @Column
    private int quantity;
    @Column
    private int quantitySold = 0;
    @Column
    private Long price;

    public Product(Long id, Brand brand, String name_model, Long price, int quantity, int quantitySold) {
        this.id = id;
        this.brand = brand;
        this.name_model = name_model;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
    }

    public void addImage(Image image){ this.image.add(image); }
    public void addRating(Rating rating){ this.rating.add(rating); }
    public void addCharacteristics(Characteristics characteristics){ this.characteristics.add(characteristics); }
    public void setCategories(Categories categories) {
        this.categories = categories;
        this.categories.addProduct(this);
    }
}
