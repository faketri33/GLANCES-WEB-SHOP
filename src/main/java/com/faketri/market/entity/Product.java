package com.faketri.market.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
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
    private Long brand_id;
    @Column
    private String name_model;

    private Set<Categories> categories = new HashSet<>();
    private Set<Image> image = new HashSet<>();
    private Set<Rating> rating = new HashSet<>();
    private Set<Characteristics> characteristics = new HashSet<>();

    private String price;

}
