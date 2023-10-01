package com.faketri.market.entity;

import com.faketri.market.entity.enums.ECategories;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ElementCollection(targetClass = ECategories.class)
    @CollectionTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"))
    private Set<ECategories> categories = new HashSet<>();

    @OneToMany
    private Set<Image> image = new HashSet<>();

    @Column
    private double price;
}
