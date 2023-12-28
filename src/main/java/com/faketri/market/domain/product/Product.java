package com.faketri.market.domain.product;

import com.faketri.market.domain.image.Image;
import com.faketri.market.domain.order.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "public", name = "product")
public class Product {
    @Id
    private Long id;
    private Brand brand;
    private String nameModel;
    private Categories categories;
    @MappedCollection
    private Set<Image> image = new HashSet<>();
    @MappedCollection
    private Set<Rating> rating = new HashSet<>();
    @MappedCollection
    private Set<Characteristics> characteristics = new HashSet<>();
    private int quantity;
    private int quantitySold = 0;
    private Long price;
    private Integer discount = 0;

    public Product(Long id, Brand brand, String name_model,
                   Long price, Integer quantity, Integer quantitySold, Integer discount) {
        this.id = id;
        this.brand = brand;
        this.nameModel = name_model;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.discount = discount;
    }

    public void addImage(Image image){ this.image.add(image); }
}
