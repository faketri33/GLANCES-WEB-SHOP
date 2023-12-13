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
public class Categories {
    @Id
    private Long id;
    @Column
    private String name;
    @MappedCollection
    private Set<Product> products = new HashSet<>();

    public Categories(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addProduct(Product product) {this.products.add(product);}
}
