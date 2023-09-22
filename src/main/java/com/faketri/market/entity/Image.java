package com.faketri.market.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "image")
@Table(schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "bytea")
    private byte[] photo;
    @ManyToOne()
    @JoinColumn(name="product_id")
    private Product product;
}