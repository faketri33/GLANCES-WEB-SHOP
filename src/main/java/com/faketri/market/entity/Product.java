package com.faketri.market.entity;

import com.faketri.market.entity.enums.EBrand;
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
    @Enumerated(EnumType.STRING)
    private EBrand brand;
    @Column
    private String name_model;
    @ElementCollection(targetClass = ECategories.class)
    @CollectionTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"))
    private Set<ECategories> categories = new HashSet<>();
    @OneToMany
    private Set<Image> image = new HashSet<>();
    @OneToMany
    private Set<Rating> rating = new HashSet<>();
    @Column
    private double price;
}
